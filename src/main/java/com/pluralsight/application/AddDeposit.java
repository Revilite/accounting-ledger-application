package com.pluralsight.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddDeposit {
    public static void deposit(ArrayList<Transaction> ledger) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the date of the deposit (yyyy-mm-dd)");
        String date = scan.nextLine();
        System.out.println("Enter the time of the deposit (hh:mm:dd)");
        String time = scan.nextLine();
        System.out.println("Enter the description of the deposit");
        String description = scan.nextLine();
        System.out.println("Enter the provider of the deposit");
        String provider = scan.nextLine();
        System.out.println("Enter the amount of the deposit");
        float amount = scan.nextFloat();

        Transaction deposit = new Transaction(date, time, description, provider, amount);
        deposit.addToCSV();
        ledger.add(deposit);


    }
}
