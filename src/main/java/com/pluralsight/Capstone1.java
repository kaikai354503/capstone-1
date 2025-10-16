//import files
package com.pluralsight;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Capstone1 {
    static double total = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        homeScreen();

    }



    //create home screen

    public static void homeScreen(){
        //Loop to repeat promt and cycle switch
        while (true) {

            System.out.println("\n----Finance Tracker----\n");
            System.out.println("Please enter a character to make your selection.");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            //scanner to take input from user
            String choice = scanner.nextLine().trim().toUpperCase();

            //switch for interpreting the users choice.
            switch (choice){
                case "D": makeDeposit();
                    break;
                case "P": makePayment();
                    break;
                case "L": displayLedger();
                    break;
                case "X":
                    System.out.println("Thank you for using my fanance tracker!");
                    return;

                default:
                        System.out.println( "Invalid option. Try again.");
            }
        }
    }
    public static void makeDeposit(){
        //function to make deposits
        FileManager fileManager = new FileManager();

        System.out.println("----Make a Deposit----");
        Transaction transaction = getTransactionDetails();
        if(transaction!=null){
            fileManager.saveTransaction(transaction);
            System.out.println("Deposit sucessful");
        }
    }

    //fn for making a payment
    public static void makePayment(){
        System.out.println("Make a payment");

        FileManager fileManager = new FileManager();
        Transaction transaction = getTransactionDetails();
        if(transaction != null){
            Transaction payment = new Transaction(
                 transaction.getDate(),
                 transaction.getTime(),
                 transaction.getDescription(),
                 transaction.getVendor(),
                 -Math.abs(transaction.getAmount())
            );
            fileManager.saveTransaction(payment);
            System.out.println("Payment successful");
        }
    }

    public static Transaction getTransactionDetails(){

        String date = getCurrentDate();
        String time = getCurrentTime();

        System.out.println("Enter description: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()){
            System.out.println("Description cannot be empty.");
            return null;
        }
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine().trim();
        if (vendor.isEmpty()){
            System.out.println("Vendor cannot be empty.");
            return null;
        }
        double amount = getValidatedAmount();
        if(amount <= 0){
            System.out.println("Ammount must be a positive value.");
            return null;
        }

        return new Transaction(date,time,description,vendor,amount);
    }

    public static double getValidatedAmount(){
        while (true){
            System.out.println("Enter amount: ");
            try{
                return Double.parseDouble(scanner.nextLine().trim());
            }
            catch (NumberFormatException e){
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }
    public Capstone1() throws IOException {
    }

    public static String getCurrentDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getCurrentTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    //function for ledger
    public static void displayLedger(){
        while (true){
            System.out.println("Please make a selection");
            System.out.println("\nLedger\n");
            System.out.println("A) Display all entries");
            System.out.println("D) Display deposits");
            System.out.println("P) Display payments");
            System.out.println("R) Run reports or custom search");
            System.out.println("H) Home");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice){
                case "A":
                    displayAllEntries();
                    break;
                case "D":
                    break;
                case "P":
                    break;
                case "R":
                    break;
                case "H":
                    return;
                default:
                    System.out.println("Invalid entry, please try again.");

                }

            }


        }

        public static void displayAllEntries(){
            System.out.println("All entries from newest to oldest.");
            ArrayList<Transaction> transactions = FileManager.loadTransactions();
            displayTransactionList(transactions);
        }

    public static void displayTransactionList(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()){
            System.out.println("No transactions");
            return;
        }
        System.out.printf("Date","Time","Descriptoin","Vendor","Amount");
        System.out.println("-------------------------------------------");
        for (Transaction transaction : transactions){
            System.out.println(transaction);
            total += transaction.getAmount();
        }

        System.out.println("total: " + total + "\n");


    }
}
