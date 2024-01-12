package com.example.rentalapp;
public class Property {
    public String propertyTitle;
    public String propertyType;
    public String numBedrooms;
    public String numBathrooms;
    public String monthlyRent;
    public String location;
    public String contactNumber;

    // Default constructor
    public Property() {
    }

    // Parameterized constructor
    public Property(String propertyTitle, String propertyType, String numBedrooms,
                    String numBathrooms, String monthlyRent, String location,
                    String contactNumber) {
        this.propertyTitle = propertyTitle;
        this.propertyType = propertyType;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.monthlyRent = monthlyRent;
        this.location = location;
        this.contactNumber = contactNumber;
    }

    // Getters and setters
    public String getPropertyTitle() { return propertyTitle; }
    public void setPropertyTitle(String propertyTitle) { this.propertyTitle = propertyTitle; }

    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    public String getNumBedrooms() { return numBedrooms; }
    public void setNumBedrooms(String numBedrooms) { this.numBedrooms = numBedrooms; }

    public String getNumBathrooms() { return numBathrooms; }
    public void setNumBathrooms(String numBathrooms) { this.numBathrooms = numBathrooms; }

    public String getMonthlyRent() { return monthlyRent; }
    public void setMonthlyRent(String monthlyRent) { this.monthlyRent = monthlyRent; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}
