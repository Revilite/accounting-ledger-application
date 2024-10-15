package com.pluralsight.application;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowLedger {
    public static void showAll(ArrayList<Transaction> ledger) {
        for (Transaction transaction : ledger) {
            System.out.println(transaction);
        }
    }

    public static void ledgerPage(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        boolean ledgerLoop = true;
        while (ledgerLoop) {
            System.out.println("""
                    
                    Which option would you like to do?
                    
                    Show All       (1)
                    Show Deposits  (2)
                    Show Payments  (3)
                    Show Reports   (4)
                    Go Home        (5)
                    """);
            String ledgerChoice = scan.nextLine();
            switch (ledgerChoice) {
                case "1": {
                    showAll(ledger);
                    break;
                }
                case "5": {
                    ledgerLoop = false;
                    break;
                }
                default: {
                    System.out.println("Please choose one of the options");
                }
            }

        }
    }
}
