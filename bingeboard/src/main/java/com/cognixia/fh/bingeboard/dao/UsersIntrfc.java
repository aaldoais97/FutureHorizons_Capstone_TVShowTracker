package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UsersIntrfc {
    public int getId();

    public String getUsername();
        
    public String getPassword();

    /*
     * This method checks if a username already exists in the database.
     * It throws SQLException if there is an error executing the query,
     * and throws Exception for any other unexpected errors.
     */
    public static boolean usernameExists(Connection connection, String username) throws SQLException, Exception {
        // Check if the username already exists in the database
        try {
            PreparedStatement usernameExistsStmt = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
            usernameExistsStmt.setString(1, username);
            ResultSet rs = usernameExistsStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Username exists
            } else {
                return false; // Username does not exist
            }
        } catch (SQLException e) {
            throw e; // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e; // Rethrow the exception to be handled by the caller
        }
    }

    /* This method validates the login credentials of a user.
       It checks if the provided username and password match any record in the database.
       It throws SQLException if there is an error executing the query,
       and throws Exception for any other unexpected errors.
    */
    public static int validateLogin(Connection connection, String username, String password) throws SQLException, Exception {
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
            throw e; // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e; // Rethrow the exception to be handled by the caller
        }
    }

    /* This method inserts a new user into the database.
       It takes a Connection object, username, and password as parameters.
       It returns a Users object if the insertion is successful, or null if it fails.
       It throws SQLException if there is an error executing the query,
       and throws Exception for any other unexpected errors.
    */
    public static Users insertNewUser(Connection connection, String username, String password) throws SQLException, Exception {
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
            throw e; // Rethrow the exception to be handled by the caller
        } catch (Exception e) {
            throw e; // Rethrow the exception to be handled by the caller
        }
        
        return null; // Return null if insertion fails
    }

}   