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

        // Attempt to establish a connection to the database
        try {
            Connection connection = ConnectionManager.getConnection(); // Establish a connection to the database
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

        Login.startPage(inputScanner); // Start the login process
        MainInterface.displayMainMenu(inputScanner); // Display the main menu after successful login

        inputScanner.close(); // Close the scanner to prevent resource leaks
    }
}
