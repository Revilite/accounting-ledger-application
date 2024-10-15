package com.pluralsight.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddToLedger {
    public static String enterPrompt(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        return scan.nextLine();
    }

    public static void deposit(ArrayList<Transaction> ledger) throws IOException {
        String date = "Enter the date of the deposit (yyyy-mm-dd)";
        String time = "Enter the time of the deposit (hh:mm:ss)";
        String description = "Enter the description of the deposit";
        String provider = "Enter the provider of the deposit";
        String amount = "Enter the amount of the deposit";
        Transaction deposit = new Transaction(enterPrompt(date), enterPrompt(time), enterPrompt(description), enterPrompt(provider), Float.parseFloat(enterPrompt(amount)));
        deposit.addToCSV();
        ledger.add(deposit);
    }

    public static void payment(ArrayList<Transaction> ledger) throws IOException {
        String date = "Enter the date of the deposit (yyyy-mm-dd)";
        String time = "Enter the time of the deposit (hh:mm:ss)";
        String description = "Enter the description of the deposit";
        String provider = "Enter the provider of the deposit";
        String amount = "Enter the amount of the deposit (do not put '-')";
        Transaction payment = new Transaction(enterPrompt(date), enterPrompt(time), enterPrompt(description), enterPrompt(provider), -Float.parseFloat(enterPrompt(amount)));
        payment.addToCSV();
        ledger.add(payment);
    }
}
