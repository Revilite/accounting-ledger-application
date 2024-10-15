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

    public static String enterDate(String prompt) {
        Scanner scan = new Scanner(System.in);
        boolean correctFormat = false;
        while (!correctFormat) {
            System.out.println(prompt);
            String userInput = scan.nextLine();
            /*
                How this regex works:
                ^ beginning container
                $ ending container
                d{4}  checks for a length of 4 digits of any number 0-9 (year)
                (0[1-9]|1[012]) check if the first digit is ether 0 or 1
                                if the first digit is 0 then check if the second digit is 1-9
                                if the digit is 1 then check if the second digit is either 0, 1, or 2
                                (month)
                (0[1-9]|[12][0-9]|3[01]) checks if the first digit is either a 0, 1, 2, or 3
                                         if the first digit is 0 then check if the second digit is either 0-9
                                         if the first digit is either 1 or 2 then check if the second digit is 1-9
                                         if the first digit is 3 then check if the second digit is either 0 or 1
                                         (day)
                \\- checks for '-' symbol


             */
            if (userInput.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                return userInput;
            } else {
                System.out.println("Incorrect Input");
            }
        }
        return "";
    }

    public static String enterTime(String prompt) {
        Scanner scan = new Scanner(System.in);
        boolean correctFormat = false;
        while (!correctFormat) {
            System.out.println(prompt);
            String userInput = scan.nextLine();
            /*
            I made this :)
            How this regex works:
            ^ Starting container
            $ ending container
            ([01][0-9]|2[0-4]) checks if the first digit is either 0, 1, or 2
                               if the first digit is either 0 or 1 check if the second digit is either 0-9
                               if the first digit is 2 then check if the second digit is either 0-4
                               (Hour)
            ([0-5][0-9]) checks if the first digit is either 0-5
                         if the first digit is 0-5 then check if the second digit is 0-9
                         (Minute and second)
            //: checks for ':'
             */

            if (userInput.matches("^([01][0-9]|2[0-4])\\:([0-5][0-9])\\:([0-5][0-9])$")) {
                return userInput;
            } else {
                System.out.println("Incorrect Format");
            }
        }

        return "";
    }

    public static float enterAmount(String prompt) {
        Scanner scan = new Scanner(System.in);


        boolean correctFormat = false;
        while (!correctFormat) {
            try {
                System.out.println(prompt);
                String userInput = scan.nextLine();
                float updatedUserInput = Float.parseFloat(userInput);
                return updatedUserInput;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }

        return 0.0f;
    }


    public static void deposit(ArrayList<Transaction> ledger) throws IOException {
        String date = "Enter the date of the deposit (yyyy-mm-dd)";
        String time = "Enter the time of the deposit (hh:mm:ss)";
        String description = "Enter the description of the deposit";
        String provider = "Enter the provider of the deposit";
        String amount = "Enter the amount of the deposit";
        Transaction deposit = new Transaction(enterDate(date), enterTime(time), enterPrompt(description), enterPrompt(provider), enterAmount(amount));
        deposit.addToCSV();
        ledger.add(deposit);
    }

    public static void payment(ArrayList<Transaction> ledger) throws IOException {
        String date = "Enter the date of the deposit (yyyy-mm-dd)";
        String time = "Enter the time of the deposit (hh:mm:ss)";
        String description = "Enter the description of the deposit";
        String provider = "Enter the provider of the deposit";
        String amount = "Enter the amount of the deposit (do not put '-')";
        Transaction payment = new Transaction(enterDate(date), enterTime(time), enterPrompt(description), enterPrompt(provider), -enterAmount(amount));
        payment.addToCSV();
        ledger.add(payment);
    }
}
