package com.pluralsight.application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String provider;
    private double amount;

    public Transaction(String date, String time, String description, String provider, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.provider = provider;
        this.amount = amount;
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
        return "Transaction{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", provider='" + provider + '\'' +
                ", amount=" + amount +
                '}';
    }
}
