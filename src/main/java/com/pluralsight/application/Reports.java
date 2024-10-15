package com.pluralsight.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {
    public static String[] getCurrentDate() {
        LocalDateTime currntTime = LocalDateTime.now();
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formattedTime.format(currntTime).split("-");
    }


    public static void monthToDate(ArrayList<Transaction> ledger) {
        String[] currentParts = getCurrentDate();
        int currentYear = Integer.parseInt(currentParts[0]);
        int currentMonth = Integer.parseInt(currentParts[1]);
        for (Transaction transaction : ledger) {
            String[] transactionParts = transaction.getDate().split("-");
            int transactionYear = Integer.parseInt(transactionParts[0]);
            int transactionMonth = Integer.parseInt(transactionParts[1]);

            if (transactionMonth == currentMonth && transactionYear == currentYear) {
                System.out.println(transaction);
            }
        }
    }

    public static void previousMonth(ArrayList<Transaction> ledger) {
        //Creates and segments current time and date
        String[] currentParts = getCurrentDate();
        int currentYear = Integer.parseInt(currentParts[0]);
        int currentMonth = Integer.parseInt(currentParts[1]);

        //Segments and checks if the transaction was in the last month and in the same year
        for (Transaction transaction : ledger) {
            String[] transactionParts = transaction.getDate().split("-");
            int transactionYear = Integer.parseInt(transactionParts[0]);
            int transactionMonth = Integer.parseInt(transactionParts[1]);

            if ((currentMonth - 1) == transactionMonth && transactionYear == currentYear) {
                System.out.println(transaction);
            }
        }
    }

    public static void yearToDate(ArrayList<Transaction> ledger) {
        int currentYear = Integer.parseInt(getCurrentDate()[0]);

        for (Transaction transaction : ledger) {
            int transactionYear = Integer.parseInt(transaction.getDate().split("-")[0]);
            if (transactionYear == currentYear) {
                System.out.println(transaction);
            }
        }
    }

    public static void previousYear(ArrayList<Transaction> ledger) {
        int currentYear = Integer.parseInt(getCurrentDate()[0]);
        for(Transaction transaction : ledger){
            int transactionYear = Integer.parseInt(transaction.getDate().split("-")[0]);
            if((currentYear - 1) == transactionYear){
                System.out.println(transaction);
            }
        }
    }

    public static void searchByVendor(ArrayList<Transaction> ledger, String vendor){
        for(Transaction transaction : ledger){
            if(transaction.getProvider().equalsIgnoreCase(vendor)){
                System.out.println(transaction);
            }
        }

    }

    public static void showReports(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        boolean reportLoop = true;

        while (reportLoop) {
            System.out.println("""
                    How would you like to search?
                    
                    Month to Date       (1)
                    Previous Month      (2)
                    Year To Date        (3)
                    Previous Year       (4)
                    Search by Vendor    (5)
                    Custom Search       (6)
                    Go Back             (0)
                    """);
            String reportChoice = scan.nextLine();
            switch (reportChoice) {
                case "1": {
                    monthToDate(ledger);
                    break;
                }
                case "2": {
                    previousMonth(ledger);
                    break;
                }
                case "3": {
                    yearToDate(ledger);
                    break;
                }
                case "4": {
                    previousYear(ledger);
                }
                case "5": {
                    System.out.println("Which vendor are you looking for?");
                    String vendor = scan.nextLine();
                    searchByVendor(ledger, vendor);
                }
                case "6": {
                    CustomSearch.searchingScreen(ledger);
                }
                case "0": {
                    reportLoop = false;
                    break;
                }
                default: {
                    System.out.println("Please choose one of the options");
                    break;
                }
            }

        }
    }
}
