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
        String[] tokens = csvLine.split("\\|");
        return new Transaction(tokens[0], tokens[1], tokens[2], tokens[3], Float.parseFloat(tokens[4]));
    }
}
