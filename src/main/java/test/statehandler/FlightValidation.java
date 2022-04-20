package test.statehandler;

import test.database.Database;
import test.model.Bookings;
import test.model.Flights;

import java.util.ArrayList;

public class FlightValidation implements ValidationHandler{
    private ValidationHandler next= null;
    @Override
    public boolean validate(ArrayList<Bookings> bookings) {
        Database database = Database.getInstance();

        for(Bookings booking: bookings){
            if(!database.getFlightsMap().containsKey(booking.getFlightNumber())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void nextHandler(ValidationHandler next) {
        this.next = next ;
    }
}