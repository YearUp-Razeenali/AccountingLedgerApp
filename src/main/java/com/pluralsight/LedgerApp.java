package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LedgerApp {
    private static TransactionManager transactionManager = new TransactionManager();
    private static ReportManager reportManager = new ReportManager(transactionManager);;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Console.printLine("*",74);
        System.out.println("*                    Welcome to Ledgers Application!                     *");
        Console.printLine("*",74);

        while (true) {
            try {
                System.out.println("What do you want to do?");
                System.out.println("    1- Add Deposit");
                System.out.println("    2- Make Payment (Debit)");
                System.out.println("    3- View Ledger");
                System.out.println("    4- Exit");
                System.out.print("Enter command: ");
                int choice = Console.PromptForByte();
                Console.printLine("-");

                if (choice == 1) {
                    addDeposit(scanner);
                } else if (choice == 2) {
                    makePayment(scanner);
                } else if (choice == 3) {
                    viewLedger(scanner);  // Moved View Reports to Ledger section
                } else if (choice == 4) {
                    System.out.print("Exiting"); Console.animation();
                    Console.printLine("-");
                    return;
                } else {
                    System.out.println("Invalid command. Please try again.");
                    Console.printLine("-");
                }
            }catch (Exception e){
                System.out.println("Invalid Command");
            }
        }
    }

    /**
     * Asks user for deposit
     * Asks for date (YYYY-MM-DD)
     *          time (HH:MM:SS)
     *          vendor
     *          amount
     * Then adds the transcation
     */

    private static void addDeposit(Scanner scanner) throws InterruptedException {
        while (true) {
            try {
                System.out.println("Choose transaction type:");
                System.out.println("    1- Recent Transaction");
                System.out.println("    2- Old Transaction");
                System.out.println("    3- Exit");
                System.out.print("Enter your choice: ");

                int choice = Console.PromptForInt();
                Console.printLine("-");

                if (choice == 1) {
                    // Use current date and time
                    LocalDateTime now = LocalDateTime.now();
                    String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                    // Call helper method to handle transaction details
                    handleTransactionInput(date, time, scanner, false); // false for deposit

                } else if (choice == 2) {
                    // Prompt for old transaction details
                    String date = Console.PromptForString("Enter date (YYYY-MM-DD): ");
                    String time = Console.PromptForString("Enter time (HH:MM:SS): ");

                    // Call helper method to handle transaction details
                    handleTransactionInput(date, time, scanner, false); // false for deposit

                } else if (choice == 3) {
                    System.out.print("Exiting to the main menu.");Console.animation();
                    Console.printLine("-");
                    return;

                } else {
                    System.out.println("Invalid choice. Please try again.");
                    Console.printLine("-");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Asks user for payment
     * Asks for date (YYYY-MM-DD)
     *          time (HH:MM:SS)
     *          vendor
     *          amount
     * Then adds the transcation but amount would be negative
     */
    private static void makePayment(Scanner scanner) throws InterruptedException {
        while (true) {
            try {
                System.out.println("Choose transaction type:");
                System.out.println("    1- Recent Payment");
                System.out.println("    2- Old Payment");
                System.out.println("    3- Exit");
                System.out.print("Enter your choice: ");

                int choice = Console.PromptForInt();
                Console.printLine("-");

                if (choice == 1) {
                    // Use current date and time
                    LocalDateTime now = LocalDateTime.now();
                    String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                    // Call helper method to handle transaction details
                    handleTransactionInput(date, time, scanner, true); // true for payment

                } else if (choice == 2) {
                    // Prompt for old payment details
                    String date = Console.PromptForString("Enter date (YYYY-MM-DD): ");
                    String time = Console.PromptForString("Enter time (HH:MM:SS): ");

                    // Call helper method to handle transaction details
                    handleTransactionInput(date, time, scanner, true); // true for payment

                } else if (choice == 3) {
                    System.out.print("Exiting to the main menu.");Console.animation();
                    Console.printLine("-");
                    return;

                } else {
                    System.out.println("Invalid choice. Please try again.");
                    Console.printLine("-");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void handleTransactionInput(String date, String time, Scanner scanner, boolean isPayment) throws InterruptedException {
        try {
            String description = Console.PromptForString("Enter description: ");
            String vendor = Console.PromptForString("Enter vendor: ");
            float amount = Console.PromptForFloat("Enter amount: ");

            // Create transaction
            if (isPayment) {
                transactionManager.addTransaction(new Transaction(date, time, description, vendor, -amount)); // Negative for payment
                Console.Loadinganimation();
                System.out.println("Payment recorded successfully.");
                Console.printLine("-");
            } else {
                transactionManager.addTransaction(new Transaction(date, time, description, vendor, amount)); // Positive for deposit
                Console.Loadinganimation();
                System.out.println("Deposit added successfully.");
                Console.printLine("-");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter a valid number for amount.");
            scanner.nextLine();
        }
    }

    /**
     * Handles viewing the ledger (with sub-options for All, Deposits, Payments, and Reports)
     * A - All
     * D - Deposits
     * P - Payments
     * R -  Reports
     * X - Back to Main Menu
     */
    private static void viewLedger(Scanner scanner) throws InterruptedException {
        while (true) {
            System.out.println("Ledger - What would you like to view?");
            System.out.println("    A) All transactions");
            System.out.println("    D) Deposits transactions");
            System.out.println("    P) Payments transactions");
            System.out.println("    R) Reports");
            System.out.println("    X) Back to Main Menu");
            System.out.print("Enter option: ");
            String option = Console.PromptForString().toUpperCase();
            Console.printLine("-");

            List<Transaction> transactions = transactionManager.getAllTransactions();

            // Sort the transactions by date and time (newest first)
            Collections.sort(transactions, Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());

            try {
                if (option.equals("A")) {
                    Console.Loadinganimation();
                    displayTransactions(transactions);
                    Console.printLine("-");
                } else if (option.equals("D")) {
                    List<Transaction> deposits = transactionManager.getDeposits();
                    Console.Loadinganimation();
                    displayTransactions(deposits);
                    Console.printLine("-");
                } else if (option.equals("P")) {
                    List<Transaction> payments = transactionManager.getPayments();
                    Console.Loadinganimation();
                    displayTransactions(payments);
                    Console.printLine("-");
                } else if (option.equals("R")) {
                    Console.Loadinganimation();
                    viewReports(scanner);
                } else if (option.equals("X")) {
                    System.out.print("Exiting to the main menu.");Console.animation();
                    Console.printLine("-");
                    return;
                } else {
                    Thread.sleep(1000);
                    System.out.println("Invalid option. Please try again.");
                    Console.printLine("-");
                }
            }catch (Exception e){
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Handles displaying a list of transactions with headings
    private static void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            // Print table headings
            System.out.printf("%-15s %-10s %-35s %-25s %-10s%n", "Date", "Time", "Description", "Vendor", "Amount");
            Console.printLine("-");

            // Print each transaction
            for (Transaction t : transactions) {
                System.out.printf("%-15s %-10s %-35s %-25s %10.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }

    // Handles viewing reports
    private static void viewReports(Scanner scanner) throws InterruptedException {
        System.out.println("Select report:");
        System.out.println("    1- Month To Date");
        System.out.println("    2- Previous Month");
        System.out.println("    3- Year To Date");
        System.out.println("    4- Search by Vendor");
        System.out.print("Enter option: ");
        int choice = Console.PromptForInt();
        Console.printLine("-");

        if (choice == 1) {
            Console.Loadinganimation();
            displayReport(reportManager.generateMonthToDateReport());
            Console.printLine("-");
        } else if (choice == 2) {
            Console.Loadinganimation();
            displayReport(reportManager.generatePreviousMonthReport());
            Console.printLine("-");
        } else if (choice == 3) {
            Console.Loadinganimation();
            displayReport(reportManager.generateYearToDateReport());
            Console.printLine("-");
        } else if (choice == 4) {
            String vendor = Console.PromptForString("Enter vendor name: ");
            Console.printLine("-");
            Console.Loadinganimation();
            displayReport(reportManager.searchByVendor(vendor));
            Console.printLine("-");
        } else {
            System.out.println("Invalid choice.");
            Console.printLine("-");
        }
    }

    /**
     * if Report is empty
     *      prints no transaction found
     * else
     *      prints the report
     */
// Handles displaying a report of transactions with headings
    private static void displayReport(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            // Print table headings with more space for "Description"
            System.out.printf("%-15s %-10s %-35s %-25s %-10s%n", "Date", "Time", "Description", "Vendor", "Amount");
            Console.printLine("-");

            // Print each transaction
            for (Transaction t : transactions) {
                System.out.printf("%-15s %-10s %-35s %-25s %10.2f%n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }
        }
    }
    
}
