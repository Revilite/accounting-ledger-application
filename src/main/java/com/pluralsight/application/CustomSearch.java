package com.pluralsight.application;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

//Implement Predicate

public class CustomSearch {

    public static void searchBySelectedDate(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        LocalDate beginningDate = LocalDate.now();
        LocalDate endingDate = LocalDate.now();
        boolean correctFormat = false;
        //Collects dates from user and regex checks if it's a valid date
        while (!correctFormat) {
            System.out.println("Enter the beginning date (yyyy-mm-dd) (enter 'x' to exit)");
            String userBeginningDate = scan.nextLine();
            if (userBeginningDate.toLowerCase().equals("x")) {
                return;
            }
            System.out.println("Enter the ending date (yyyy-mm-dd) (enter 'x' to exit");
            String userEndingDate = scan.nextLine();
            if (userEndingDate.toLowerCase().equals("x")) {
                return;
            }
            try {
                beginningDate = LocalDate.parse(userBeginningDate);
                endingDate = LocalDate.parse(userEndingDate);
                correctFormat = true;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect format");
            }
        }
        //Searches dates (exclusive) and if beginning and ending date  are the same look for only one date
        for (Transaction transaction : ledger) {
            if (transaction.getDate().isAfter(beginningDate) && transaction.getDate().isBefore(endingDate)) {
                System.out.print(transaction);
            } else if (beginningDate.equals(endingDate)) {
                if (transaction.getDate().equals(beginningDate)) {
                    System.out.print(transaction);
                }
            }
        }

    }


    public static void searchByDescription(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);

        System.out.println("What is the description of transaction? (enter 'x' to exit");
        String userInput = scan.nextLine();
        if (userInput.toLowerCase().equals("x")) {
            return;
        }
        for (Transaction transaction : ledger) {
            if (transaction.getDescription().equalsIgnoreCase(userInput)) {
                System.out.print(transaction);
            }
        }
    }

    public static void searchAmount(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the amount you would like to search for? (enter 'x' to exit");
        String amount = scan.nextLine();
        if (amount.toLowerCase().equals("x")) {
            return;
        }

        //Checks to see if user input is a float
        float amountFloat = 0.0f;
        try {
            amountFloat = Float.parseFloat(amount);
        } catch (NumberFormatException e) {
            System.out.println("That is not a number");
        }

        //searches transactions for an amount smaller than the user input
        for (Transaction transaction : ledger) {
            if (Math.abs(transaction.getAmount()) <= amountFloat) {
                System.out.print(transaction);
            }
        }
    }


    public static String prompt(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = scan.nextLine();

        if (userInput.toLowerCase().equals("x")) {
            return "";
        }
        if (userInput.isEmpty()) {
            return null;
        }
        return userInput;
    }


    public static void searchByMultipleValues(ArrayList<Transaction> ledger) {
        String startDate = prompt("What is the start date? (enter 'x' to exit");
        if (startDate != null && startDate.isEmpty()) {
            return;
        }
        String endDate = prompt("What is the end date? (enter 'x' to exit");
        if (endDate != null && endDate.isEmpty()) {
            return;
        }
        String description = prompt("What is the description? (enter 'x' to exit");
        if (description != null && description.isEmpty()) {
            return;
        }
        String vendor = prompt("What is the vendor? (enter 'x' to exit");
        if (vendor != null && vendor.isEmpty()) {
            return;
        }

        boolean notFloat = true;
        float amountFloat = 0.0f;
        while (notFloat) {
            String amount = prompt("What is the amount? (enter 'x' to exit");
            if (amount == null) {
                amountFloat = 0.0f;
                break;
            }
            try {
                amountFloat = Float.parseFloat(amount);
                notFloat = false;
            } catch (NumberFormatException e) {
                System.out.println("That is not a number");
            }
        }
        //Variable has to be final
        final float finalAmount = amountFloat;
        //Pure Magic
        ledger.stream().filter(t -> startDate == null || t.getDate().isAfter(LocalDate.parse(startDate)))
                .filter(t -> endDate == null || t.getDate().isBefore(LocalDate.parse(endDate)))
                .filter(t -> description == null || t.getDescription().contains(description))
                .filter(t -> vendor == null || t.getProvider().contains(vendor))
                .filter(t -> finalAmount == 0.0f || t.getAmount() <= finalAmount)
                .forEach(t -> System.out.print(t));

    }


    public static void searchingScreen(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        System.out.println("""
                    |======================|
                    |    Custom Search     |
                    |======================|
                
                Selected Date   (1)
                Description     (2)
                Amount          (3)
                Multiple        (4)
                Go Back         (0)
                """);
        String searchingChoice = scan.nextLine();
        switch (searchingChoice) {
            case "1": {
                searchBySelectedDate(ledger);
                break;
            }
            case "2": {
                searchByDescription(ledger);
                break;
            }
            case "3": {
                searchAmount(ledger);
                break;
            }
            case "4": {
                searchByMultipleValues(ledger);
                break;
            }
            case "0": {
                break;
            }
            default: {
                System.out.println("Please enter one of the options");
            }

        }

    }
}
