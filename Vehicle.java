package marketplace;

public class Vehicle {

    private String listingId;
    private String vin;

    private String make;
    private String model;
    private int year;

    private double price;
    private int mileage;

    private String fuelType;
    private String transmission;
    private String location;

    private String sellerId;

    private boolean available;

    public Vehicle(String listingId, String vin, String make, String model,
                   int year, double price, int mileage,
                   String fuelType, String transmission,
                   String location, String sellerId) {

        this.listingId = listingId;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.location = location;
        this.sellerId = sellerId;

        this.available = true;
    }

    // Getters

    public String getListingId() {
        return listingId;
    }

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public int getMileage() {
        return mileage;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getLocation() {
        return location;
    }

    public String getSellerId() {
        return sellerId;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {

        return "\nListing ID: " + listingId +
               "\nVIN: " + vin +
               "\nVehicle: " + year + " " + make + " " + model +
               "\nPrice: " + price +
               "\nMileage: " + mileage + " km" +
               "\nFuel Type: " + fuelType +
               "\nTransmission: " + transmission +
               "\nLocation: " + location +
               "\nSeller ID: " + sellerId +
               "\nAvailability: " + (available ? "Available" : "Sold/Unavailable");
    }
}