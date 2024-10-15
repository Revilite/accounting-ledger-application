package com.pluralsight.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainScreen {

    public static void fillLedger(ArrayList<Transaction> ledger) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader("./src/main/resources/transactions.csv"));
        //Skips heading line
        String input = buffRead.readLine();
        while ((input = buffRead.readLine()) != null) {
            String[] chunks = input.split("[|]");
            Transaction transaction = new Transaction(chunks[0], chunks[1], chunks[2], chunks[3], Float.parseFloat(chunks[4]));
            ledger.add(transaction);
        }
    }

    public static void sortLedger(ArrayList<Transaction> ledger) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader("./src/main/resources/transactions.csv"));
        ledger.clear();

        //Skips the heading
        String input = buffRead.readLine();
        //Adds the first line
        String[] values = buffRead.readLine().split("[|]");
        ledger.add(new Transaction(values[0], values[1], values[2], values[3], Float.parseFloat(values[4])));

        while ((input = buffRead.readLine()) != null) {
            String[] lineChunks = input.split("[|]")[0].split("-");
            int lineDate = Integer.parseInt(lineChunks[0] + lineChunks[1] + lineChunks[2]);
            int index = 0;
            values = input.split("[|]");
            for (int i = 0; i < ledger.size(); i++) {
                String[] ledgerChunks = ledger.get(ledger.size() - 1).getDate().split("-");
                int ledgerDate = Integer.parseInt(ledgerChunks[0] + ledgerChunks[1] + ledgerChunks[2]);
                if (lineDate <= ledgerDate) {
                    index = i;

                }
            }
            ledger.add(index, new Transaction(values[0], values[1], values[2], values[3], Float.parseFloat(values[4])));
        }
        System.out.println(ledger);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Transaction> ledger = new ArrayList<>();
        try {
            fillLedger(ledger);
            sortLedger(ledger);
        } catch (IOException e) {
            System.out.println("Could not fill ledger :(");
        }


        System.out.println("""
                ================
                    Welcome!
                ================
                """);
        boolean mainLoop = true;

        while (mainLoop) {
            System.out.println("""
                    Which action would you like to  do?
                    
                    Add Deposit         (D)
                    Make a Payment      (P)
                    Ledger              (L)
                    Exit                (X)
                    """);
            String mainChoice = scan.nextLine().toUpperCase();

            switch (mainChoice) {
                case "D": {
                    try {
                        AddToLedger.deposit(ledger);
                    } catch (IOException e) {
                        System.out.println("Unable to deposit information :(");
                    }
                    break;
                }
                case "P": {
                    try {
                        AddToLedger.payment(ledger);
                    } catch (IOException e) {
                        System.out.println("Unable to add payment :(");
                    }
                    break;
                }
                case "L": {
                    ShowLedger.ledgerPage(ledger);
                    break;
                }
                case "X": {
                    mainLoop = false;
                    break;
                }
                default: {
                    System.out.println("Please choose one of the options :)");
                    break;
                }
            }

        }

    }

}
