package com.pluralsight.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {
    //Gets current date and formats it to the year-month-day format
    public static String[] getCurrentDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formattedTime.format(currentTime).split("-");
    }

    //Shows all the beginning of the current calendar month to the current date
    public static void monthToDate(ArrayList<Transaction> ledger) {
        String[] currentParts = getCurrentDate();
        int currentYear = Integer.parseInt(currentParts[0]);
        int currentMonth = Integer.parseInt(currentParts[1]);
        for (Transaction transaction : ledger) {
            String[] transactionParts = transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).split("-");
            int transactionYear = Integer.parseInt(transactionParts[0]);
            int transactionMonth = Integer.parseInt(transactionParts[1]);

            if (transactionMonth == currentMonth && transactionYear == currentYear) {
                System.out.print(transaction);
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
            String[] transactionParts = transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).split("-");
            int transactionYear = Integer.parseInt(transactionParts[0]);
            int transactionMonth = Integer.parseInt(transactionParts[1]);
            //checks if the current month is January  if so check for December of last year
            if (currentMonth == 1) {
                Object[] dates = LocalDate.of(currentYear - 1, 12, 1).datesUntil(LocalDate.of(currentYear - 1, 12, 31)).toArray();
                for (Object date : dates) {
                    if (transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(date.toString())) {
                        System.out.println(transaction);
                    }
                }
                //Checks for the last month of the same year
            } else if ((currentMonth - 1) == transactionMonth && transactionYear == currentYear) {
                System.out.print(transaction);
            }
        }
    }

    public static void yearToDate(ArrayList<Transaction> ledger) {
        int currentYear = Integer.parseInt(getCurrentDate()[0]);

        //Searches from the beginning of the year to current day
        for (Transaction transaction : ledger) {
            int transactionYear = Integer.parseInt(transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).split("-")[0]);
            if (transactionYear == currentYear) {
                System.out.print(transaction);
            }
        }
    }

    public static void previousYear(ArrayList<Transaction> ledger) {
        int currentYear = Integer.parseInt(getCurrentDate()[0]);

        //Searches everything in the previous year
        for (Transaction transaction : ledger) {
            int transactionYear = Integer.parseInt(transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).split("-")[0]);
            if ((currentYear - 1) == transactionYear) {
                System.out.print(transaction);
            }
        }
    }

    public static void searchByVendor(ArrayList<Transaction> ledger, String vendor) {
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
                    break;
                }
                case "5": {
                    System.out.println("Which vendor are you looking for?");
                    String vendor = scan.nextLine();
                    searchByVendor(ledger, vendor);
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
