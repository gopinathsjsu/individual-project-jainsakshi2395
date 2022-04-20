package test.statehandler;

import test.database.Database;
import test.model.Bookings;

import java.util.ArrayList;

public class SeatValidation implements ValidationHandler{
    private ValidationHandler next= null;

    @Override
    public boolean validate(ArrayList<Bookings> bookings) {

        Database database = Database.getInstance();
        for(Bookings booking: bookings){
            if(database.getFlightsMap().get(booking.getFlightNumber()).getAvailableSeats()<booking.getNumberOfSeats()){
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
