package com.pluralsight.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomSearch {
    public static void searchByDescription(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);

        System.out.println("What is the description of transaction?");
        String userInput = scan.nextLine();
        for (Transaction transaction : ledger) {
            if (transaction.getDescription().equalsIgnoreCase(userInput)) {
                System.out.println(transaction);
            }
        }
    }

    public static void searchBySelectedDate(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        String beginningDate = "";
        String endingDate = "";
        boolean correctFormat = false;
        while (!correctFormat) {
            System.out.println("Enter the beginning date (yyyy-mm-dd)");
            beginningDate = scan.nextLine();
            System.out.println("Enter the ending date (yyyy-mm-dd)");
            endingDate = scan.nextLine();
            //Please refer to AddToLedger.enterDate() for explanation on regex
            if (!beginningDate.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$") || !endingDate.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                System.out.println("Incorrect Format");
            } else {
                correctFormat = true;
            }
        }
        Object[] dates = LocalDate.parse(beginningDate).datesUntil(LocalDate.parse(endingDate)).toArray();
        for (Transaction transaction : ledger) {
            for (Object date : dates) {
                if (transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(date.toString())) {
                    System.out.print(transaction);
                }
            }
        }
    }

    public static void searchAmount(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the amount you would like to search for?");
        String amount = scan.nextLine();

        float amountFloat = 0.0f;
        try{
           amountFloat =  Float.parseFloat(amount);
        } catch (NumberFormatException e) {
            System.out.println("That is not a number");
        }

        for(Transaction transaction : ledger){
            if(Math.abs(transaction.getAmount()) <= amountFloat){
                System.out.print(transaction);
            }
        }


    }


    public static void searchingScreen(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        System.out.println("""
                What would you like to search by?
                
                Selected Date   (1)
                Description     (2)
                Amount          (3)
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
            case "0": {
                break;
            }
            default: {
                System.out.println("Please enter one of the options");
            }

        }

    }
}
