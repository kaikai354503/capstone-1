package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class FileManager {
    //assigns filepath to variable
    public static String TRANSACION_FILE = "src/main/resources/transactions.csv";


    //saves transaction to CSV file
    public void saveTransaction(Transaction transaction) {
        //With resources to properly close writer
        try (PrintWriter writer = new PrintWriter((new FileWriter(TRANSACION_FILE, true)))) {
            writer.println(transaction.toCSV());
        }
        catch (IOException e) {
            System.out.println("Transaction could not be saved to file: " + e.getMessage());
        }
    }

    //loads transactions form CSV into an array list
    public static ArrayList<Transaction> loadTransactions() {
        //creates empty list
        ArrayList<Transaction> transactions = new ArrayList<>();
        File file = new File(TRANSACION_FILE);

        if (!file.exists()) {
            return transactions;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){

            String line;
            boolean isFirstLine = true;//flag to skip CSV header

            //reads file line by line
             while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                if (!line.trim().isEmpty()) {//skips empty lines
                try {
                    //converts CSV line to Transaction object
                    transactions.add(Transaction.fromCSV(line));
                }
                catch (Exception e)
                    {System.out.println("Error parsing line: " + line);}
                }
            }
        }
        catch(IOException e) {
            System.out.println("Error reading transaction: " + e.getMessage());
        }
        //reverses list so newest is displayed first.
        Collections.reverse(transactions);
        return transactions;
    }

}
