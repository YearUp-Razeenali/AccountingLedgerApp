package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {
    private static TransactionManager transactionManager;

    public ReportManager(TransactionManager transactionManager) {
        ReportManager.transactionManager = transactionManager; // Dependency injection of TransactionManager
    }

    // Generate report for transactions in the current month
    public static List<Transaction> generateMonthToDateReport() {
        List<Transaction> monthToDateTransactions = new ArrayList<>();
        LocalDate today = LocalDate.now();
        String currentMonth = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        for (Transaction transaction : transactionManager.getAllTransactions()) {
            if (transaction.getDate().startsWith(currentMonth)) {
                monthToDateTransactions.add(transaction);
            }
        }
        return monthToDateTransactions;
    }

    // Generate report for transactions in the previous month
    public static List<Transaction> generatePreviousMonthReport() {
        List<Transaction> previousMonthTransactions = new ArrayList<>();
        LocalDate today = LocalDate.now().minusMonths(1);
        String previousMonth = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        for (Transaction transaction : transactionManager.getAllTransactions()) {
            if (transaction.getDate().startsWith(previousMonth)) {
                previousMonthTransactions.add(transaction);
            }
        }
        return previousMonthTransactions;
    }

    // Generate report for transactions in the current year
    public static List<Transaction> generateYearToDateReport() {
        List<Transaction> yearToDateTransactions = new ArrayList<>();
        LocalDate today = LocalDate.now();
        String currentYear = today.format(DateTimeFormatter.ofPattern("yyyy"));

        for (Transaction transaction : transactionManager.getAllTransactions()) {
            if (transaction.getDate().startsWith(currentYear)) {
                yearToDateTransactions.add(transaction);
            }
        }
        return yearToDateTransactions;
    }

    // Search transactions by vendor name
    public static List<Transaction> searchByVendor(String vendor) {
        List<Transaction> vendorTransactions = new ArrayList<>();
        for (Transaction transaction : transactionManager.getAllTransactions()) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                vendorTransactions.add(transaction);
            }
        }
        return vendorTransactions;
    }
}
