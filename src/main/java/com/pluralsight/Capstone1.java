//import files
package com.pluralsight;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Capstone1 {


    private static String CSV_FILE = "transactions.csv";

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        homeScreen();

    }

    private static void initializeFile(){
        File file = new File(CSV_FILE);
        if(!file.exists()){
            try(PrintWriter )
        }

    }

    //create home screen

    private static void homeScreen(){
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
    private static void makeDeposit(){
        System.out.println("----Make a Deposit----");
        double amount = getAmountInput("Enter deposit amount");

        Transacion transacion = new Transaction(amount, "Deposit");


        //function to make deposits

    }

    //fn for making a payment
    private static void makePayment(){
        System.out.println("Make a payment");
    }
    //function for ledger
    private static void displayLedger(){
        System.out.println("Ledger");

    }

    public Capstone1() throws IOException {
    }


}
