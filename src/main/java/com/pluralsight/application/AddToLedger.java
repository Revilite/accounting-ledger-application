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
        return scan.nextLine();
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
                if (updatedUserInput <= 0) {
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

        String description = "Enter the description of the deposit";
        String provider = "Enter the provider of the deposit";
        String amount = "Enter the amount of the deposit";
        Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), enterPrompt(description), enterPrompt(provider), enterAmount(amount));
        deposit.addToCSV();
        ledger.add(deposit);
    }

    //Gets called when adding a payment
    public static void payment(ArrayList<Transaction> ledger) throws IOException {
        String description = "Enter the description of the payment";
        String provider = "Enter the provider of the payment";
        String amount = "Enter the amount of the payment (do not put '-')";
        Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), enterPrompt(description), enterPrompt(provider), -enterAmount(amount));
        payment.addToCSV();
        ledger.add(payment);
    }
}
