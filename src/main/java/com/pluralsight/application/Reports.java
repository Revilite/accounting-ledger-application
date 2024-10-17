package com.pluralsight.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {
    //Shows all the beginning of the current calendar month to the current date
    public static void monthToDate(ArrayList<Transaction> ledger) {
        for (Transaction transaction : ledger) {
            if (transaction.getDate().getMonth() == LocalDate.now().getMonth() && transaction.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.print(transaction);
            }
        }
    }

    public static void previousMonth(ArrayList<Transaction> ledger) {
        //Creates and segments current time and date

        //Segments and checks if the transaction was in the last month and in the same year
        for (Transaction transaction : ledger) {
            //checks if the current month is January  if so check for December of last year
            if (LocalDate.now().getMonthValue() == 1) {
                  if(transaction.getDate().getMonthValue() == 12 && LocalDate.now().getYear() - 1 == transaction.getDate().getYear()){
                      System.out.println(transaction);
                  }
                //Checks for the last month of the same year
            } else if ((LocalDate.now().getMonthValue() - 1) == transaction.getDate().getMonthValue() && transaction.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.print(transaction);
            }
        }
    }

    public static void yearToDate(ArrayList<Transaction> ledger) {
        //Searches from the beginning of the year to current day
        for (Transaction transaction : ledger) {
        if (transaction.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.print(transaction);
            }
        }
    }

    public static void previousYear(ArrayList<Transaction> ledger) {
        for (Transaction transaction : ledger) {
            if ((LocalDate.now().getYear() - 1) == transaction.getDate().getYear()) {
                System.out.print(transaction);
            }
        }
    }

    public static void searchByVendor(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which vendor are you looking for? (enter x to exit)");
        String vendor = scan.nextLine();
        if(vendor.toLowerCase().equals("x")){
            return;
        }
        for (Transaction transaction : ledger) {
            if (transaction.getProvider().equalsIgnoreCase(vendor)) {
                System.out.print(transaction);
            }
        }
    }

    public static void showReports(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        boolean reportLoop = true;

        while (reportLoop) {
            System.out.println("""
                        |===============|
                        |    Reports    |
                        |===============|
                    
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
                    break;
                }
                case "5": {
                    searchByVendor(ledger);
                    break;
                }
                case "6": {
                    CustomSearch.searchingScreen(ledger);
                    break;
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
