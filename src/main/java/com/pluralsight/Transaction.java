package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transaction {
private String date;
private String time;
private String description;
private String vendor;
private double amount;

    public Transaction (String date, String time, String description, String vendor, double amount){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }

    //getters
    public String getDate(){return date;}
    public String getTime(){return time;}
    public String getDescription(){return description;}
    public String getVendor(){return vendor;}
    public Double getAmount(){return amount;}

    //CSV format
    public String toCSV(){
        return String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor,amount);
    }

    public Transaction fromCSV(String csvLine){
        String[] parts = csvLine.split("\\|");
        return new Transaction(parts[0],parts[1],parts[2],parts[3], Double.parseDouble(parts[4]));
    }
    public String toString(){
        return String.format("%-12s %-10s %-20s %-15s %10.2f", date, time, description, vendor, amount);
    }

    public String getCurrentDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getCurrentTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

}
