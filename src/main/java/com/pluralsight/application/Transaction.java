package com.pluralsight.application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String provider;
    private float amount;

    public Transaction(String date, String time, String description, String provider, float amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.provider = provider;
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addToCSV() {
        try {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("./src/main/resources/transactions.csv", true));
            buffWrite.write(String.format("\n%s|%s|%s|%s|%.2f", date, time, description, provider, amount));
            buffWrite.close();
        } catch (IOException e) {
            System.out.println("Could not add transaction :(");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(date).append("|");
        sb.append(time).append("|");
        sb.append(description).append("|");
        sb.append(provider).append("|");
        sb.append(String.format("%.2f", amount));

        return sb.toString();
    }
}
