package com.example.mcp_server;

import java.math.BigDecimal;
import java.time.LocalDate;
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
            String productCode,
            BigDecimal interestRate,
            String accountTier
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
            BigDecimal minimumBalance,
            BigDecimal monthlyInterestEarned,
            BigDecimal yearToDateInterestEarned
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
            String statementDelivery,
            Boolean paperlessStatements,
            String communicationLanguage
    ) {
    }

    public record EmploymentInfo(
            String employerName,
            String jobTitle,
            String industry,
            BigDecimal monthlyIncome,
            LocalDate employmentStartDate,
            String employmentType
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
            CustomerPreferences preferences,
            EmploymentInfo employmentInfo,
            String creditScore,
            String riskProfile,
            String nationality,
            String maritalStatus
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
            String location,
            String merchantCategory,
            String authorizationCode,
            BigDecimal exchangeRate
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

    // Loan Records
    public record GetLoansRequest(
            String customerId
    ) {
    }

    public record Loan(
            String loanId,
            String loanNumber,
            String loanType,
            String loanSubType,
            BigDecimal principalAmount,
            BigDecimal currentBalance,
            BigDecimal interestRate,
            String currency,
            LocalDate startDate,
            LocalDate maturityDate,
            LocalDate nextPaymentDate,
            BigDecimal monthlyPayment,
            BigDecimal nextPaymentAmount,
            Integer totalPayments,
            Integer paymentsRemaining,
            String status,
            String purpose,
            BigDecimal totalInterestPaid,
            BigDecimal principalPaid,
            String collateralType,
            BigDecimal collateralValue,
            String repaymentFrequency,
            LocalDate lastPaymentDate,
            BigDecimal lastPaymentAmount
    ) {
    }

    public record GetLoansResponse(
            String customerId,
            List<Loan> loans,
            Integer totalCount,
            String status,
            String message
    ) {
    }

    // Loan Payment Schedule Records
    public record GetLoanPaymentScheduleRequest(
            String loanId,
            Integer numberOfPayments
    ) {
    }

    public record PaymentScheduleItem(
            Integer paymentNumber,
            LocalDate paymentDate,
            BigDecimal paymentAmount,
            BigDecimal principalAmount,
            BigDecimal interestAmount,
            BigDecimal remainingBalance,
            String status
    ) {
    }

    public record GetLoanPaymentScheduleResponse(
            String loanId,
            List<PaymentScheduleItem> paymentSchedule,
            String status,
            String message
    ) {
    }

    // Credit Card Records
    public record GetCreditCardDetailsRequest(
            String accountId
    ) {
    }

    public record CreditCardDetails(
            String accountId,
            String cardNumber,
            String cardHolderName,
            String cardType,
            String cardStatus,
            BigDecimal creditLimit,
            BigDecimal availableCredit,
            BigDecimal currentBalance,
            BigDecimal minimumPayment,
            LocalDate statementDate,
            LocalDate paymentDueDate,
            BigDecimal interestRate,
            BigDecimal annualFee,
            String currency,
            Integer rewardPoints,
            BigDecimal cashbackEarned,
            LocalDate expiryDate,
            String securityCode
    ) {
    }

    public record GetCreditCardDetailsResponse(
            CreditCardDetails creditCardDetails,
            String status,
            String message,
            LocalDateTime responseTime
    ) {
    }

    // Investment Portfolio Records
    public record GetInvestmentPortfolioRequest(
            String customerId
    ) {
    }

    public record Investment(
            String investmentId,
            String instrumentType,
            String instrumentName,
            String symbol,
            BigDecimal quantity,
            BigDecimal currentPrice,
            BigDecimal marketValue,
            BigDecimal purchasePrice,
            BigDecimal gainLoss,
            BigDecimal gainLossPercentage,
            LocalDate purchaseDate,
            String currency,
            String riskLevel
    ) {
    }

    public record GetInvestmentPortfolioResponse(
            String customerId,
            List<Investment> investments,
            BigDecimal totalPortfolioValue,
            BigDecimal totalGainLoss,
            BigDecimal totalGainLossPercentage,
            String currency,
            String status,
            String message
    ) {
    }

    // Comprehensive Financial Overview Records
    public record GetFinancialOverviewRequest(
            String customerId,
            Boolean includeTransactionSummary,
            Boolean includeLoanDetails,
            Boolean includeInvestmentDetails
    ) {
    }

    public record AccountSummary(
            String accountId,
            String accountType,
            String accountName,
            BigDecimal currentBalance,
            BigDecimal availableBalance,
            String currency,
            BigDecimal interestRate,
            String status,
            BigDecimal monthlyInterestEarned
    ) {
    }

    public record LoanSummary(
            String loanId,
            String loanType,
            BigDecimal currentBalance,
            BigDecimal monthlyPayment,
            LocalDate nextPaymentDate,
            BigDecimal interestRate,
            Integer paymentsRemaining,
            String status
    ) {
    }

    public record CreditCardSummary(
            String accountId,
            String cardType,
            BigDecimal currentBalance,
            BigDecimal creditLimit,
            BigDecimal availableCredit,
            BigDecimal utilizationPercentage,
            LocalDate paymentDueDate,
            BigDecimal minimumPayment,
            Integer rewardPoints
    ) {
    }

    public record InvestmentSummary(
            BigDecimal totalPortfolioValue,
            BigDecimal totalGainLoss,
            BigDecimal totalGainLossPercentage,
            Integer numberOfInvestments,
            String currency
    ) {
    }

    public record TransactionSummary(
            BigDecimal totalDebits30Days,
            BigDecimal totalCredits30Days,
            BigDecimal netCashFlow30Days,
            String topSpendingCategory,
            BigDecimal topCategoryAmount,
            Integer pendingTransactionCount,
            BigDecimal pendingAmount
    ) {
    }

    public record FinancialMetrics(
            BigDecimal totalAssets,
            BigDecimal totalLiabilities,
            BigDecimal netWorth,
            BigDecimal monthlyIncome,
            BigDecimal monthlyExpenses,
            BigDecimal debtToIncomeRatio,
            BigDecimal liquidityRatio,
            String overallFinancialHealth
    ) {
    }

    public record UpcomingPayments(
            List<String> dueDates,
            List<String> descriptions,
            List<BigDecimal> amounts,
            BigDecimal totalUpcoming30Days
    ) {
    }

    public record FinancialOverview(
            CustomerProfile customerProfile,
            List<AccountSummary> accounts,
            List<LoanSummary> loans,
            List<CreditCardSummary> creditCards,
            InvestmentSummary investments,
            TransactionSummary transactionSummary,
            FinancialMetrics financialMetrics,
            UpcomingPayments upcomingPayments,
            String currency,
            LocalDateTime generatedAt
    ) {
    }

    public record GetFinancialOverviewResponse(
            FinancialOverview financialOverview,
            String status,
            String message,
            LocalDateTime responseTime
    ) {
    }
}