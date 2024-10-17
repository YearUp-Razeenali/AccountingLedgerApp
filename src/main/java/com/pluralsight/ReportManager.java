package com.pluralsight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {
    private TransactionManager transactionManager; // Instance variable, not static

    // Constructor to inject the TransactionManager
    public ReportManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager; // Use instance variable
    }

    // Generate report for transactions in the current month
    public List<Transaction> generateMonthToDateReport() { // Remove static
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
    public List<Transaction> generatePreviousMonthReport() { // Remove static
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
    public List<Transaction> generateYearToDateReport() { // Remove static
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
    public List<Transaction> searchByVendor(String vendor) { // Remove static
        List<Transaction> vendorTransactions = new ArrayList<>();
        for (Transaction transaction : transactionManager.getAllTransactions()) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                vendorTransactions.add(transaction);
            }
        }
        return vendorTransactions;
    }
}
