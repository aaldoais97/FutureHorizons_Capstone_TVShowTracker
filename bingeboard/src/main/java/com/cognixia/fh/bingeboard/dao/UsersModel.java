package com.cognixia.fh.bingeboard.dao;

public class UsersModel {
    int id;
    String username;
    String password;
    String firstName;
    String lastName;

    // Constructor for when user is created with all information
    public UsersModel(String firstName, int id, String lastName, String password, String username) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }

    // This much information is needed to create a user
    public UsersModel(int id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}