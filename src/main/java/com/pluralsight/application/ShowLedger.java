package com.pluralsight.application;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowLedger {
    public static void showAll(ArrayList<Transaction> ledger) {
        for (Transaction transaction : ledger) {
            System.out.print(transaction);
        }
    }

    public static void showDeposits(ArrayList<Transaction> ledger) {
        for (Transaction transaction : ledger) {
            if (transaction.getAmount() > 0) {
                System.out.print(transaction);
            }
        }
    }

    public static void showPayments(ArrayList<Transaction> ledger) {
        for (Transaction transaction : ledger) {
            if (transaction.getAmount() < 0) {
                System.out.print(transaction);
            }
        }
    }

    public static void ledgerPage(ArrayList<Transaction> ledger) {
        Scanner scan = new Scanner(System.in);
        boolean ledgerLoop = true;
        while (ledgerLoop) {
            System.out.println("""
                    
                    Which option would you like to do?
                    
                    Show All       (A)
                    Show Deposits  (D)
                    Show Payments  (P)
                    Show Reports   (R)
                    Go Home        (H)
                    """);
            String ledgerChoice = scan.nextLine().toUpperCase();
            switch (ledgerChoice) {
                case "A": {
                    showAll(ledger);
                    break;
                }
                case "D": {
                    showDeposits(ledger);
                    break;
                }
                case "P": {
                    showPayments(ledger);
                    break;
                }
                case "R": {
                    Reports.showReports(ledger);
                    break;
                }
                case "H": {
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
