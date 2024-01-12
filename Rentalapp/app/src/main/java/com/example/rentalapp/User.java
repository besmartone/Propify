package com.example.rentalapp;

public class User {
    private String fullName;
    private String email;
    private String mobileNumber;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String fullName, String email, String mobileNumber) {
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
