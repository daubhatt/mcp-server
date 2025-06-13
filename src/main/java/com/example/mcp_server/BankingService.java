package com.example.mcp_server;

import com.example.mcp_server.BankingRecords.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class BankingService {

    // Method to get accounts
    @Tool(description = "Retrieve a list of accounts for a customer")
    public GetAccountsResponse getAccounts(String customerId, ToolContext context) {
        // In a real application, this would query a database or external service
        // Here we return hardcoded data for demonstration purposes
        return getAccountsData(customerId);
    }

    // Method to get balance by account
    @Tool(description = "Retrieve the balance for a specific account")
    public GetBalanceByAccountResponse getBalanceByAccount(String accountId, ToolContext context) {
        // In a real application, this would query a database or external service
        // Here we return hardcoded data for demonstration purposes
        return getBalanceByAccountData(accountId);
    }

    // Method to get transactions
    @Tool(description = "Retrieve transactions for a specific account")
    public GetTransactionsResponse getTransactions(String accountId, ToolContext context) {
        // In a real application, this would query a database or external service
        // Here we return hardcoded data for demonstration purposes
        return getTransactionsData(accountId);
    }

    // Method to get customer profile
    @Tool(description = "Retrieve the customer profile for a specific customer")
    public GetCustomerProfileResponse getCustomerProfile(String customerId, ToolContext context) {
        log.info("Retrieving customer profile for customerId: {}", customerId);
        // In a real application, this would query a database or external service
        // Here we return hardcoded data for demonstration purposes
        return getCustomerProfileData(customerId);
    }

    // Private methods returning hardcoded data
    private GetAccountsResponse getAccountsData(String customerId) {
        if (!"CUST001".equals(customerId)) {
            return new GetAccountsResponse(
                    customerId,
                    List.of(),
                    0,
                    "FAILURE",
                    "Customer not found"
            );
        }
        List<Account> accounts = List.of(
                new Account(
                        "ACC001",
                        "1234567890",
                        "CHECKING",
                        "Primary Checking",
                        "ACTIVE",
                        "USD",
                        LocalDateTime.of(2020, 3, 15, 9, 30),
                        LocalDateTime.of(2024, 5, 28, 14, 45),
                        "BR001",
                        "CHK001"
                ),
                new Account(
                        "ACC002",
                        "1234567891",
                        "SAVINGS",
                        "High Yield Savings",
                        "ACTIVE",
                        "USD",
                        LocalDateTime.of(2021, 7, 22, 10, 15),
                        LocalDateTime.of(2024, 5, 27, 16, 20),
                        "BR001",
                        "SAV001"
                ),
                new Account(
                        "ACC003",
                        "1234567892",
                        "CREDIT",
                        "Platinum Credit Card",
                        "ACTIVE",
                        "USD",
                        LocalDateTime.of(2022, 1, 10, 11, 0),
                        LocalDateTime.of(2024, 5, 29, 8, 30),
                        "BR001",
                        "CC001"
                )
        );

        return new GetAccountsResponse(
                "CUST001",
                accounts,
                3,
                "SUCCESS",
                "Accounts retrieved successfully"
        );
    }

    private GetBalanceByAccountResponse getBalanceByAccountData(String accountId) {
        switch (accountId) {
            case "ACC001" -> {
                Balance balance = new Balance(
                        "ACC001",
                        "1234567890",
                        new BigDecimal("2547.83"),
                        new BigDecimal("2347.83"),
                        new BigDecimal("200.00"),
                        "USD",
                        LocalDateTime.of(2024, 5, 29, 9, 15),
                        new BigDecimal("500.00"),
                        new BigDecimal("100.00")
                );

                return new GetBalanceByAccountResponse(
                        balance,
                        "SUCCESS",
                        "Balance retrieved successfully",
                        LocalDateTime.now()
                );
            }
            case "ACC002" -> {
                Balance balance = new Balance(
                        "ACC002",
                        "1234567891",
                        new BigDecimal("15000.00"),
                        new BigDecimal("15000.00"),
                        BigDecimal.ZERO,
                        "USD",
                        LocalDateTime.of(2024, 5, 29, 9, 15),
                        BigDecimal.ZERO,
                        new BigDecimal("500.00")
                );

                return new GetBalanceByAccountResponse(
                        balance,
                        "SUCCESS",
                        "Balance retrieved successfully",
                        LocalDateTime.now()
                );
            }
            case "ACC003" -> {
                Balance balance = new Balance(
                        "ACC003",
                        "1234567892",
                        new BigDecimal("1200.50"),
                        new BigDecimal("1000.50"),
                        new BigDecimal("200.00"),
                        "USD",
                        LocalDateTime.of(2024, 5, 29, 9, 15),
                        new BigDecimal("3000.00"),
                        new BigDecimal("100.00")
                );

                return new GetBalanceByAccountResponse(
                        balance,
                        "SUCCESS",
                        "Balance retrieved successfully",
                        LocalDateTime.now()
                );
            }
            default -> {
                return new GetBalanceByAccountResponse(
                        null,
                        "FAILURE",
                        "Account not found",
                        LocalDateTime.now()
                );
            }
        }

    }

    private GetCustomerProfileResponse getCustomerProfileData(String customerId) {
        if (!"CUST001".equals(customerId)) {
            return new GetCustomerProfileResponse(
                    null,
                    "FAILURE",
                    "Customer not found",
                    LocalDateTime.now()
            );
        }
        List<Address> addresses = List.of(
                new Address(
                        "HOME",
                        "123 Main Street",
                        "Apt 4B",
                        "New York",
                        "NY",
                        "10001",
                        "USA",
                        true
                ),
                new Address(
                        "MAILING",
                        "456 Oak Avenue",
                        null,
                        "Brooklyn",
                        "NY",
                        "11201",
                        "USA",
                        false
                )
        );

        ContactInfo contactInfo = new ContactInfo(
                "+1-555-123-4567",
                "aman.bhatt@email.com",
                "EMAIL",
                addresses
        );

        CustomerPreferences preferences = new CustomerPreferences(
                "EN",
                "USD",
                "America/New_York",
                true,
                false,
                "ELECTRONIC"
        );

        CustomerProfile profile = new CustomerProfile(
                "CUST001",
                "C001234567",
                "Aman",
                "Bhatt",
                "",
                "1985-06-15",
                "XXX-XX-1234",
                "INDIVIDUAL",
                "ACTIVE",
                LocalDateTime.of(2018, 9, 12, 14, 30),
                "RM001",
                contactInfo,
                preferences
        );

        return new GetCustomerProfileResponse(
                profile,
                "SUCCESS",
                "Customer profile retrieved successfully",
                LocalDateTime.now()
        );
    }

    private GetTransactionsResponse getTransactionsData(String accountId) {
        if ("ACC002".equals(accountId) || "ACC003".equals(accountId)) {
            return new GetTransactionsResponse(
                    accountId,
                    List.of(),
                    0,
                    0,
                    false,
                    "SUCCESS",
                    "Transactions not found"
            );
        } else if ("ACC001".equals(accountId)) {
            List<Transaction> transactions = List.of(
                    new Transaction(
                            "TXN001",
                            "ACC001",
                            "DEBIT",
                            new BigDecimal("-85.32"),
                            "USD",
                            "Purchase - STARBUCKS #1234",
                            "Starbucks Coffee",
                            "DINING",
                            LocalDateTime.of(2024, 5, 29, 8, 15),
                            LocalDateTime.of(2024, 5, 29, 8, 15),
                            "POSTED",
                            "REF123456789",
                            new BigDecimal("2547.83"),
                            "CARD",
                            "New York, NY"
                    ),
                    new Transaction(
                            "TXN002",
                            "ACC001",
                            "CREDIT",
                            new BigDecimal("2500.00"),
                            "USD",
                            "Direct Deposit - PAYROLL",
                            "ABC Corporation",
                            "SALARY",
                            LocalDateTime.of(2024, 5, 28, 9, 0),
                            LocalDateTime.of(2024, 5, 28, 9, 0),
                            "POSTED",
                            "REF123456788",
                            new BigDecimal("2633.15"),
                            "ACH",
                            "Electronic"
                    ),
                    new Transaction(
                            "TXN003",
                            "ACC001",
                            "DEBIT",
                            new BigDecimal("-45.67"),
                            "USD",
                            "ATM Withdrawal",
                            "Chase ATM",
                            "CASH",
                            LocalDateTime.of(2024, 5, 27, 14, 30),
                            LocalDateTime.of(2024, 5, 27, 14, 30),
                            "POSTED",
                            "REF123456787",
                            new BigDecimal("133.15"),
                            "ATM",
                            "Manhattan, NY"
                    ),
                    new Transaction(
                            "TXN004",
                            "ACC001",
                            "DEBIT",
                            new BigDecimal("-120.50"),
                            "USD",
                            "Online Purchase - AMAZON.COM",
                            "Amazon",
                            "SHOPPING",
                            LocalDateTime.of(2024, 5, 26, 16, 45),
                            LocalDateTime.of(2024, 5, 27, 9, 0),
                            "POSTED",
                            "REF123456786",
                            new BigDecimal("178.82"),
                            "ONLINE",
                            "Online"
                    ),
                    new Transaction(
                            "TXN005",
                            "ACC001",
                            "DEBIT",
                            new BigDecimal("-75.00"),
                            "USD",
                            "Grocery Store Purchase",
                            "Whole Foods Market",
                            "GROCERIES",
                            LocalDateTime.of(2024, 5, 25, 18, 20),
                            LocalDateTime.of(2024, 5, 25, 18, 20),
                            "POSTED",
                            "REF123456785",
                            new BigDecimal("299.32"),
                            "CARD",
                            "Brooklyn, NY"
                    ),
                    new Transaction(
                            "TXN006",
                            "ACC001",
                            "DEBIT",
                            new BigDecimal("-25.00"),
                            "USD",
                            "Transfer to Savings",
                            "Internal Transfer",
                            "TRANSFER",
                            LocalDateTime.of(2024, 5, 24, 10, 0),
                            LocalDateTime.of(2024, 5, 24, 10, 0),
                            "POSTED",
                            "REF123456784",
                            new BigDecimal("374.32"),
                            "ONLINE",
                            "Mobile App"
                    ),
                    new Transaction(
                            "TXN007",
                            "ACC001",
                            "DEBIT",
                            new BigDecimal("-200.00"),
                            "USD",
                            "Pending - RENT PAYMENT",
                            "Property Management Co",
                            "HOUSING",
                            LocalDateTime.of(2024, 5, 29, 7, 0),
                            null,
                            "PENDING",
                            "REF123456790",
                            null,
                            "ACH",
                            "Electronic"
                    )
            );

            return new GetTransactionsResponse(
                    "ACC001",
                    transactions,
                    25,
                    7,
                    true,
                    "SUCCESS",
                    "Transactions retrieved successfully"
            );
        } else {
            return new GetTransactionsResponse(
                    accountId,
                    List.of(),
                    0,
                    0,
                    false,
                    "FAILURE",
                    "Account not found"
            );
        }
    }
}