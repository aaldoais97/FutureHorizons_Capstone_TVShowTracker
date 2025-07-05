package com.cognixia.fh.bingeboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.connection.ConnectionManager;
import com.cognixia.fh.bingeboard.dao.ProgressLists;
import com.cognixia.fh.bingeboard.dao.Shows;
import com.cognixia.fh.bingeboard.dao.Users;
import com.cognixia.fh.bingeboard.userinterface.Login;
import com.cognixia.fh.bingeboard.userinterface.MainInterface;

/**
 * BingeBoardRunner is the main class that starts the BingeBoard application.
 * It initializes the database connection, handles user login, and displays the main interface.
 */
public class BingeBoardRunner 
{
    public static void main( String[] args )
    {
        Scanner inputScanner = new Scanner(System.in);
        Connection connection = null; // Initialize the connection variable
        Users user = null; // Initialize the user variable
        ProgressLists progressList = null; // Initialize the show progress list

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
            ArrayList<Shows> showsList = Shows.allShows(connection); // Fetch all shows from the database
            showsList.forEach(show -> System.out.println(show)); // Print the names of all shows for debugging purposes
            user = Login.startPage(inputScanner, connection, progressList); // Start the login process
        }
        
        if (user != null) {
            MainInterface.displayMainMenu(inputScanner, connection, user, progressList); // Display the main menu after successful login
        }

        inputScanner.close(); // Close the scanner to prevent resource leaks
        connection = null; // Clear the connection variable to allow garbage collection
    }
}
