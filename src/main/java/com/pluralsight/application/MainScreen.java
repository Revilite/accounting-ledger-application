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
        System.out.println(ledger);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Transaction> ledger = new ArrayList<>();
        try {
            fillLedger(ledger);
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
                    
                    Add Deposit         (1)
                    Make a Payment      (2)
                    Ledger              (3)
                    Exit                (4)
                    """);
            String mainChoice = scan.nextLine();

            switch (mainChoice) {
                case "1": {
                    try {
                        AddToLedger.deposit(ledger);
                    } catch (IOException e) {
                        System.out.println("Unable to deposit information :(");
                    }
                    break;
                }
                case "2": {
                    try {
                        AddToLedger.payment(ledger);
                    } catch (IOException e) {
                        System.out.println("Unable to add payment :(");
                    }
                    break;
                }
                case "3": {
                    ShowLedger.ledgerPage(ledger);
                    break;
                }
                case "4": {
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
