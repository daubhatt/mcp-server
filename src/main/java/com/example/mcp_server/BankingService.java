package com.example.mcp_server;

import com.example.mcp_server.BankingRecords.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class BankingService {

    // Map to store multiple customers' data
    private static final Map<String, String> CUSTOMER_MAP = Map.of(
            "Aman", "Aman Bhatt",
            "Sarah", "Sarah Al-Mansouri",
            "Ahmed", "Ahmed Hassan",
            "Fatima", "Fatima Al-Zahra",
            "Omar", "Omar Abdullah"
    );

    @Tool(description = "Retrieve a list of accounts for a customer")
    public GetAccountsResponse getAccounts(GetAccountsRequest accountsRequest, ToolContext context) {
        log.info("Retrieving accounts for customerId: {}", accountsRequest.customerId());
        return getAccountsData(accountsRequest.customerId());
    }

    @Tool(description = "Retrieve the balance for a specific account")
    public GetBalanceByAccountResponse getBalanceByAccount(GetBalanceByAccountRequest balanceByAccountRequest, ToolContext context) {
        log.info("Retrieving balance for accountId: {}", balanceByAccountRequest.accountId());
        return getBalanceByAccountData(balanceByAccountRequest.accountId());
    }

    @Tool(description = "Retrieve transactions for a specific account")
    public GetTransactionsResponse getTransactions(GetTransactionsRequest transactionsRequest, ToolContext context) {
        log.info("Retrieving transactions for accountId: {}", transactionsRequest.accountId());
        return getTransactionsData(transactionsRequest.accountId());
    }

    @Tool(description = "Retrieve the customer profile for a specific customer")
    public GetCustomerProfileResponse getCustomerProfile(GetCustomerProfileRequest customerProfileRequest, ToolContext context) {
        log.info("Retrieving customer profile for customerId: {}", customerProfileRequest.customerId());
        return getCustomerProfileData(customerProfileRequest.customerId());
    }

    @Tool(description = "Retrieve all loans for a specific customer")
    public GetLoansResponse getLoans(GetLoansRequest loansRequest, ToolContext context) {
        log.info("Retrieving loans for customerId: {}", loansRequest.customerId());
        return getLoansData(loansRequest.customerId());
    }

    @Tool(description = "Retrieve payment schedule for a specific loan")
    public GetLoanPaymentScheduleResponse getLoanPaymentSchedule(GetLoanPaymentScheduleRequest loanPaymentScheduleRequest, Integer numberOfPayments, ToolContext context) {
        log.info("Retrieving payment schedule for loanId: {} with {} payments", loanPaymentScheduleRequest.loanId(), numberOfPayments);
        return getLoanPaymentScheduleData(loanPaymentScheduleRequest.loanId(), numberOfPayments);
    }

    @Tool(description = "Retrieve credit card details for a specific account")
    public GetCreditCardDetailsResponse getCreditCardDetails(GetCreditCardDetailsRequest creditCardDetailsRequest, ToolContext context) {
        log.info("Retrieving credit card details for accountId: {}", creditCardDetailsRequest.accountId());
        return getCreditCardDetailsData(creditCardDetailsRequest.accountId());
    }

    @Tool(description = "Get complete financial overview including all accounts, loans, credit cards, investments, transaction summaries, and financial metrics in a single call")
    public GetFinancialOverviewResponse getFinancialOverview(String customerId, ToolContext context) {
        log.info("Retrieving comprehensive financial overview for customerId: {}", customerId);
        return getFinancialOverviewData(customerId);
    }

    @Tool(description = "Retrieve investment portfolio for a specific customer")
    public GetInvestmentPortfolioResponse getInvestmentPortfolio(GetInvestmentPortfolioRequest investmentPortfolioRequest, ToolContext context) {
        log.info("Retrieving investment portfolio for customerId: {}", investmentPortfolioRequest.customerId());
        return getInvestmentPortfolioData(investmentPortfolioRequest.customerId());
    }

    private GetAccountsResponse getAccountsData(String customerId) {
        if (!CUSTOMER_MAP.containsKey(customerId)) {
            return new GetAccountsResponse(
                    customerId,
                    List.of(),
                    0,
                    "FAILURE",
                    "Customer not found"
            );
        }

        return switch (customerId) {
            case "Aman" -> new GetAccountsResponse(
                    "Aman",
                    List.of(
                            new Account(
                                    "ACC001",
                                    "1234567890",
                                    "CHECKING",
                                    "Premium Checking Account",
                                    "ACTIVE",
                                    "AED",
                                    LocalDateTime.of(2020, 3, 15, 9, 30),
                                    LocalDateTime.of(2024, 5, 28, 14, 45),
                                    "BR001",
                                    "CHK001",
                                    new BigDecimal("0.5"),
                                    "PREMIUM"
                            ),
                            new Account(
                                    "ACC002",
                                    "1234567891",
                                    "SAVINGS",
                                    "High Yield Savings",
                                    "ACTIVE",
                                    "AED",
                                    LocalDateTime.of(2021, 7, 22, 10, 15),
                                    LocalDateTime.of(2024, 5, 27, 16, 20),
                                    "BR001",
                                    "SAV001",
                                    new BigDecimal("3.25"),
                                    "GOLD"
                            ),
                            new Account(
                                    "ACC003",
                                    "1234567892",
                                    "CREDIT",
                                    "Platinum Credit Card",
                                    "ACTIVE",
                                    "AED",
                                    LocalDateTime.of(2022, 1, 10, 11, 0),
                                    LocalDateTime.of(2024, 5, 29, 8, 30),
                                    "BR001",
                                    "CC001",
                                    new BigDecimal("24.99"),
                                    "PLATINUM"
                            )
                    ),
                    3,
                    "SUCCESS",
                    "Accounts retrieved successfully"
            );
            case "Sarah" -> new GetAccountsResponse(
                    "Sarah",
                    List.of(
                            new Account(
                                    "ACC004",
                                    "2234567890",
                                    "CHECKING",
                                    "Business Current Account",
                                    "ACTIVE",
                                    "AED",
                                    LocalDateTime.of(2019, 8, 10, 14, 30),
                                    LocalDateTime.of(2024, 5, 29, 10, 15),
                                    "BR002",
                                    "CHK002",
                                    new BigDecimal("0.75"),
                                    "BUSINESS"
                            ),
                            new Account(
                                    "ACC005",
                                    "2234567891",
                                    "SAVINGS",
                                    "Corporate Savings",
                                    "ACTIVE",
                                    "AED",
                                    LocalDateTime.of(2020, 2, 5, 9, 45),
                                    LocalDateTime.of(2024, 5, 28, 16, 30),
                                    "BR002",
                                    "SAV002",
                                    new BigDecimal("2.85"),
                                    "BUSINESS"
                            )
                    ),
                    2,
                    "SUCCESS",
                    "Accounts retrieved successfully"
            );
            default -> new GetAccountsResponse(
                    customerId,
                    List.of(
                            new Account(
                                    "ACC" + customerId.substring(4) + "1",
                                    "1000000" + customerId.substring(4),
                                    "CHECKING",
                                    "Standard Checking",
                                    "ACTIVE",
                                    "AED",
                                    LocalDateTime.of(2022, 1, 1, 10, 0),
                                    LocalDateTime.of(2024, 5, 29, 12, 0),
                                    "BR001",
                                    "CHK001",
                                    new BigDecimal("0.25"),
                                    "STANDARD"
                            )
                    ),
                    1,
                    "SUCCESS",
                    "Accounts retrieved successfully"
            );
        };
    }

    private GetBalanceByAccountResponse getBalanceByAccountData(String accountId) {
        return switch (accountId) {
            case "ACC001" -> {
                Balance balance = new Balance(
                        "ACC001",
                        "1234567890",
                        new BigDecimal("9350.48"), // ~2547.83 USD
                        new BigDecimal("8616.73"), // ~2347.83 USD
                        new BigDecimal("733.75"), // ~200.00 USD
                        "AED",
                        LocalDateTime.of(2024, 5, 29, 9, 15),
                        new BigDecimal("1837.50"), // ~500.00 USD
                        new BigDecimal("367.50"), // ~100.00 USD
                        new BigDecimal("156.83"),
                        new BigDecimal("1890.25")
                );
                yield new GetBalanceByAccountResponse(
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
                        new BigDecimal("55125.00"), // ~15000.00 USD
                        new BigDecimal("55125.00"),
                        BigDecimal.ZERO,
                        "AED",
                        LocalDateTime.of(2024, 5, 29, 9, 15),
                        BigDecimal.ZERO,
                        new BigDecimal("1837.50"), // ~500.00 USD
                        new BigDecimal("448.12"),
                        new BigDecimal("5125.75")
                );
                yield new GetBalanceByAccountResponse(
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
                        new BigDecimal("4410.84"), // ~1200.50 USD (credit card balance)
                        new BigDecimal("3676.84"), // ~1000.50 USD
                        new BigDecimal("734.00"), // ~200.00 USD
                        "AED",
                        LocalDateTime.of(2024, 5, 29, 9, 15),
                        new BigDecimal("11025.00"), // ~3000.00 USD
                        new BigDecimal("367.50"), // ~100.00 USD
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                );
                yield new GetBalanceByAccountResponse(
                        balance,
                        "SUCCESS",
                        "Balance retrieved successfully",
                        LocalDateTime.now()
                );
            }
            case "ACC004" -> {
                Balance balance = new Balance(
                        "ACC004",
                        "2234567890",
                        new BigDecimal("125430.75"),
                        new BigDecimal("118680.75"),
                        new BigDecimal("6750.00"),
                        "AED",
                        LocalDateTime.of(2024, 5, 29, 10, 30),
                        new BigDecimal("25000.00"),
                        new BigDecimal("5000.00"),
                        new BigDecimal("785.45"),
                        new BigDecimal("9125.80")
                );
                yield new GetBalanceByAccountResponse(
                        balance,
                        "SUCCESS",
                        "Balance retrieved successfully",
                        LocalDateTime.now()
                );
            }
            case "ACC005" -> {
                Balance balance = new Balance(
                        "ACC005",
                        "2234567891",
                        new BigDecimal("485750.25"),
                        new BigDecimal("485750.25"),
                        BigDecimal.ZERO,
                        "AED",
                        LocalDateTime.of(2024, 5, 29, 10, 30),
                        BigDecimal.ZERO,
                        new BigDecimal("10000.00"),
                        new BigDecimal("1156.75"),
                        new BigDecimal("13825.90")
                );
                yield new GetBalanceByAccountResponse(
                        balance,
                        "SUCCESS",
                        "Balance retrieved successfully",
                        LocalDateTime.now()
                );
            }
            default -> new GetBalanceByAccountResponse(
                    null,
                    "FAILURE",
                    "Account not found",
                    LocalDateTime.now()
            );
        };
    }

    private GetCustomerProfileResponse getCustomerProfileData(String customerId) {
        return switch (customerId) {
            case "Aman" -> {
                List<Address> addresses = List.of(
                        new Address(
                                "HOME",
                                "Villa 123, Al Wasl Road",
                                "Jumeirah 1",
                                "Dubai",
                                "Dubai",
                                "00000",
                                "UAE",
                                true
                        ),
                        new Address(
                                "OFFICE",
                                "Office 456, Business Bay",
                                "Executive Towers",
                                "Dubai",
                                "Dubai",
                                "00000",
                                "UAE",
                                false
                        )
                );

                ContactInfo contactInfo = new ContactInfo(
                        "+971-50-123-4567",
                        "aman.bhatt@email.com",
                        "EMAIL",
                        addresses
                );

                CustomerPreferences preferences = new CustomerPreferences(
                        "EN",
                        "AED",
                        "Asia/Dubai",
                        true,
                        true,
                        "ELECTRONIC",
                        true,
                        "ENGLISH"
                );

                EmploymentInfo employmentInfo = new EmploymentInfo(
                        "Emirates Technology Solutions",
                        "Senior Software Engineer",
                        "Technology",
                        new BigDecimal("22000.00"), // AED monthly
                        LocalDate.of(2020, 1, 15),
                        "FULL_TIME"
                );

                CustomerProfile profile = new CustomerProfile(
                        "Aman",
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
                        preferences,
                        employmentInfo,
                        "750",
                        "LOW",
                        "Indian",
                        "MARRIED"
                );

                yield new GetCustomerProfileResponse(
                        profile,
                        "SUCCESS",
                        "Customer profile retrieved successfully",
                        LocalDateTime.now()
                );
            }
            case "Sarah" -> {
                List<Address> addresses = List.of(
                        new Address(
                                "HOME",
                                "Apartment 789, Marina Walk",
                                "Dubai Marina",
                                "Dubai",
                                "Dubai",
                                "00000",
                                "UAE",
                                true
                        )
                );

                ContactInfo contactInfo = new ContactInfo(
                        "+971-55-987-6543",
                        "sarah.almansouri@email.com",
                        "SMS",
                        addresses
                );

                CustomerPreferences preferences = new CustomerPreferences(
                        "AR",
                        "AED",
                        "Asia/Dubai",
                        true,
                        true,
                        "ELECTRONIC",
                        true,
                        "ARABIC"
                );

                EmploymentInfo employmentInfo = new EmploymentInfo(
                        "Al Mansouri Trading LLC",
                        "Business Owner",
                        "Trading",
                        new BigDecimal("85000.00"), // AED monthly
                        LocalDate.of(2015, 3, 1),
                        "SELF_EMPLOYED"
                );

                CustomerProfile profile = new CustomerProfile(
                        "Sarah",
                        "C002345678",
                        "Sarah",
                        "Al-Mansouri",
                        "Ahmed",
                        "1988-11-22",
                        "XXX-XX-5678",
                        "BUSINESS",
                        "ACTIVE",
                        LocalDateTime.of(2019, 5, 18, 10, 15),
                        "RM002",
                        contactInfo,
                        preferences,
                        employmentInfo,
                        "720",
                        "MEDIUM",
                        "Emirati",
                        "SINGLE"
                );

                yield new GetCustomerProfileResponse(
                        profile,
                        "SUCCESS",
                        "Customer profile retrieved successfully",
                        LocalDateTime.now()
                );
            }
            default -> new GetCustomerProfileResponse(
                    null,
                    "FAILURE",
                    "Customer not found",
                    LocalDateTime.now()
            );
        };
    }

    private GetTransactionsResponse getTransactionsData(String accountId) {
        return switch (accountId) {
            case "ACC001" -> {
                List<Transaction> transactions = List.of(
                        new Transaction(
                                "TXN001",
                                "ACC001",
                                "DEBIT",
                                new BigDecimal("-31.32"), // ~8.52 USD Starbucks
                                "AED",
                                "Purchase - STARBUCKS DUBAI MALL",
                                "Starbucks Coffee",
                                "DINING",
                                LocalDateTime.of(2024, 5, 29, 8, 15),
                                LocalDateTime.of(2024, 5, 29, 8, 15),
                                "POSTED",
                                "REF123456789",
                                new BigDecimal("9350.48"),
                                "CARD",
                                "Dubai Mall, Dubai",
                                "MCC5814",
                                "AUTH001",
                                new BigDecimal("3.675")
                        ),
                        new Transaction(
                                "TXN002",
                                "ACC001",
                                "CREDIT",
                                new BigDecimal("22000.00"), // Monthly salary in AED
                                "AED",
                                "Direct Deposit - SALARY",
                                "Emirates Technology Solutions",
                                "SALARY",
                                LocalDateTime.of(2024, 5, 28, 9, 0),
                                LocalDateTime.of(2024, 5, 28, 9, 0),
                                "POSTED",
                                "REF123456788",
                                new BigDecimal("9381.80"),
                                "ACH",
                                "Electronic",
                                "MCC0000",
                                "SALARY001",
                                new BigDecimal("1.0")
                        ),
                        new Transaction(
                                "TXN003",
                                "ACC001",
                                "DEBIT",
                                new BigDecimal("-183.75"), // ~50 USD ATM
                                "AED",
                                "ATM Withdrawal - ADCB ATM",
                                "ADCB ATM",
                                "CASH",
                                LocalDateTime.of(2024, 5, 27, 14, 30),
                                LocalDateTime.of(2024, 5, 27, 14, 30),
                                "POSTED",
                                "REF123456787",
                                new BigDecimal("-12638.20"),
                                "ATM",
                                "Sheikh Zayed Road, Dubai",
                                "MCC6011",
                                "ATM001",
                                new BigDecimal("3.675")
                        ),
                        new Transaction(
                                "TXN004",
                                "ACC001",
                                "DEBIT",
                                new BigDecimal("-442.84"), // ~120.50 USD Amazon
                                "AED",
                                "Online Purchase - AMAZON.AE",
                                "Amazon",
                                "SHOPPING",
                                LocalDateTime.of(2024, 5, 26, 16, 45),
                                LocalDateTime.of(2024, 5, 27, 9, 0),
                                "POSTED",
                                "REF123456786",
                                new BigDecimal("-12821.95"),
                                "ONLINE",
                                "Online",
                                "MCC5399",
                                "AMZN001",
                                new BigDecimal("3.675")
                        ),
                        new Transaction(
                                "TXN005",
                                "ACC001",
                                "DEBIT",
                                new BigDecimal("-275.63"), // ~75 USD Carrefour
                                "AED",
                                "Grocery Purchase - CARREFOUR",
                                "Carrefour Hypermarket",
                                "GROCERIES",
                                LocalDateTime.of(2024, 5, 25, 18, 20),
                                LocalDateTime.of(2024, 5, 25, 18, 20),
                                "POSTED",
                                "REF123456785",
                                new BigDecimal("-12379.11"),
                                "CARD",
                                "Mall of the Emirates, Dubai",
                                "MCC5411",
                                "CAR001",
                                new BigDecimal("3.675")
                        ),
                        new Transaction(
                                "TXN006",
                                "ACC001",
                                "DEBIT",
                                new BigDecimal("-91.88"), // ~25 USD transfer
                                "AED",
                                "Transfer to Savings Account",
                                "Internal Transfer",
                                "TRANSFER",
                                LocalDateTime.of(2024, 5, 24, 10, 0),
                                LocalDateTime.of(2024, 5, 24, 10, 0),
                                "POSTED",
                                "REF123456784",
                                new BigDecimal("-12103.48"),
                                "ONLINE",
                                "Mobile Banking",
                                "MCC0000",
                                "INT001",
                                new BigDecimal("1.0")
                        ),
                        new Transaction(
                                "TXN007",
                                "ACC001",
                                "DEBIT",
                                new BigDecimal("-7350.00"), // ~2000 USD rent
                                "AED",
                                "Pending - RENT PAYMENT",
                                "Dubai Properties Group",
                                "HOUSING",
                                LocalDateTime.of(2024, 5, 29, 7, 0),
                                null,
                                "PENDING",
                                "REF123456790",
                                null,
                                "ACH",
                                "Electronic",
                                "MCC6513",
                                "RENT001",
                                new BigDecimal("1.0")
                        )
                );

                yield new GetTransactionsResponse(
                        "ACC001",
                        transactions,
                        25,
                        7,
                        true,
                        "SUCCESS",
                        "Transactions retrieved successfully"
                );
            }
            case "ACC004" -> {
                List<Transaction> transactions = List.of(
                        new Transaction(
                                "TXN008",
                                "ACC004",
                                "CREDIT",
                                new BigDecimal("18375.00"), // Business income
                                "AED",
                                "Customer Payment - AL RASHID TRADING",
                                "Al Rashid Trading LLC",
                                "BUSINESS_INCOME",
                                LocalDateTime.of(2024, 5, 29, 14, 30),
                                LocalDateTime.of(2024, 5, 29, 14, 30),
                                "POSTED",
                                "REF234567890",
                                new BigDecimal("125430.75"),
                                "WIRE",
                                "Dubai, UAE",
                                "MCC0000",
                                "WIRE001",
                                new BigDecimal("1.0")
                        ),
                        new Transaction(
                                "TXN009",
                                "ACC004",
                                "DEBIT",
                                new BigDecimal("-5512.50"), // Business expense
                                "AED",
                                "Supplier Payment - LOGISTICS CO",
                                "Emirates Logistics Company",
                                "BUSINESS_EXPENSE",
                                LocalDateTime.of(2024, 5, 28, 11, 15),
                                LocalDateTime.of(2024, 5, 28, 11, 15),
                                "POSTED",
                                "REF234567889",
                                new BigDecimal("107055.75"),
                                "ACH",
                                "Electronic",
                                "MCC4214",
                                "SUPP001",
                                new BigDecimal("1.0")
                        )
                );

                yield new GetTransactionsResponse(
                        "ACC004",
                        transactions,
                        12,
                        2,
                        true,
                        "SUCCESS",
                        "Transactions retrieved successfully"
                );
            }
            default -> new GetTransactionsResponse(
                    accountId,
                    List.of(),
                    0,
                    0,
                    false,
                    "FAILURE",
                    "Account not found or no transactions available"
            );
        };
    }

    private GetLoansResponse getLoansData(String customerId) {
        return switch (customerId) {
            case "Aman" -> {
                List<Loan> loans = List.of(
                        new Loan(
                                "LOAN001",
                                "HL001234567",
                                "HOME_LOAN",
                                "CONVENTIONAL_MORTGAGE",
                                new BigDecimal("1837500.00"), // ~500K USD home loan
                                new BigDecimal("1654000.00"), // Current balance
                                new BigDecimal("3.85"),
                                "AED",
                                LocalDate.of(2022, 3, 15),
                                LocalDate.of(2047, 3, 15), // 25 years
                                LocalDate.of(2024, 6, 15),
                                new BigDecimal("9525.75"),
                                new BigDecimal("9525.75"),
                                300, // Total payments (25 years)
                                273, // Payments remaining
                                "ACTIVE",
                                "PRIMARY_RESIDENCE",
                                new BigDecimal("183500.00"), // Total interest paid so far
                                new BigDecimal("183500.00"), // Principal paid
                                "REAL_ESTATE",
                                new BigDecimal("2205000.00"), // Property value
                                "MONTHLY",
                                LocalDate.of(2024, 5, 15),
                                new BigDecimal("9525.75")
                        ),
                        new Loan(
                                "LOAN002",
                                "PL001234568",
                                "PERSONAL_LOAN",
                                "UNSECURED_PERSONAL",
                                new BigDecimal("73500.00"), // ~20K USD personal loan
                                new BigDecimal("41825.50"), // Current balance
                                new BigDecimal("8.5"),
                                "AED",
                                LocalDate.of(2023, 8, 10),
                                LocalDate.of(2028, 8, 10), // 5 years
                                LocalDate.of(2024, 6, 10),
                                new BigDecimal("1547.25"),
                                new BigDecimal("1547.25"),
                                60, // Total payments
                                50, // Payments remaining
                                "ACTIVE",
                                "HOME_IMPROVEMENT",
                                new BigDecimal("6125.00"), // Total interest paid
                                new BigDecimal("31674.50"), // Principal paid
                                "NONE",
                                BigDecimal.ZERO,
                                "MONTHLY",
                                LocalDate.of(2024, 5, 10),
                                new BigDecimal("1547.25")
                        )
                );

                yield new GetLoansResponse(
                        "Aman",
                        loans,
                        2,
                        "SUCCESS",
                        "Loans retrieved successfully"
                );
            }
            case "Sarah" -> {
                List<Loan> loans = List.of(
                        new Loan(
                                "LOAN003",
                                "BL002345678",
                                "BUSINESS_LOAN",
                                "TERM_LOAN",
                                new BigDecimal("550000.00"), // ~150K USD business loan
                                new BigDecimal("412500.00"), // Current balance
                                new BigDecimal("5.25"),
                                "AED",
                                LocalDate.of(2021, 1, 20),
                                LocalDate.of(2031, 1, 20), // 10 years
                                LocalDate.of(2024, 6, 20),
                                new BigDecimal("5896.75"),
                                new BigDecimal("5896.75"),
                                120, // Total payments
                                82, // Payments remaining
                                "ACTIVE",
                                "BUSINESS_EXPANSION",
                                new BigDecimal("95125.50"), // Total interest paid
                                new BigDecimal("137500.00"), // Principal paid
                                "BUSINESS_ASSETS",
                                new BigDecimal("825000.00"), // Collateral value
                                "MONTHLY",
                                LocalDate.of(2024, 5, 20),
                                new BigDecimal("5896.75")
                        ),
                        new Loan(
                                "LOAN004",
                                "CL002345679",
                                "AUTO_LOAN",
                                "NEW_VEHICLE",
                                new BigDecimal("183750.00"), // ~50K USD car loan
                                new BigDecimal("128625.00"), // Current balance
                                new BigDecimal("4.75"),
                                "AED",
                                LocalDate.of(2022, 11, 5),
                                LocalDate.of(2029, 11, 5), // 7 years
                                LocalDate.of(2024, 6, 5),
                                new BigDecimal("2756.25"),
                                new BigDecimal("2756.25"),
                                84, // Total payments
                                63, // Payments remaining
                                "ACTIVE",
                                "VEHICLE_PURCHASE",
                                new BigDecimal("20125.00"), // Total interest paid
                                new BigDecimal("55125.00"), // Principal paid
                                "VEHICLE",
                                new BigDecimal("147000.00"), // Vehicle value
                                "MONTHLY",
                                LocalDate.of(2024, 5, 5),
                                new BigDecimal("2756.25")
                        )
                );

                yield new GetLoansResponse(
                        "Sarah",
                        loans,
                        2,
                        "SUCCESS",
                        "Loans retrieved successfully"
                );
            }
            default -> new GetLoansResponse(
                    customerId,
                    List.of(),
                    0,
                    "FAILURE",
                    "Customer not found or no loans available"
            );
        };
    }

    private GetLoanPaymentScheduleResponse getLoanPaymentScheduleData(String loanId, Integer numberOfPayments) {
        if (numberOfPayments == null) numberOfPayments = 12; // Default to next 12 payments

        return switch (loanId) {
            case "LOAN001" -> {
                List<PaymentScheduleItem> schedule = List.of(
                        new PaymentScheduleItem(
                                28, // Payment number
                                LocalDate.of(2024, 6, 15),
                                new BigDecimal("9525.75"),
                                new BigDecimal("3456.25"),
                                new BigDecimal("6069.50"),
                                new BigDecimal("1650543.75"),
                                "UPCOMING"
                        ),
                        new PaymentScheduleItem(
                                29,
                                LocalDate.of(2024, 7, 15),
                                new BigDecimal("9525.75"),
                                new BigDecimal("3467.38"),
                                new BigDecimal("6058.37"),
                                new BigDecimal("1647076.37"),
                                "UPCOMING"
                        ),
                        new PaymentScheduleItem(
                                30,
                                LocalDate.of(2024, 8, 15),
                                new BigDecimal("9525.75"),
                                new BigDecimal("3478.56"),
                                new BigDecimal("6047.19"),
                                new BigDecimal("1643597.81"),
                                "UPCOMING"
                        ),
                        new PaymentScheduleItem(
                                31,
                                LocalDate.of(2024, 9, 15),
                                new BigDecimal("9525.75"),
                                new BigDecimal("3489.78"),
                                new BigDecimal("6035.97"),
                                new BigDecimal("1640108.03"),
                                "UPCOMING"
                        ),
                        new PaymentScheduleItem(
                                32,
                                LocalDate.of(2024, 10, 15),
                                new BigDecimal("9525.75"),
                                new BigDecimal("3501.04"),
                                new BigDecimal("6024.71"),
                                new BigDecimal("1636606.99"),
                                "UPCOMING"
                        )
                );

                yield new GetLoanPaymentScheduleResponse(
                        "LOAN001",
                        schedule,
                        "SUCCESS",
                        "Payment schedule retrieved successfully"
                );
            }
            case "LOAN002" -> {
                List<PaymentScheduleItem> schedule = List.of(
                        new PaymentScheduleItem(
                                11, // Payment number
                                LocalDate.of(2024, 6, 10),
                                new BigDecimal("1547.25"),
                                new BigDecimal("1252.50"),
                                new BigDecimal("294.75"),
                                new BigDecimal("40573.00"),
                                "UPCOMING"
                        ),
                        new PaymentScheduleItem(
                                12,
                                LocalDate.of(2024, 7, 10),
                                new BigDecimal("1547.25"),
                                new BigDecimal("1261.38"),
                                new BigDecimal("285.87"),
                                new BigDecimal("39311.62"),
                                "UPCOMING"
                        ),
                        new PaymentScheduleItem(
                                13,
                                LocalDate.of(2024, 8, 10),
                                new BigDecimal("1547.25"),
                                new BigDecimal("1270.31"),
                                new BigDecimal("276.94"),
                                new BigDecimal("38041.31"),
                                "UPCOMING"
                        )
                );

                yield new GetLoanPaymentScheduleResponse(
                        "LOAN002",
                        schedule,
                        "SUCCESS",
                        "Payment schedule retrieved successfully"
                );
            }
            default -> new GetLoanPaymentScheduleResponse(
                    loanId,
                    List.of(),
                    "FAILURE",
                    "Loan not found"
            );
        };
    }

    private GetCreditCardDetailsResponse getCreditCardDetailsData(String accountId) {
        return switch (accountId) {
            case "ACC003" -> {
                CreditCardDetails details = new CreditCardDetails(
                        "ACC003",
                        "****-****-****-7892",
                        "AMAN BHATT",
                        "PLATINUM",
                        "ACTIVE",
                        new BigDecimal("36750.00"), // ~10K USD credit limit
                        new BigDecimal("32339.16"), // Available credit
                        new BigDecimal("4410.84"), // Current balance
                        new BigDecimal("220.54"), // Minimum payment (5% of balance)
                        LocalDate.of(2024, 5, 25), // Statement date
                        LocalDate.of(2024, 6, 20), // Payment due date
                        new BigDecimal("24.99"),
                        new BigDecimal("1500.00"), // Annual fee in AED
                        "AED",
                        15875, // Reward points
                        new BigDecimal("325.50"), // Cashback earned this year
                        LocalDate.of(2027, 1, 31),
                        "***" // Security code masked
                );

                yield new GetCreditCardDetailsResponse(
                        details,
                        "SUCCESS",
                        "Credit card details retrieved successfully",
                        LocalDateTime.now()
                );
            }
            default -> new GetCreditCardDetailsResponse(
                    null,
                    "FAILURE",
                    "Credit card account not found",
                    LocalDateTime.now()
            );
        };
    }

    private GetFinancialOverviewResponse getFinancialOverviewData(String customerId) {
        if (!CUSTOMER_MAP.containsKey(customerId)) {
            return new GetFinancialOverviewResponse(
                    null,
                    "FAILURE",
                    "Customer not found",
                    LocalDateTime.now()
            );
        }

        // Get all customer data in one aggregated response
        return switch (customerId) {
            case "Aman" -> {
                // Customer Profile
                CustomerProfile profile = getCustomerProfileData(customerId).customerProfile();

                // Account Summaries
                List<AccountSummary> accounts = List.of(
                        new AccountSummary(
                                "ACC001", "CHECKING", "Premium Checking Account",
                                new BigDecimal("9350.48"), new BigDecimal("8616.73"), "AED",
                                new BigDecimal("0.5"), "ACTIVE", new BigDecimal("156.83")
                        ),
                        new AccountSummary(
                                "ACC002", "SAVINGS", "High Yield Savings",
                                new BigDecimal("55125.00"), new BigDecimal("55125.00"), "AED",
                                new BigDecimal("3.25"), "ACTIVE", new BigDecimal("448.12")
                        )
                );

                // Loan Summaries
                List<LoanSummary> loans = List.of(
                        new LoanSummary(
                                "LOAN001", "HOME_LOAN", new BigDecimal("1654000.00"),
                                new BigDecimal("9525.75"), LocalDate.of(2024, 6, 15),
                                new BigDecimal("3.85"), 273, "ACTIVE"
                        ),
                        new LoanSummary(
                                "LOAN002", "PERSONAL_LOAN", new BigDecimal("41825.50"),
                                new BigDecimal("1547.25"), LocalDate.of(2024, 6, 10),
                                new BigDecimal("8.5"), 50, "ACTIVE"
                        )
                );

                // Credit Card Summaries
                List<CreditCardSummary> creditCards = List.of(
                        new CreditCardSummary(
                                "ACC003", "PLATINUM", new BigDecimal("4410.84"),
                                new BigDecimal("36750.00"), new BigDecimal("32339.16"),
                                new BigDecimal("12.0"), LocalDate.of(2024, 6, 20),
                                new BigDecimal("220.54"), 15875
                        )
                );

                // Investment Summary
                InvestmentSummary investments = new InvestmentSummary(
                        new BigDecimal("73405.00"), new BigDecimal("2620.00"),
                        new BigDecimal("3.70"), 4, "AED"
                );

                // Transaction Summary (last 30 days)
                TransactionSummary transactionSummary = new TransactionSummary(
                        new BigDecimal("8705.07"), // Total debits
                        new BigDecimal("22000.00"), // Total credits (salary)
                        new BigDecimal("13294.93"), // Net cash flow
                        "HOUSING", new BigDecimal("7350.00"), // Top category
                        1, new BigDecimal("7350.00") // Pending transactions
                );

                // Financial Metrics
                BigDecimal totalAssets = new BigDecimal("137880.48"); // Accounts + Investments
                BigDecimal totalLiabilities = new BigDecimal("1700236.34"); // Loans + Credit Card
                BigDecimal netWorth = totalAssets.subtract(totalLiabilities);

                FinancialMetrics metrics = new FinancialMetrics(
                        totalAssets, totalLiabilities, netWorth,
                        new BigDecimal("22000.00"), // Monthly income
                        new BigDecimal("8705.07"), // Monthly expenses
                        new BigDecimal("77.3"), // Debt-to-income ratio (1700236/22000)
                        new BigDecimal("6.9"), // Liquidity ratio (liquid assets/monthly expenses)
                        "MODERATE_RISK" // Overall health
                );

                // Upcoming Payments
                UpcomingPayments upcomingPayments = new UpcomingPayments(
                        List.of("2024-06-10", "2024-06-15", "2024-06-20"),
                        List.of("Personal Loan Payment", "Home Loan Payment", "Credit Card Minimum Payment"),
                        List.of(new BigDecimal("1547.25"), new BigDecimal("9525.75"), new BigDecimal("220.54")),
                        new BigDecimal("11293.54")
                );

                FinancialOverview overview = new FinancialOverview(
                        profile, accounts, loans, creditCards, investments,
                        transactionSummary, metrics, upcomingPayments,
                        "AED", LocalDateTime.now()
                );

                yield new GetFinancialOverviewResponse(
                        overview, "SUCCESS",
                        "Complete financial overview retrieved successfully",
                        LocalDateTime.now()
                );
            }
            case "Sarah" -> {
                // Customer Profile
                CustomerProfile profile = getCustomerProfileData(customerId).customerProfile();

                // Account Summaries
                List<AccountSummary> accounts = List.of(
                        new AccountSummary(
                                "ACC004", "CHECKING", "Business Current Account",
                                new BigDecimal("125430.75"), new BigDecimal("118680.75"), "AED",
                                new BigDecimal("0.75"), "ACTIVE", new BigDecimal("785.45")
                        ),
                        new AccountSummary(
                                "ACC005", "SAVINGS", "Corporate Savings",
                                new BigDecimal("485750.25"), new BigDecimal("485750.25"), "AED",
                                new BigDecimal("2.85"), "ACTIVE", new BigDecimal("1156.75")
                        )
                );

                // Loan Summaries
                List<LoanSummary> loans = List.of(
                        new LoanSummary(
                                "LOAN003", "BUSINESS_LOAN", new BigDecimal("412500.00"),
                                new BigDecimal("5896.75"), LocalDate.of(2024, 6, 20),
                                new BigDecimal("5.25"), 82, "ACTIVE"
                        ),
                        new LoanSummary(
                                "LOAN004", "AUTO_LOAN", new BigDecimal("128625.00"),
                                new BigDecimal("2756.25"), LocalDate.of(2024, 6, 5),
                                new BigDecimal("4.75"), 63, "ACTIVE"
                        )
                );

                // No Credit Cards for business customer
                List<CreditCardSummary> creditCards = List.of();

                // Investment Summary
                InvestmentSummary investments = new InvestmentSummary(
                        new BigDecimal("54250.00"), new BigDecimal("3400.00"),
                        new BigDecimal("6.69"), 2, "AED"
                );

                // Transaction Summary (last 30 days)
                TransactionSummary transactionSummary = new TransactionSummary(
                        new BigDecimal("25125.75"), // Total debits
                        new BigDecimal("85000.00"), // Total credits (business income)
                        new BigDecimal("59874.25"), // Net cash flow
                        "BUSINESS_EXPENSE", new BigDecimal("5512.50"), // Top category
                        0, BigDecimal.ZERO // No pending transactions
                );

                // Financial Metrics
                BigDecimal totalAssets = new BigDecimal("665431.00"); // Accounts + Investments
                BigDecimal totalLiabilities = new BigDecimal("541125.00"); // Loans only
                BigDecimal netWorth = totalAssets.subtract(totalLiabilities);

                FinancialMetrics metrics = new FinancialMetrics(
                        totalAssets, totalLiabilities, netWorth,
                        new BigDecimal("85000.00"), // Monthly income
                        new BigDecimal("25125.75"), // Monthly expenses
                        new BigDecimal("6.4"), // Debt-to-income ratio (541125/85000)
                        new BigDecimal("24.3"), // Liquidity ratio
                        "EXCELLENT" // Overall health
                );

                // Upcoming Payments
                UpcomingPayments upcomingPayments = new UpcomingPayments(
                        List.of("2024-06-05", "2024-06-20"),
                        List.of("Auto Loan Payment", "Business Loan Payment"),
                        List.of(new BigDecimal("2756.25"), new BigDecimal("5896.75")),
                        new BigDecimal("8653.00")
                );

                FinancialOverview overview = new FinancialOverview(
                        profile, accounts, loans, creditCards, investments,
                        transactionSummary, metrics, upcomingPayments,
                        "AED", LocalDateTime.now()
                );

                yield new GetFinancialOverviewResponse(
                        overview, "SUCCESS",
                        "Complete financial overview retrieved successfully",
                        LocalDateTime.now()
                );
            }
            default -> new GetFinancialOverviewResponse(
                    null,
                    "FAILURE",
                    "Customer data not available",
                    LocalDateTime.now()
            );
        };
    }

    private GetInvestmentPortfolioResponse getInvestmentPortfolioData(String customerId) {
        return switch (customerId) {
            case "Aman" -> {
                List<Investment> investments = List.of(
                        new Investment(
                                "INV001",
                                "STOCK",
                                "Emirates NBD Bank PJSC",
                                "EMIRATES.DFM",
                                new BigDecimal("500"),
                                new BigDecimal("15.75"), // Current price in AED
                                new BigDecimal("7875.00"), // Market value
                                new BigDecimal("14.25"), // Purchase price
                                new BigDecimal("750.00"), // Gain/Loss
                                new BigDecimal("10.53"), // Gain/Loss %
                                LocalDate.of(2023, 3, 15),
                                "AED",
                                "MEDIUM"
                        ),
                        new Investment(
                                "INV002",
                                "STOCK",
                                "Dubai Islamic Bank PJSC",
                                "DIB.DFM",
                                new BigDecimal("300"),
                                new BigDecimal("6.85"),
                                new BigDecimal("2055.00"),
                                new BigDecimal("6.20"),
                                new BigDecimal("195.00"),
                                new BigDecimal("10.48"),
                                LocalDate.of(2023, 7, 22),
                                "AED",
                                "LOW"
                        ),
                        new Investment(
                                "INV003",
                                "FUND",
                                "Emirates Islamic Global Equity Fund",
                                "EIGEF",
                                new BigDecimal("1000"),
                                new BigDecimal("12.45"),
                                new BigDecimal("12450.00"),
                                new BigDecimal("11.80"),
                                new BigDecimal("650.00"),
                                new BigDecimal("5.51"),
                                LocalDate.of(2022, 11, 10),
                                "AED",
                                "MEDIUM"
                        ),
                        new Investment(
                                "INV004",
                                "BOND",
                                "UAE Government Bond 2029",
                                "UAEGB29",
                                new BigDecimal("50"),
                                new BigDecimal("1020.50"), // Per unit of 1000 face value
                                new BigDecimal("51025.00"),
                                new BigDecimal("1000.00"),
                                new BigDecimal("1025.00"),
                                new BigDecimal("2.05"),
                                LocalDate.of(2023, 1, 5),
                                "AED",
                                "LOW"
                        )
                );

                yield new GetInvestmentPortfolioResponse(
                        "Aman",
                        investments,
                        new BigDecimal("73405.00"), // Total portfolio value
                        new BigDecimal("2620.00"), // Total gain/loss
                        new BigDecimal("3.70"), // Total gain/loss %
                        "AED",
                        "SUCCESS",
                        "Investment portfolio retrieved successfully"
                );
            }
            case "Sarah" -> {
                List<Investment> investments = List.of(
                        new Investment(
                                "INV005",
                                "STOCK",
                                "Emaar Properties PJSC",
                                "EMAAR.DFM",
                                new BigDecimal("2000"),
                                new BigDecimal("5.25"),
                                new BigDecimal("10500.00"),
                                new BigDecimal("4.80"),
                                new BigDecimal("900.00"),
                                new BigDecimal("9.38"),
                                LocalDate.of(2022, 8, 15),
                                "AED",
                                "MEDIUM"
                        ),
                        new Investment(
                                "INV006",
                                "FUND",
                                "ADCB Islamic Equity Fund",
                                "AIEF",
                                new BigDecimal("5000"),
                                new BigDecimal("8.75"),
                                new BigDecimal("43750.00"),
                                new BigDecimal("8.25"),
                                new BigDecimal("2500.00"),
                                new BigDecimal("6.06"),
                                LocalDate.of(2021, 12, 20),
                                "AED",
                                "MEDIUM"
                        )
                );

                yield new GetInvestmentPortfolioResponse(
                        "Sarah",
                        investments,
                        new BigDecimal("54250.00"), // Total portfolio value
                        new BigDecimal("3400.00"), // Total gain/loss
                        new BigDecimal("6.69"), // Total gain/loss %
                        "AED",
                        "SUCCESS",
                        "Investment portfolio retrieved successfully"
                );
            }
            default -> new GetInvestmentPortfolioResponse(
                    customerId,
                    List.of(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    "AED",
                    "FAILURE",
                    "Customer not found or no investments available"
            );
        };
    }
}