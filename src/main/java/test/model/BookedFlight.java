package test.model;

public class BookedFlight {

    private String bookingName;
    private String flightNumber;
    private String  category;
    private int numberOfSeats;
    private Double totalPrice;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "BookedFlight{" +
                "bookingName='" + bookingName + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", category='" + category + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
