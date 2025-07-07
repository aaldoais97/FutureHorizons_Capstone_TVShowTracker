package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Users class represents a user in the system with attributes such as id, username, password, first name, and last name.
 * It provides methods to check if a username exists, validate login credentials, and insert a new user into the database.
 */
public class Users implements UsersIntrfc {
    int id;
    String username;
    String password;

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

    public static boolean usernameExists(Connection connection, String username) throws SQLException, Exception {
        return UsersIntrfc.usernameExists(connection, username);
    }

    public static int validateLogin(Connection connection, String username, String password) throws SQLException, Exception { 
        return UsersIntrfc.validateLogin(connection, username, password);
    }

    public static Users insertNewUser(Connection connection, String username, String password) throws SQLException, Exception {
        return UsersIntrfc.insertNewUser(connection, username, password);
    }
}