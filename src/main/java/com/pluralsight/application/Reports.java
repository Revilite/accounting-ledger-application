package com.pluralsight.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {
    public static void monthToDate(ArrayList<Transaction> ledger) {
        //Creates and segments current time and date
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formattedTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] currentParts = formattedTime.format(currentTime).split("-");
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
                    Go Back             (0)
                    """);
            String reportChoice = scan.nextLine();
            switch (reportChoice) {
                case "1": {
                    monthToDate(ledger);
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
