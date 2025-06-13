package com.example.mcp_server;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BankingRecords {

    // Get Accounts Records
    public record GetAccountsRequest(
            String customerId,
            String accountType,
            Boolean includeInactive
    ) {
    }

    public record Account(
            String accountId,
            String accountNumber,
            String accountType,
            String accountName,
            String status,
            String currency,
            LocalDateTime openDate,
            LocalDateTime lastActivityDate,
            String branchCode,
            String productCode
    ) {
    }

    public record GetAccountsResponse(
            String customerId,
            List<Account> accounts,
            Integer totalCount,
            String status,
            String message
    ) {
    }

    // Get Balance by Account Records
    public record GetBalanceByAccountRequest(
            String accountId
    ) {
    }

    public record Balance(
            String accountId,
            String accountNumber,
            BigDecimal currentBalance,
            BigDecimal availableBalance,
            BigDecimal pendingBalance,
            String currency,
            LocalDateTime lastUpdated,
            BigDecimal overdraftLimit,
            BigDecimal minimumBalance
    ) {
    }

    public record GetBalanceByAccountResponse(
            Balance balance,
            String status,
            String message,
            LocalDateTime responseTime
    ) {
    }

    // Get Customer Profile Records
    public record GetCustomerProfileRequest(
            String customerId
    ) {
    }

    public record Address(
            String addressType,
            String street1,
            String street2,
            String city,
            String state,
            String zipCode,
            String country,
            Boolean isPrimary
    ) {
    }

    public record ContactInfo(
            String phoneNumber,
            String email,
            String preferredContactMethod,
            List<Address> addresses
    ) {
    }

    public record CustomerPreferences(
            String language,
            String currency,
            String timeZone,
            Boolean emailNotifications,
            Boolean smsNotifications,
            String statementDelivery
    ) {
    }

    public record CustomerProfile(
            String customerId,
            String customerNumber,
            String firstName,
            String lastName,
            String middleName,
            String dateOfBirth,
            String ssn,
            String customerType,
            String status,
            LocalDateTime joinDate,
            String relationshipManager,
            ContactInfo contactInfo,
            CustomerPreferences preferences
    ) {
    }

    public record LoginResponse(
            String accessToken,
            GetCustomerProfileResponse customerProfile
    ) {
    }

    public record GetCustomerProfileResponse(
            CustomerProfile customerProfile,
            String status,
            String message,
            LocalDateTime responseTime
    ) {
    }

    // Get Transactions Records
    public record GetTransactionsRequest(
            String accountId,
            String accountNumber,
            LocalDateTime fromDate,
            LocalDateTime toDate,
            String transactionType,
            Integer limit,
            Integer offset
    ) {
    }

    public record Transaction(
            String transactionId,
            String accountId,
            String transactionType,
            BigDecimal amount,
            String currency,
            String description,
            String merchantName,
            String category,
            LocalDateTime transactionDate,
            LocalDateTime postDate,
            String status,
            String referenceNumber,
            BigDecimal runningBalance,
            String channel,
            String location
    ) {
    }

    public record GetTransactionsResponse(
            String accountId,
            List<Transaction> transactions,
            Integer totalCount,
            Integer returnedCount,
            Boolean hasMore,
            String status,
            String message
    ) {
    }
}
