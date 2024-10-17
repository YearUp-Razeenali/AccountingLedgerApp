package com.pluralsight;

import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class LedgerApp {
    private static TransactionManager transactionManager = new TransactionManager();
    private static ReportManager reportManager = new ReportManager(transactionManager);;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("What do you want to do?");
                System.out.println(" 1- Add Deposit");
                System.out.println(" 2- Make Payment (Debit)");
                System.out.println(" 3- View Ledger");
                System.out.println(" 4- Exit");
                System.out.print("Enter command: ");
                int choice = Console.PromptForByte();

                if (choice == 1) {
                    addDeposit(scanner);
                } else if (choice == 2) {
                    makePayment(scanner);
                } else if (choice == 3) {
                    viewLedger(scanner);  // Moved View Reports to Ledger section
                } else if (choice == 4) {
                    System.out.print("Existing"); animation();
                    return;
                } else {
                    System.out.println("Invalid command. Please try again.");
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

        String date = Console.PromptForString("Enter date (YYYY-MM-DD): ");
        String time = Console.PromptForString("Enter time (HH:MM:SS): ");
        String description = Console.PromptForString("Enter description: ");
        String vendor = Console.PromptForString("Enter vendor: ");
        float amount = Console.PromptForFloat("Enter amount: ");

        transactionManager.addTransaction(new Transaction(date, time, description, vendor, amount));
        Loadinganimation();
        System.out.println("Deposit added successfully.");
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
        String date = Console.PromptForString("Enter date (YYYY-MM-DD): ");
        String time = Console.PromptForString("Enter time (HH:MM:SS): ");
        String description = Console.PromptForString("Enter description: ");
        String vendor = Console.PromptForString("Enter vendor: ");
        float amount = Console.PromptForFloat("Enter amount: ");;

        transactionManager.addTransaction(new Transaction(date, time, description, vendor, -amount));
        Loadinganimation();
        System.out.println("Payment recorded successfully.");
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
            System.out.println(" A) All transactions");
            System.out.println(" D) Deposits transactions");
            System.out.println(" P) Payments transactions");
            System.out.println(" R) Reports");  // Added Reports option here
            System.out.println(" X) Back to Main Menu");
            System.out.print("Enter option: ");
            String option = Console.PromptForString().toUpperCase();

            List<Transaction> transactions = transactionManager.getAllTransactions();

            // Sort the transactions by date and time (newest first)
            Collections.sort(transactions, Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());

            try {
                if (option.equals("A")) {
                    displayTransactions(transactions);
                } else if (option.equals("D")) {
                    List<Transaction> deposits = transactionManager.getDeposits();
                    displayTransactions(deposits);
                } else if (option.equals("P")) {
                    List<Transaction> payments = transactionManager.getPayments();
                    displayTransactions(payments);
                } else if (option.equals("R")) {
                    viewReports(scanner);  // Call viewReports from the ledger screen
                } else if (option.equals("X")) {
                    return;  // Go back to the main menu
                } else {
                    Thread.sleep(1000);
                    System.out.println("Invalid option. Please try again.");
                }
            }catch (Exception e){
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Handles displaying a list of transactions
    private static void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    // Handles viewing reports
    private static void viewReports(Scanner scanner) {
        System.out.println("Select report:");
        System.out.println("1- Month To Date");
        System.out.println("2- Previous Month");
        System.out.println("3- Year To Date");
        System.out.println("4- Search by Vendor");
        int choice = Console.PromptForInt();

        if (choice == 1) {
            displayReport(reportManager.generateMonthToDateReport());
        } else if (choice == 2) {
            displayReport(reportManager.generatePreviousMonthReport());
        } else if (choice == 3) {
            displayReport(reportManager.generateYearToDateReport());
        } else if (choice == 4) {
            System.out.print("Enter vendor name: ");
            String vendor = scanner.next();
            displayReport(reportManager.searchByVendor(vendor));
        } else {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * if Report is empty
     *      prints no transaction found
     * else
     *      prints the report
     */
    private static void displayReport(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    private static void animation() throws InterruptedException {
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".\n");
        Thread.sleep(500);
    }

    private static void Loadinganimation() throws InterruptedException {
        System.out.print("Loading");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".\n");
        Thread.sleep(500);
    }
}
