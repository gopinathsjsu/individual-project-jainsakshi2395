package test.statehandler;

import test.model.Bookings;

import java.util.ArrayList;

public interface ValidationHandler {
    boolean validate(ArrayList<Bookings> bookings);
    void nextHandler(ValidationHandler next);

}
