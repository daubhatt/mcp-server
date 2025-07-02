package com.example.mcp_server;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@Controller
public class SimulationController {

    @GetMapping("/kyc-verification")
    public Mono<String> kycVerification(@RequestParam String customerId,
                                  @RequestParam String transactionId,
                                  Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("pageTitle", "KYC Verification");
        model.addAttribute("description", "Complete your Enhanced KYC verification to enable international transfers");
        return Mono.just( "kyc-verification");
    }

    @GetMapping("/biometric-verification")
    public Mono<String> biometricVerification(@RequestParam String customerId,
                                        @RequestParam(required = false) String transactionId,
                                        Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("pageTitle", "Biometric Verification");
        model.addAttribute("description", "Complete biometric authentication for high-value transfers");
        return Mono.just("biometric-verification");
    }

    @GetMapping("/account-opening")
    public Mono<String> accountOpening(@RequestParam String customerId,
                                 @RequestParam String currency,
                                 Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("currency", currency);
        model.addAttribute("pageTitle", currency + " Account Opening");
        model.addAttribute("description", "Open a new " + currency + " account to enable currency transfers");
        return Mono.just("account-opening");
    }
}