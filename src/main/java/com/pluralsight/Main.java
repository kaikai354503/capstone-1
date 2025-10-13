//import files
package com.pluralsight;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        homeScreen();

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
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    //function to make deposits
    private static void makeDeposit(){
        System.out.println("Make a Deposit");
    }
    //function to make payments
    private static void makePayment(){
        System.out.println("Make a payment");
    }
    //function for ledger
    private static void displayLedger(){
        System.out.println("Ledger");

    }


}
