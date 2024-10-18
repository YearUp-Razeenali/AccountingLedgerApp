package com.pluralsight;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private float amount;

    public Transaction(String date, String time, String description, String vendor, float amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }

    public static Transaction fromString(String csvLine) {
        try {
            String[] tokens = csvLine.split("\\|");
            if (tokens.length < 5) {
                throw new IllegalArgumentException("Invalid transaction data: " + csvLine);
            }

            String date = tokens[0];
            String time = tokens[1];
            String description = tokens[2];
            String vendor = tokens[3];
            float amount = Float.parseFloat(tokens[4]);

            return new Transaction(date, time, description, vendor, amount);
        } catch (Exception e) {
            System.err.println("Error parsing transaction: " + csvLine + " - " + e.getMessage());
            return null; // Or handle the error accordingly
        }
    }
}
