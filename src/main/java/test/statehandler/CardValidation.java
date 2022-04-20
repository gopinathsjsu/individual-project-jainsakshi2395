package test.statehandler;

import test.model.Bookings;

import java.util.ArrayList;

public class CardValidation implements ValidationHandler{

    private ValidationHandler next= null;
    @Override
    public boolean validate(ArrayList<Bookings> bookings) {

        for(Bookings booking : bookings){
            if(isCardValid(booking.getCardNumber())){
                return true;
            }

        }
        return false;
    }

    @Override
    public void nextHandler(ValidationHandler next) {
        this.next = next ;
    }

    private boolean isCardValid(String cardNumber) {
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
}
