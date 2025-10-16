package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {
    public String TRANSACION_FILE = "src/main/resources/transactions.csv";

    public void ensureTransactionFile(){
        File file = new File(TRANSACION_FILE);
        if(!file.exists()){
            try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
                writer.println("date|time|description|vendor|amount");

            }
            catch(IOException e){
                System.out.println("FIle could not be created: " + e.getMessage());
            }
        }
    }

    public void saveTransaction(Transaction transaction){
        try (PrintWriter writer = new PrintWriter((new FileWriter(TRANSACION_FILE, true)))){
            writer.println(transaction.toCSV());
        }
        catch (IOException  e){
            System.out.println("Transaction could not be saved to file: " + e.getMessage());
        }
    }

}
