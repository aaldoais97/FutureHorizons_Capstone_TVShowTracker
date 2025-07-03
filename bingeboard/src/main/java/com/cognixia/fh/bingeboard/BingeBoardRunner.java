package com.cognixia.fh.bingeboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.connection.ConnectionManager;
import com.cognixia.fh.bingeboard.userinterface.Login;
import com.cognixia.fh.bingeboard.userinterface.MainInterface;

/**
 * Hello world!
 *
 */
public class BingeBoardRunner 
{
    public static void main( String[] args )
    {
        Scanner inputScanner = new Scanner(System.in);
        Connection connection = null; // Initialize the connection variable

        // Attempt to establish a connection to the database
        try {
            connection = ConnectionManager.getConnection(); // Establish a connection to the database
            // No need to print connection status, as the connection is established if the program starts
        } catch(SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return; // Exit if the connection fails
        } catch(IOException e) {
            System.out.println("Error reading configuration file: " + e.getMessage());
            return; // Exit if there's an error reading the config file
        } catch(ClassNotFoundException e) {
            System.out.println("Database driver not found: " + e.getMessage());
            return; // Exit if the JDBC driver is not found
        } catch(Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return; // Exit if there's an unexpected error
        }

        // This code will not be reached if the connection fails, but just to be safe, check for null
        if (connection != null) {
            Login.startPage(inputScanner, connection); // Start the login process
            MainInterface.displayMainMenu(inputScanner, connection); // Display the main menu after successful login
        }

        inputScanner.close(); // Close the scanner to prevent resource leaks
        connection = null; // Clear the connection variable to allow garbage collection
    }
}
