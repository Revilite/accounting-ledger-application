package com.pluralsight.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        int beginningYear = Integer.parseInt(beginningDate.split("-")[0]);
        int beginningMonth = Integer.parseInt(beginningDate.split("-")[1]);
        int beginningDay = Integer.parseInt(beginningDate.split("-")[2]);

        int endingYear = Integer.parseInt(endingDate.split("-")[0]);
        int endingMonth = Integer.parseInt(endingDate.split("-")[1]);
        int endingDay = Integer.parseInt(endingDate.split("-")[2]);

        for (Transaction transaction : ledger) {
            int transactionYear = Integer.parseInt(transaction.getDate().split("-")[0]);
            int transactionMonth = Integer.parseInt(transaction.getDate().split("-")[1]);
            int transactionDay = Integer.parseInt(transaction.getDate().split("-")[2]);


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
