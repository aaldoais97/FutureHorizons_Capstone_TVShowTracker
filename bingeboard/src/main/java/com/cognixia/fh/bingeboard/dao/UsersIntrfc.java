package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UsersIntrfc {
    public int getId();

    public String getUsername();
        
    public String getPassword();
        
    public String getFirstName();
        
    public String getLastName();

    // This method should check if the username already exists in the database
    public static boolean usernameExists(Connection connection, String username) {
        try {
            PreparedStatement checkUsernameStmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
            checkUsernameStmt.setString(1, username);
            ResultSet rs = checkUsernameStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false; // Username exists
            } else {
                return true; // Username does not exist
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while checking the username: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return false; // Return false in case of an error
        } catch (Exception e) {
            System.out.println("An error occurred while checking the username: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return false; // Return false in case of an error
        }
    }

    public static int validateLogin(Connection connection, String username, String password) {
        try {
            PreparedStatement loginStmt = connection.prepareStatement("SELECT id FROM users WHERE username = ? AND password = ?");
            loginStmt.setString(1, username);
            loginStmt.setString(2, password);
            ResultSet rs = loginStmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id"); // Return the user ID if credentials are valid
            } else {
                return -1; // Return -1 if credentials are invalid
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while validating login: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return -1; // Return -1 in case of an error
        } catch (Exception e) {
            System.out.println("An error occurred while validating login: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return -1; // Return -1 in case of an error
        }
    }

    public static Users insertNewUser(Connection connection, String username, String password) {
        try {
            PreparedStatement insertUserStmt = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            insertUserStmt.setString(1, username);
            insertUserStmt.setString(2, password);
            int rowsAffected = insertUserStmt.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet generatedKeys = insertUserStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    return new Users(newId, password, username); // Return the new user object
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while inserting the new user: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
        } catch (Exception e) {
            System.out.println("An error occurred while inserting the new user: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
        return null; // Return null if insertion fails
    }

}   