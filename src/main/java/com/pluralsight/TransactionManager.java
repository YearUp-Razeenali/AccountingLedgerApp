package com.pluralsight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private static final String dataFileName = "transactions.csv"; // Define the CSV file name
    private List<Transaction> transactions;

    public TransactionManager() {
        this.transactions = loadTransactions(); // Load transactions when the manager is created
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction); // Add the new transaction to the list
        saveTransactions(); // Save updated transactions back to the CSV
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    private List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(dataFileName);

        // Check if the file exists; if not, create it
        if (!file.exists()) {
            try {
                file.createNewFile(); // Create a new transactions.csv file
                System.out.println("Created new transactions file: " + dataFileName);
            } catch (IOException e) {
                System.out.println("Error creating transactions file");
                e.printStackTrace();
            }
            return transactions; // Return an empty list since no transactions were loaded
        }

        // If the file exists, read transactions from it
        try (BufferedReader br = new BufferedReader(new FileReader(dataFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                transactions.add(Transaction.fromString(line)); // Parse and add transactions
            }
        } catch (Exception e) {
            System.out.println("Error reading transactions file");
            e.printStackTrace();
        }
        return transactions; // Return the list of loaded transactions
    }

    public void saveTransactions() {
        try (FileWriter writer = new FileWriter(dataFileName)) {
            for (Transaction t : transactions) {
                writer.write(t.toString() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error saving transactions");
            e.printStackTrace();
        }
    }

    public List<Transaction> getDeposits() {
        List<Transaction> deposits = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                deposits.add(t);
            }
        }
        return deposits;
    }

    public List<Transaction> getPayments() {
        List<Transaction> payments = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                payments.add(t);
            }
        }
        return payments;
    }
}
