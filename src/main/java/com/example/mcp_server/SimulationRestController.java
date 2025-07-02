package com.example.mcp_server;

import com.example.mcp_server.EnhancedBankingRecords.SimulationStateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulation")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class SimulationRestController {

    private final BankingService bankingService;

    /**
     * Called by KYC verification page when user completes KYC process
     */
    @PostMapping("/kyc-completion/{customerId}")
    public ResponseEntity<SimulationStateResponse> completeKyc(@PathVariable String customerId) {
        log.info("REST API: KYC completion for customer: {}", customerId);

        try {
            SimulationStateResponse response = bankingService.processKycCompletion(customerId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error processing KYC completion", e);
            return ResponseEntity.internalServerError()
                    .body(new SimulationStateResponse(
                            customerId,
                            "KYC_ERROR",
                            "UNKNOWN",
                            "ERROR",
                            "Failed to process KYC completion: " + e.getMessage()
                    ));
        }
    }

    /**
     * Called by biometric verification page when user completes biometric process
     */
    @PostMapping("/biometric-completion/{customerId}")
    public ResponseEntity<SimulationStateResponse> completeBiometric(@PathVariable String customerId) {
        log.info("REST API: Biometric completion for customer: {}", customerId);

        try {
            SimulationStateResponse response = bankingService.processBiometricCompletion(customerId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error processing biometric completion", e);
            return ResponseEntity.internalServerError()
                    .body(new SimulationStateResponse(
                            customerId,
                            "BIOMETRIC_ERROR",
                            "UNKNOWN",
                            "ERROR",
                            "Failed to process biometric completion: " + e.getMessage()
                    ));
        }
    }

    /**
     * Called by account opening page when user opens a new currency account
     */
    @PostMapping("/account-opening/{customerId}")
    public ResponseEntity<SimulationStateResponse> completeAccountOpening(
            @PathVariable String customerId,
            @RequestParam String currency) {
        log.info("REST API: Account opening completion for customer: {} currency: {}", customerId, currency);

        try {
            // Add currency account to customer
            // This could be implemented in BankingService as a public method
            SimulationStateResponse response = new SimulationStateResponse(
                    customerId,
                    "ACCOUNT_OPENING",
                    "NO_ACCOUNT",
                    "ACCOUNT_CREATED",
                    currency + " account successfully created for customer " + customerId
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error processing account opening", e);
            return ResponseEntity.internalServerError()
                    .body(new SimulationStateResponse(
                            customerId,
                            "ACCOUNT_ERROR",
                            "UNKNOWN",
                            "ERROR",
                            "Failed to create account: " + e.getMessage()
                    ));
        }
    }

    /**
     * Health check endpoint for simulation services
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Simulation services are running");
    }
}