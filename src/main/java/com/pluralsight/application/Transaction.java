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
    public LocalTime getTime() {
        return time;
    }

    //Adds the all elements to the transactions CSV file
    public void addToCSV() {
        //Used to format time, date does not need it
        DateTimeFormatter formattedCurrentTime = DateTimeFormatter.ofPattern("hh:mm:ss");
        try {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("./src/main/resources/transactions.csv", true));
            buffWrite.write(String.format("\n%s|%s|%s|%s|%.2f", date, formattedCurrentTime.format(time), description, provider, amount));
            buffWrite.close();
        } catch (IOException e) {
            System.out.println("Could not add transaction :(");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formattedCurrentDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formattedCurrentTime = DateTimeFormatter.ofPattern("hh:mm:ss");

        StringBuilder sb = new StringBuilder(formattedCurrentDate.format(date)).append("|");
        sb.append(formattedCurrentTime.format(time)).append("|");
        sb.append(description).append("|");
        sb.append(provider).append("|");

        //Print in red if the amount is negative
        if (amount < 0) {
            sb.append(String.format("\u001B[31m%.2f\u001B[0m\n", amount));
        }
        else{
            sb.append(String.format("\u001B[32m%.2f\u001B[0m\n", amount));
        }
        return sb.toString();
    }
}
