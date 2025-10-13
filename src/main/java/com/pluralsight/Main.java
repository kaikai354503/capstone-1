//import files
package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        homeScreen();

    }

    //create home screen
    private static void homeScreen(){
        System.out.println("\n----Finance Tracker----\n");
        System.out.println("Please enter a character to make your selection.");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
    }
}
