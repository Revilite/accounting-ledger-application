package com.pluralsight.application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class AddToLedger {

    public static String enterPrompt(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = scan.nextLine().trim();
        if (userInput.toLowerCase().equals("x")) {
            return "";
        }
        return userInput;
    }

    //Same as enterPrompt but returns a formatted float variable
    public static float enterAmount(String prompt) {
        Scanner scan = new Scanner(System.in);

        boolean correctFormat = false;
        while (!correctFormat) {
            try {
                System.out.println(prompt);
                String userInput = scan.nextLine();
                float updatedUserInput = Float.parseFloat(userInput);
                if (userInput.isEmpty()) {
                    return 0.0f;
                } else if (updatedUserInput <= 0) {
                    System.out.println("Please enter a number higher than 0");
                } else {
                    return updatedUserInput;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }

        return 0.0f;
    }

    //Gets called when adding a deposit
    public static void deposit(ArrayList<Transaction> ledger) throws IOException {
        String descriptionPrompt = "Enter the description of the deposit (Enter 'x' to exit )";
        String providerPrompt = "Enter the provider of the deposit (Enter 'x' to exit )";
        String amountPrompt = "Enter the amount of the deposit (Enter 'x' to exit )";
        String description = enterPrompt(descriptionPrompt);
        if (description.isEmpty()) {
            return;
        }
        String provider = enterPrompt(providerPrompt);
        if (provider.isEmpty()) {
            return;
        }
        float amount = enterAmount(amountPrompt);
        if (amount == 0.0f) {
            return;
        }
        Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), description, provider, amount);
        deposit.addToCSV();
        ledger.add(0, deposit);
        System.out.println("Successfully added deposit!");
    }

    //Gets called when adding a payment
    public static void payment(ArrayList<Transaction> ledger) throws IOException {
        String descriptionPrompt = "Enter the description of the deposit (Enter 'x' to exit )";
        String providerPrompt = "Enter the provider of the deposit (Enter 'x' to exit )";
        String amountPrompt = "Enter the amount of the deposit (Enter 'x' to exit )";
        String description = enterPrompt(descriptionPrompt);
        if (description.isEmpty()) {
            return;
        }
        String provider = enterPrompt(providerPrompt);
        if (provider.isEmpty()) {
            return;
        }
        float amount = enterAmount(amountPrompt);
        if (amount == 0.0f) {
            return;
        }
        Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), description, provider, -amount);
        if (payment.getDescription().isEmpty() || payment.getProvider().isEmpty() || payment.getAmount() == 0) {
            System.out.println("Exiting");
            return;
        }
        payment.addToCSV();
        ledger.add(0, payment);
        System.out.println("Successfully added payment!");
    }
}
