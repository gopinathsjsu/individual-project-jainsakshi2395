package test.database;

import test.model.Bookings;
import test.model.Flights;

import java.util.ArrayList;


public class Database {

        private static Database databaseInstance;

        private ArrayList<Flights>  flights = new ArrayList<>();
        private ArrayList<Bookings> bookings = new ArrayList<>();

    private Database() {}

        public static Database getInstance() {
        if( databaseInstance == null){
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    public ArrayList<Flights> getFlights() {
        return flights;
    }

        public ArrayList<Bookings> getBookings() {
        return bookings;
    }



}
