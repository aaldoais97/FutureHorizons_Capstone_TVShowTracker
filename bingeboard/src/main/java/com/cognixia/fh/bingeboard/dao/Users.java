package com.cognixia.fh.bingeboard.dao;

public class Users implements UsersIntrfc {
    int id;
    String username;
    String password;
    String firstName;
    String lastName;

    // Constructor for when user is created with all information
    public Users(String firstName, int id, String lastName, String password, String username) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }

    // This much information is needed to create a user
    public Users(int id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    // Getters (no need for setters since user information does not change after instantiation)
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}