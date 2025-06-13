/*
package com.example.mcp_server;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer.authorizationServer;

class SecurityConfig {

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .with(authorizationServer(), Customizer.withDefaults())
                .oauth2ResourceServer(resource -> resource.jwt(Customizer.withDefaults()))
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .cors(Customizer.withDefaults())
                .build();
    }

}
*/
