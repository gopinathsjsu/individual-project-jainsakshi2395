package test.controller;

import test.database.Database;
import test.model.Flights;
import test.utility.FileUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DatasetController {

    private Database database = Database.getInstance();
    private FileUtility fileHelper;
    public DatasetController(String path) throws IOException {
        fileHelper = new FileUtility(path);
    }

    public void datasetCreation() {
        try{
            fileHelper.fileReader(true);
        }catch (Exception e){
            System.out.println("The Flights.csv file path was not found. Please enter valid file path");
            System.exit(0);
        }
        readItems(fileHelper.getContentFile());

    }


    private void readItems(ArrayList<String> contentOfFile){
        for(int i=0;i<contentOfFile.size();i++){
            String[] splitItem = contentOfFile.get(i).split(",");
            database.getFlights().add(new Flights(splitItem[0].trim(), splitItem[1].trim(),
                    Integer.parseInt(splitItem[2].trim()), Double.parseDouble(splitItem[3].trim()),splitItem[4].trim(), splitItem[5].trim()));

        }
    }
}
