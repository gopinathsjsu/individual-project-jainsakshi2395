package test.controller;

import test.database.Database;
import test.model.BookedFlight;
import test.model.Bookings;
import test.model.Flights;
import test.statehandler.CardValidation;
import test.statehandler.FlightValidation;
import test.statehandler.SeatValidation;
import test.statehandler.ValidationHandler;
import test.utility.FileUtility;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class InputController {

    private Database database = Database.getInstance();
    private BookedFlight currentBooking = new BookedFlight();
    private FileUtility fileUtility;
    private ArrayList<String> output = new ArrayList<>();
    private ArrayList<Bookings> bookings = new ArrayList<>();
    private double total = 0;


    public InputController(String filePath){
        fileUtility = new FileUtility(filePath);
    }

    public void addToOutput(String str){
        output.add(str);
    }

    public boolean startOrder() {
        try{
            fileUtility.fileReader(true);
        }catch (Exception e){
            return false;
        }
        getBookings(fileUtility.getContentFile());
        return true;
    }
    public boolean checkOrder() {
        checkItemStock();
        return output.size()==0;
    }

    public void calculateTotal() {
        for(Bookings booking: bookings){
            total += booking.getNumberOfSeats()*database.getFlightsMap().get(booking.getBookingName()).getPrice();
        }
        currentBooking.);
    }

    public double getTotal() {
        return currentBooking.getTotalPrice();
    }

    public void checkoutOrder() {  for(OrderItem orderItem: items){
        Items item = database.getItemsMap().get(orderItem.getName());
        item.setQuantity(item.getQuantity()-orderItem.getQuantity());
    }
        for(String credit:creditCards){
            if(!database.getCardsSet().contains(credit)){
                database.getCardsSet().add(credit);
            }
        }
        generateOutputFile();
    }

    public void printMessage() {
        for(String line: output){
            System.out.println(line);
        }
    }

    public void getBookings(ArrayList<String> fileContent){
        for(String line: fileContent){
            String[] lineData = line.split(",");
            if(database.getFlightsMap().containsKey(lineData[1])){
                bookings.add(new Bookings(lineData[0], lineData[1].trim(), lineData[2].trim(),Integer.parseInt(lineData[3]), lineData[4].trim()););
            }else{
                output.add("Please enter correct booking details for"+  lineData[0] + ": invalid flight number."
);
            }
        }
        if(!output.isEmpty()){
            bookings.clear();
        }
    }

    public boolean checkAvailableSeats() {
        StringBuilder sb = new StringBuilder();
        database.getBookings().add(currentBooking);
        ValidationHandler flightValidation = new FlightValidation();
        ValidationHandler seatValidation = new SeatValidation();
        ValidationHandler cardValidation = new CardValidation();

        flightValidation.nextHandler(seatValidation);
        seatValidation.nextHandler(cardValidation);

            if(!flightValidation.validate(bookings)){
                output.add("One of the Flighdoesn't exist in the stock");
            }else if(!seatValidation.validate(bookings)){
                output.add("Please correct quantities of one of the items");
            }else if(!cardValidation.validate(bookings)){
                output.add("Limit on one of the Categories has exceeded");
            }

        for(Bookings booking: bookings){
            Flights flight = database.getFlightsMap().get(booking.getFlightNumber());
            if(flight.getAvailableSeats()<booking.getNumberOfSeats()){
                if(sb.length()>0)
                    sb.append(",");
                sb.append(booking.getBookingName()+"("+flight.getAvailableSeats()+")");
            }
        }
        if(sb.length()>0){
            output.add("Please enter correct booking details for : invalid number of seats");
            output.add(sb.toString());
        }
        return sb.length()==0;
    }

    public void generateOutputFile(){

        if(output.size()==0){
            output.add("Booking Name");
            output.add(currentBooking.getBookingName());
            output.add("Flight Number");
            output.add(currentBooking.getFlightNumber());
            output.add("Category");
            output.add(currentBooking.getCategory());
            output.add("Number of Seats");
            output.add(Integer.toString(currentBooking.getNumberOfSeats()));
            output.add("Total Price");
            output.add(Double.toString((currentBooking.getTotalPrice())));
            output.add("Amount Paid");
            output.add(Double.toString((currentBooking.getTotalPrice())));
            try{
                fileUtility.writeOuput(output,false);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            try{
                fileUtility.writeOuput(output,true);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
