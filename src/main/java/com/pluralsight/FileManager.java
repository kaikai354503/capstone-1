package com.pluralsight;

import java.io.*;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FileManager {
    public static String TRANSACION_FILE = "src/main/resources/transactions.csv";

    public void readTransaction(String TRANSACION_FILE) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACION_FILE))){
            String line = reader.readLine();

            while (reader.readLine() != null){
                System.out.println(line);
            }


        }

    }

    public void writeTransaction(String TRANSACION_FILE) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACION_FILE))){
            String line = reader.readLine();

            while (reader.readLine() != null){
                System.out.println(line);
            }


        }

    }

    public void ensureTransactionFile() {
        File file = new File(TRANSACION_FILE);
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("date|time|description|vendor|amount");

            } catch (IOException e) {
                System.out.println("FIle could not be created: " + e.getMessage());
            }
        }
    }

    public void saveTransaction(Transaction transaction) {
        try (PrintWriter writer = new PrintWriter((new FileWriter(TRANSACION_FILE, true)))) {
            writer.println(transaction.toCSV());
        }
        catch (IOException e) {
            System.out.println("Transaction could not be saved to file: " + e.getMessage());
        }
    }


    public static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        File file = new File(TRANSACION_FILE);

        if (!file.exists()) {
            return transactions;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){

            String line;
            boolean isFirstLine = true;

             while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                if (!line.trim().isEmpty()) {
                try {
                    transactions.add(Transaction.fromCSV(line));
                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line);
                }
            }
        }
        }
        catch(IOException e) {
            System.out.println("Error reading transaction: " + e.getMessage());
        }

        Collections.reverse(transactions);
        return transactions;
    }

}
