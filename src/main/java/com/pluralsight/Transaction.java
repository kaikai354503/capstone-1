package com.pluralsight;

public class Transaction {
    //stores data for each transaction
public String date;
public String time;
public String description;
public String vendor;
public double amount;
    //constructor for creating a new Transactions object with provided values.
    public Transaction (String date, String time, String description, String vendor, double amount){
        //assigns to instance variable
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }

    //getters
    public String getDate(){return this.date;}
    public String getTime(){return this.time;}
    public String getDescription(){return this.description;}
    public String getVendor(){return this.vendor;}
    public double getAmount(){return this.amount;}

    //converts to CSV format
    public String toCSV(){
        return String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor,amount);
    }
    //creates transaction from CSV string
    public static Transaction fromCSV(String csvLine){
        String[] parts = csvLine.split("\\|");

        //create and return new Transaction using parts
        return new Transaction(parts[0],parts[1],parts[2],parts[3], Double.parseDouble(parts[4]));
    }
        // creates a formatted string of Transaction for display
    public String toString(){
        return String.format("%-12s %-10s %-20s %-15s %10.2f", date, time, description, vendor, amount);
    }



}
