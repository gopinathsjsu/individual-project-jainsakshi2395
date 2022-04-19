package test.model;

public class Flights {

    private String category;
    private String flightNumber;
    private int availableSeats;
    private double price;
    private String arrivalCity;
    private String departureCity;

    public Flights(String category, String flightNumber, int availableSeats, double price, String arrivalCity, String departureCity) {
        this.category = category;
        this.flightNumber = flightNumber;
        this.availableSeats = availableSeats;
        this.price = price;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    @Override
    public String toString() {
        return "Flights [arrivalCity=" + arrivalCity + ", availableSeats=" + availableSeats + ", category=" + category
                + ", departureCity=" + departureCity + ", flightNumber=" + flightNumber + ", price=" + price + "]";
    }
    

    
}
