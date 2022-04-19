package test.model;

public class Bookings {
    
    private String bookingName;
    private String flightNumber;
    private String  seatCategory;
    private int numberOfSeats;
    private String cardNumber;

    public Bookings(){}

   public Bookings(String bookingName, String flightNumber, String seatCategory, int numberOfSeats,
            String cardNumber) {
        this.bookingName = bookingName;
        this.flightNumber = flightNumber;
        this.seatCategory = seatCategory;
        this.numberOfSeats = numberOfSeats;
        this.cardNumber = cardNumber;
    }
    
    public String getBookingName() {
        return bookingName;
    }
    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getSeatCategory() {
        return seatCategory;
    }
    public void setSeatCategory(String seatCategory) {
        this.seatCategory = seatCategory;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "Bookings [bookingName=" + bookingName + ", cardNumber=" + cardNumber + ", flightNumber=" + flightNumber
                + ", numberOfSeats=" + numberOfSeats + ", seatCategory=" + seatCategory + "]";
    }


    

}
