package test.database;

import test.model.BookedFlight;
import test.model.Bookings;
import test.model.Flights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Database {

        private static Database databaseInstance;

        private HashMap<String,Flights> flightsMap = new HashMap<>();
        private ArrayList<BookedFlight> bookings = new ArrayList<>();

    private Database() {}

        public static Database getInstance() {
        if( databaseInstance == null){
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    public HashMap<String,Flights> getFlightsMap(){
        return flightsMap;
    }

        public ArrayList<BookedFlight> getBookings() {
        return bookings;
    }



}
