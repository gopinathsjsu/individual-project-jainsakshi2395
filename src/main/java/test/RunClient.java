package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import test.model.Bookings;
import test.model.Flights;

public class RunClient 
{
    public static void main( String[] args ) throws IOException
    {
        if(args.length==0){
            System.out.println("File path to Flights Dataset not provided. Please enter one.");
            System.exit(0);
        }
       
        double amountPaid = 0;
        ArrayList<Flights> flights = loadDataSet(args[1]);
        ArrayList<Bookings> bookings = loadSampleInput(args[0]);
        System.out.println(">> Processing input...");
        for (Bookings booking : bookings) {

            System.out.println("Calculating for booking : " + booking.getBookingName());

            for (Flights flight : flights) {
                
                if (booking.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {

                    if (flight.getCategory().equalsIgnoreCase("Economy") && flight.getAvailableSeats() >= booking.getNumberOfSeats()) {
                       double price = 0;
                        if (isCardValid(booking.getCardNumber())) {
                           price = flight.getPrice() * booking.getNumberOfSeats();
                            int availableSeats = flight.getAvailableSeats() - booking.getNumberOfSeats();
                            updateAvailableSeats(availableSeats,flight.getCategory(), flight.getFlightNumber());
                        }else{

                            System.err.println(">> Incorrect card details.. writing to Output.txt");
                            String log = "Please enter correct booking details for "+ booking.getBookingName() + " : incorrect card details";
                            writeToErrorLog(log);
                        }
                        amountPaid += price;
                        System.out.println("Economy >> " + price);
                        break;
                    } else if (flight.getCategory().equalsIgnoreCase("Premium Economy") && flight.getAvailableSeats() >= booking.getNumberOfSeats()) {
                        double price = 0;
                        if (isCardValid(booking.getCardNumber())) {
                             price = flight.getPrice() * booking.getNumberOfSeats();
                            int availableSeats = flight.getAvailableSeats() - booking.getNumberOfSeats();
                            updateAvailableSeats(availableSeats,flight.getCategory(), flight.getFlightNumber());
                        }else{
                            System.err.println(">> Incorrect card details.. writing to Output.txt");
                            String log = "Please enter correct booking details for "+ booking.getBookingName() + " : incorrect card details";
                            writeToErrorLog(log);
                        }
                      
                        amountPaid += price;
                        System.out.println("Premium Economy >> " + price);
                        break;
                    } else if (flight.getCategory().equalsIgnoreCase("Business") && flight.getAvailableSeats() >= booking.getNumberOfSeats()) {
                        
                        double price = 0;
                       
                        if (isCardValid(booking.getCardNumber())) {
                             price = flight.getPrice() * booking.getNumberOfSeats();
                            int availableSeats = flight.getAvailableSeats() - booking.getNumberOfSeats();
                            updateAvailableSeats(availableSeats,flight.getCategory(), flight.getFlightNumber());
                        }else{
                            System.err.println(">> Incorrect card details.. writing to Output.txt");
                            String log = "Please enter correct booking details for "+ booking.getBookingName() + " : incorrect card details";
                            writeToErrorLog(log);
                        }
                        amountPaid += price;
                        System.out.println("Business >> " + price);
                        break;
                    } else {
                        System.err.println(">> Incorrect number of Seats.. writing to Output.txt");
                        String log = "Please enter correct booking details for "+ booking.getBookingName() + " : invalid number of seats";
                        writeToErrorLog(log);
                    }
                }else{

                    System.err.println(">> Incorrect flight number.. writing to Output.txt");
                        String log = "Please enter correct booking details for "+ booking.getBookingName() + " : invalid flight number";
                        writeToErrorLog(log);

                }
            }
          
            if (amountPaid > 0) {
                updateAmount(booking.getBookingName(),booking.getFlightNumber(), booking.getSeatCategory(), booking.getNumberOfSeats(), amountPaid);
            }

            System.out.println("Calculation for booking ended : " + booking.getBookingName());
        }

    }

    private static ArrayList<Flights> loadDataSet(String filename) {

        System.out.println("Loading datasets for : "+ filename);
        ArrayList<Flights> data = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace("\"", "");
                String[] lineData = line.split(",");
                Flights flights = new Flights(lineData[0].trim(), lineData[1].trim(),
                        Integer.parseInt(lineData[2].trim()), Double.parseDouble(lineData[3].trim()),lineData[4].trim(), lineData[5].trim());
                data.add(flights);
            }

        } catch (FileNotFoundException ex) {
            System.err.println("Dataset Error: " + ex.getMessage());
        }
        System.out.println("Flight datasets : "+ data);
        return data;
    }


    private static ArrayList<Bookings> loadSampleInput(String ordersFilename) {
        System.out.println("Loading datasets for : "+ ordersFilename);
        ArrayList<Bookings> data = new ArrayList<>();

        try {
            File file = new File(ordersFilename);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace("\"", "");
                String[] lineData = line.split(",");
                Bookings booking = new Bookings(lineData[0], lineData[1].trim(), lineData[2].trim(),Integer.parseInt(lineData[3]), lineData[4].trim());
                data.add(booking);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Sample input Error: " + ex.getMessage());
        }

        System.out.println("Booking datasets : "+ data);
        return data;
    }

    public static void writeToErrorLog(String content) {
        FileWriter writer = null;
        try {
            File file = new File("Output.txt");
            writer = new FileWriter(file, true);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(content + "\n");
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(RunClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(RunClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void updateAvailableSeats(int availableSeats, String category, String flightNumber) throws IOException {
     
        
        System.out.println("Available Seats updated for : " + flightNumber);

        File inputFile = new File("flights.csv");

        // Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        try{
            List<String[]> csvBody = reader.readAll();

            // get CSV row column and replace with by using row and column
            for(int i=1; i<csvBody.size(); i++){
                String[] strArray = csvBody.get(i);
                    if(strArray[0].equalsIgnoreCase(category) && strArray[1].equalsIgnoreCase(flightNumber)){ //String to be replaced
                        csvBody.get(i)[2] = String.valueOf(availableSeats); //Target replacement
                        break;
                    }
                
            }
            reader.close();

              // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();

        }catch(Exception e){
            System.err.println("Exception while updating flights csv " + e.getMessage());
        }

        
    }

    private static boolean isCardValid(String cardNumber) {
        boolean status = false;
       
        if(cardNumber.length() > 19){
            return status;
        }

        if(cardNumber.startsWith("4") && (cardNumber.length() == 13 || cardNumber.length() ==16)){
            status = true;
        }

        if(cardNumber.startsWith("5") && cardNumber.length() == 16 && (cardNumber.charAt(1) == '1' || cardNumber.charAt(1) == '2' || 
        cardNumber.charAt(1) == '3' || cardNumber.charAt(1) == '4' || cardNumber.charAt(1) == '5')){
            status = true;
        }

        if(cardNumber.startsWith("6011") && cardNumber.length() == 16 ){
            status = true;
        }

        if(cardNumber.startsWith("3") && cardNumber.length() == 15 && (cardNumber.charAt(1) == '4' || cardNumber.charAt(1) == '7')){
            status = true;
        }

        return status;
    }

    public static void updateAmount(String bookingName, String flightNumber, String category, int numberOfSeats, double amount) throws IOException {
        
        System.out.println("Inside updateAmount method for " +  bookingName);


        FileWriter pw = new FileWriter("Output.csv");
        pw.append("Booking Name");
        pw.append(',');
        pw.append("Flight Number");
        pw.append(',');
        pw.append("Category");
        pw.append(',');
        pw.append("Number Of Seats");
        pw.append(',');
        pw.append("total price");
        pw.append("\n");
        pw.append(bookingName);
        pw.append(',');
        pw.append(flightNumber);
        pw.append(',');
        pw.append(category);
        pw.append(',');
        pw.append(numberOfSeats+"");
        pw.append(',');
        pw.append(amount +"");
        pw.append("\n");
        pw.flush();
        pw.close();
    }

}

