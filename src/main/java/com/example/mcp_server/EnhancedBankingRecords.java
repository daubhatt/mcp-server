package com.example.mcp_server;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EnhancedBankingRecords {

    // Existing records remain the same, adding new ones for remittance

    // International Remittance Records
    public record InitiateRemittanceRequest(
            String customerId,
            String fromAccountId,
            String recipientName,
            String recipientCountry,
            String recipientBank,
            String recipientAccountNumber,
            BigDecimal amount,
            String currency,
            String purpose,
            String relationship
    ) {}

    public record RemittanceValidationResponse(
            String transactionId,
            String status, // SUCCESS, KYC_REQUIRED, TOKEN_ELEVATION_REQUIRED, INSUFFICIENT_FUNDS, etc.
            String message,
            BigDecimal amount,
            String currency,
            BigDecimal fees,
            BigDecimal exchangeRate,
            String estimatedDelivery,
            String kycUrl, // URL for KYC completion if needed
            String biometricUrl, // URL for token elevation if needed
            List<String> requiredDocuments,
            LocalDateTime responseTime
    ) {}

    public record CompleteRemittanceRequest(
            String transactionId,
            String customerId
    ) {}

    public record RemittanceExecutionResponse(
            String transactionId,
            String referenceNumber,
            String status, // PROCESSING, COMPLETED, FAILED
            String message,
            BigDecimal amountDebited,
            BigDecimal fees,
            String recipientDetails,
            String estimatedDelivery,
            LocalDateTime executionTime
    ) {}

    // Currency Conversion Records
    public record CheckCurrencyAccountRequest(
            String customerId,
            String currency
    ) {}

    public record CurrencyAccountResponse(
            String customerId,
            String currency,
            Boolean hasAccount,
            String accountId,
            BigDecimal balance,
            String status,
            String message,
            String openAccountUrl // URL to open new currency account
    ) {}

    public record CurrencyConversionRequest(
            String customerId,
            String fromAccountId,
            String toAccountId,
            String fromCurrency,
            String toCurrency,
            BigDecimal amount
    ) {}

    public record CurrencyConversionResponse(
            String transactionId,
            String status,
            String message,
            BigDecimal convertedAmount,
            BigDecimal exchangeRate,
            BigDecimal fees,
            LocalDateTime executionTime
    ) {}

    // KYC and Verification Records
    public record CustomerKycStatusRequest(
            String customerId
    ) {}

    public record KycStatusResponse(
            String customerId,
            String kycLevel, // BASIC, ENHANCED, PREMIUM
            Boolean internationalTransferAllowed,
            BigDecimal dailyLimit,
            BigDecimal monthlyLimit,
            List<String> restrictedCountries,
            LocalDateTime lastVerified
    ) {}

    public record TokenElevationStatusRequest(
            String customerId
    ) {}

    public record TokenElevationResponse(
            String customerId,
            String currentLevel, // BASIC, ELEVATED
            Boolean highValueTransferAllowed,
            BigDecimal elevatedLimit,
            String biometricUrl,
            LocalDateTime expiresAt
    ) {}

    // Account Opening Records
    public record AccountOpeningRequest(
            String customerId,
            String currency,
            String accountType,
            String purpose
    ) {}

    public record AccountOpeningResponse(
            String applicationId,
            String status, // INITIATED, PENDING_DOCUMENTS, APPROVED, REJECTED
            String message,
            String currency,
            String accountType,
            List<String> requiredDocuments,
            String documentUploadUrl,
            String estimatedProcessingTime
    ) {}

    // Transaction History for Remittances
    public record RemittanceHistoryRequest(
            String customerId,
            LocalDateTime fromDate,
            LocalDateTime toDate,
            String status
    ) {}

    public record RemittanceTransaction(
            String transactionId,
            String referenceNumber,
            String recipientName,
            String recipientCountry,
            BigDecimal amount,
            String currency,
            String status,
            BigDecimal fees,
            LocalDateTime initiatedAt,
            LocalDateTime completedAt,
            String purpose
    ) {}

    public record RemittanceHistoryResponse(
            String customerId,
            List<RemittanceTransaction> transactions,
            Integer totalCount,
            String status,
            String message
    ) {}

    // Compliance and Limits
    public record CustomerLimitsRequest(
            String customerId
    ) {}

    public record TransactionLimits(
            String limitType, // DAILY, MONTHLY, YEARLY
            BigDecimal currentUsage,
            BigDecimal limit,
            String currency,
            LocalDate resetDate
    ) {}

    public record CustomerLimitsResponse(
            String customerId,
            List<TransactionLimits> limits,
            String kycLevel,
            String tokenLevel,
            String status,
            String message
    ) {}

    // Simulation Control Records (for testing workflows)
    public record SimulationStateRequest(
            String customerId,
            String action, // SET_KYC_STATUS, SET_TOKEN_STATUS, SET_ACCOUNT_STATUS
            String value
    ) {}

    public record SimulationStateResponse(
            String customerId,
            String action,
            String previousValue,
            String newValue,
            String message
    ) {}
}