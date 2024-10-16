package com.pluralsight.application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String provider;
    private float amount;

    public Transaction(LocalDate date, LocalTime time, String description, String provider, float amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.provider = provider;
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public String getProvider() {
        return provider;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
    //Adds the all elements to the transactions CSV file
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder(dtf.format(date)).append("|");
        sb.append(time).append("|");
        sb.append(description).append("|");
        sb.append(provider).append("|");
        sb.append(String.format("%.2f\n", amount));

        return sb.toString();
    }
}
