package com.example.mcp_server.custom;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.metadata.ToolMetadata;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.ai.tool.support.ToolDefinitions;
import org.springframework.ai.tool.support.ToolUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class RoleBasedToolCallbackProvider implements ToolCallbackProvider {

    private final List<Object> toolObjects;
    private final Map<String, Set<String>> toolRequiredRolesMap = new ConcurrentHashMap<>(); // Map tool name to required roles

    public RoleBasedToolCallbackProvider(List<Object> toolObjects) {
        this.toolObjects = toolObjects;
        discoverAndMapTools();
    }

    private void discoverAndMapTools() {
        for (Object toolObject : toolObjects) {
            for (Method method : toolObject.getClass().getMethods()) {
                if (method.isAnnotationPresent(Tool.class)) {
                    Tool toolAnnotation = method.getAnnotation(Tool.class);
                    String toolName = StringUtils.hasText(toolAnnotation.name()) ? toolAnnotation.name() : method.getName();

                    if (method.isAnnotationPresent(AuthorizedTool.class)) {
                        AuthorizedTool authorizedTool = method.getAnnotation(AuthorizedTool.class);
                        toolRequiredRolesMap.put(toolName, Arrays.stream(authorizedTool.value()).collect(Collectors.toSet()));
                    } else {
                        // If no @AuthorizedTool, consider it public or require a default role
                        toolRequiredRolesMap.put(toolName, Set.of()); // Empty set means no specific role required for now
                    }
                }
            }
        }
    }

    @Override
    public ToolCallback[] getToolCallbacks() {
        Authentication authentication = ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication)
                .block();

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ToolCallback[]{}; // No authenticated user, no tools
        }
        System.out.println("Authenticated user: " + authentication.getPrincipal());

        Set<String> userRoles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        List<ToolCallback> accessibleTools = new ArrayList<>();

        for (Object toolObject : toolObjects) {
            for (Method method : toolObject.getClass().getMethods()) {
                if (method.isAnnotationPresent(Tool.class)) {
                    Tool toolAnnotation = method.getAnnotation(Tool.class);
                    String toolName = StringUtils.hasText(toolAnnotation.name()) ? toolAnnotation.name() : method.getName();

                    Set<String> requiredRoles = toolRequiredRolesMap.getOrDefault(toolName, Set.of());

                    if (requiredRoles.isEmpty() || userRoles.stream().anyMatch(requiredRoles::contains)) {
                        // User has the required role(s) or no specific role is required
                        accessibleTools.add(createToolCallback(toolObject, method, toolName, toolAnnotation.description()));
                    }
                }
            }
        }
        return accessibleTools.toArray(ToolCallback[]::new);
    }

    private ToolCallback createToolCallback(Object toolObject, Method method, String toolName, String toolDescription) {
        return MethodToolCallback.builder()
                .toolDefinition(ToolDefinitions.from(method))
                .toolMetadata(ToolMetadata.from(method))
                .toolMethod(method)
                .toolObject(toolObject)
                .toolCallResultConverter(ToolUtils.getToolCallResultConverter(method)).build();
    }
}
