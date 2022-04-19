package test.controller;

import test.database.Database;
import test.model.Bookings;
import test.model.Flights;
import test.utility.FileUtility;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class InputController {

    private Database database = Database.getInstance();
    private Bookings currentBooking = new Bookings();
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
        getItems(fileUtility.getContentFile());
        return true;
    }
    public boolean checkOrder() {
        checkItemStock();
        return output.size()==0;
    }

    public void calculateTotal() {
        for(Bookings booking: bookings){
            total += booking.getNumberOfSeats()*database.getFlights().get(booking.getBookingName()).getPrice();
        }
        currentBooking.);
    }

    public double getTotal() {
        return currentOrder.getTotalPrice();
    }

    public void checkoutOrder() {
        ArrayList<Flights> flights = database.getFlights();

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


        generateOutputFile();
    }

    public void printMessage() {
        for(String line: output){
            System.out.println(line);
        }
    }

    public void getItems(ArrayList<String> fileContent){
        for(String line: fileContent){
            String[] item = line.split(",");
            if(database.getFlightsMap().containsKey(item[0])){
                items.add(new OrderItem(item[0],Integer.parseInt(item[1]),item[2].replaceAll("\\s+","")));
            }else{
                output.add("Item "+item[0]+" not found");
            }
        }
        if(!output.isEmpty()){
            items.clear();
        }
    }

    public boolean checkItemStock() {
        StringBuilder sb = new StringBuilder();
        database.getOrdersList().add(currentOrder);
        ValidationHandler itemPresence = new ItemPresenceValidation();
        ValidationHandler itemStock = new ItemStockValidation();
        ValidationHandler itemCategory = new ItemCategoryCapValidation();
        itemPresence.nextHandler(itemStock);
        itemStock.nextHandler(itemCategory);
        if(!itemPresence.validate(items)){
            output.add("One of the Item doesn't exist in the stock");
        }else if(!itemStock.validate(items)){
            output.add("Please correct quantities of one of the items");
        }else if(!itemCategory.validate(items)){
            output.add("Limit on one of the Categories has exceeded");
        }
        for(OrderItem orderItem: items){
            Items item = database.getItemsMap().get(orderItem.getName());
            if(item.getQuantity()<orderItem.getQuantity()){
                if(sb.length()>0)
                    sb.append(",");
                sb.append(orderItem.getName()+"("+item.getQuantity()+")");
            }else{
                if(!creditCards.contains(orderItem.getCardDetails()))
                    creditCards.add(orderItem.getCardDetails());
            }
        }
        if(sb.length()>0){
            output.add("Please correct quantities");
            output.add(sb.toString());
        }
        return sb.length()==0;
    }

    public void generateOutputFile(){
        //System.out.println("Zing Zing Amazing");
        if(output.size()==0){
            output.add("Amount Paid");
            output.add(Double.toString((currentOrder.getTotalPrice())));
            try{
                fileHelper.writeOuput(output,false);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            try{
                fileHelper.writeOuput(output,true);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
