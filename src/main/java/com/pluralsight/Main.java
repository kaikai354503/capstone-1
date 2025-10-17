//import files
package com.pluralsight;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static double total = 0;
    //Scanner for user inpu and an ArrayList to store loaded transactions.
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Transaction> transactions = FileManager.loadTransactions();

    public static void main(String[] args) {
        homeScreen();

    }



    //create home screen

    public static void homeScreen(){
        //Loop to repeat prompt and cycle switch
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
                    System.out.println("Thank you for using my fnance tracker!");
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
        //Gets transaction details and creates a transaction object
        Transaction transaction = getTransactionDetails();
        if(transaction!=null){
            //saves amount to CSV file
            fileManager.saveTransaction(transaction);
            System.out.println("Deposit sucessful");
        }
    }

    //fn for making a payment
    public static void makePayment(){
        System.out.println("----Make a payment----");

        FileManager fileManager = new FileManager();
        //gets transaction details from user
        Transaction transaction = getTransactionDetails();
        if(transaction != null){
            //creates a negative transaction
            Transaction payment = new Transaction(
                 transaction.getDate(),
                 transaction.getTime(),
                 transaction.getDescription(),
                 transaction.getVendor(),
                 //converts the positive input to negative
                 -Math.abs(transaction.getAmount())
            );
            fileManager.saveTransaction(payment);
            System.out.println("Payment successful");
        }
    }
    //Method to collect transaction details
    public static Transaction getTransactionDetails(){
        //gets current date and time
        String date = getCurrentDate();
        String time = getCurrentTime();

        //makes sure description is not empty
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
        //validates that the amount is positive
        double amount = getValidatedAmount();
        if(amount <= 0){
            System.out.println("Ammount must be a positive value.");
            return null;
        }
        //returns new transaction object
        return new Transaction(date,time,description,vendor,amount);
    }
    // alows input for amount and checks if input is a valid number and type
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

    // gets current date and converts it to date format
    public static String getCurrentDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    //gets current time and converts format
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
                    displayDeposits(transactions);
                    break;
                case "P":
                    displayPayments(transactions);
                    break;
                case "R":
                    displayReports();
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
            System.out.println("----------------------------------------------------------\n");
        }

    //method to display any list of transactions in CSV format
    public static void displayTransactionList(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions");
            return;
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("\nDate|Time|Description|Vendor|Amount\n");
        //loops through each transaction
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toCSV());
            total += transaction.getAmount();
        }

        System.out.println("total: " + total + "\n");


    }

    public static void displayDeposits (ArrayList<Transaction> transactions) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Date|Time|Description|Vendor|Amount\n");

        for (Transaction transaction: transactions) {
            if (transaction.getAmount() > 0){ //filter to make sure input is positive
                System.out.println(transaction.toCSV());


            }
        }
        System.out.println("----------------------------------------------------------\n");



    }
    public static void displayPayments (ArrayList<Transaction> transactions) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Date|Time|Description|Vendor|Amount\n");
        for (Transaction transaction: transactions) {
            if (transaction.getAmount() < 0){// filter to make sure payment is negative
                System.out.println(transaction.toCSV());

            }
        }
        System.out.println("----------------------------------------------------------\n");



    }

    public static void displayReports (){
        System.out.println("Display reports");
        System.out.println("1) Month to date");
        System.out.println("2) Previous month");
        System.out.println("3) Year to date");
        System.out.println("4) Previous year");
        System.out.println("5) Search by vendor");
        System.out.println("0) Go back to ledger");

        String choice = scanner.nextLine().trim();

        switch (choice){
            case "1":
                displayMonthToDate(transactions);
                break;
            case "2":
                displayPreviousMonth(transactions);
                break;
            case "3":
                displayYearToDate(transactions);
                break;
            case "4":
                displayPreviousYear(transactions);
                break;
            case "5":
                searchByVendor(transactions);
                break;
            case "0":
                return;

        }
    }
    //filters and displays transactions for current month
    public static void displayMonthToDate (ArrayList<Transaction> transactions) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Date|Time|Description|Vendor|Amount\n");

        //get current date and extract YearMonth
        LocalDate today = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(today);

        for (Transaction transaction: transactions) {

            LocalDate tranDate = LocalDate.parse(transaction.getDate());
            //Parse transaction date and compare YearMonth with current month
            if (YearMonth.from(tranDate).equals(currentMonth))
            {
                System.out.println(transaction.toCSV());

            }

        }



    }

    public static void displayPreviousMonth(ArrayList<Transaction> transactions) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Date|Time|Description|Vendor|Amount\n");

        LocalDate today = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(today);
        //calculate for previous month
        YearMonth previousMonth = currentMonth.minusMonths(1);
        for (Transaction transaction: transactions) {

            LocalDate tranDate = LocalDate.parse(transaction.getDate());
            //filters previous month
            if (YearMonth.from(tranDate).equals(previousMonth))
            {
                System.out.println(transaction.toCSV());

            }
        }

    }

    public static void displayYearToDate(ArrayList<Transaction> transactions) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Date|Time|Description|Vendor|Amount\n");

        LocalDate today = LocalDate.now();
        Year currentYear = Year.from(today);//extracts current year
        for (Transaction transaction: transactions) {

            LocalDate tranDate = LocalDate.parse(transaction.getDate());
            if (Year.from(tranDate).equals(currentYear))//filter for current year
            {
                System.out.println(transaction.toCSV());

            }
        }

    }

    public static void displayPreviousYear(ArrayList<Transaction> transactions) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Date|Time|Description|Vendor|Amount\n");

        LocalDate today = LocalDate.now();
        Year currentYear = Year.from(today);

        Year previousYear = currentYear.minusYears(1);
        for (Transaction transaction: transactions) {

            LocalDate tranDate = LocalDate.parse(transaction.getDate());
            if (Year.from(tranDate).equals(previousYear))
            {
                System.out.println(transaction.toCSV());

            }
        }

    }
    //vendor search
    private static void searchByVendor(ArrayList<Transaction> transactions) {
        System.out.println("\nEnter vendor name: ");
        String vendor = scanner.nextLine().trim();
        System.out.println("\nVendor search results: " + vendor);
        System.out.println("----------------------------------------------------------\n");
        System.out.println("Date|Time|Description|Vendor|Amount\n");



        boolean found = false;

        for (Transaction transaction : transactions){
            //converts input and vendor to lowercase and checks for containment
            if (transaction.getVendor().toLowerCase().contains(vendor.toLowerCase())){
                System.out.println(transaction.toCSV());
                found = true;//set flag true if match is found
            }
        }

        if(!found){
            System.out.println("Vendor not found: " + vendor);
        }
    }
    //YearMonth.from()



}
