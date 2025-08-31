package com.example.mpapp;

public class User {
    public String email;
    public String password;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
