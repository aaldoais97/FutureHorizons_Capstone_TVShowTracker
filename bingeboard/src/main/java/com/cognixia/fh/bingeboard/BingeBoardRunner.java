package com.cognixia.fh.bingeboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.connection.ConnectionManager;
import com.cognixia.fh.bingeboard.dao.ProgressLists;
import com.cognixia.fh.bingeboard.dao.Users;
import com.cognixia.fh.bingeboard.userinterface.Login;
import com.cognixia.fh.bingeboard.userinterface.MainInterface;

/**
 * BingeBoardRunner is the main class that starts the BingeBoard application.
 * It initializes the database connection, handles user login, and displays the main interface.
 */
public class BingeBoardRunner 
{
    /**
     * The main method serves as the entry point for the BingeBoard application.
     * It establishes a connection to the database, handles user login, and displays the main menu.
     */
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
            user = Login.startPage(inputScanner, connection); // Start the login process
            try {
                progressList = new ProgressLists(connection, user.getId());
            } catch (SQLException e) {
                System.out.println("An error occurred while creating the progress list: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        // If user and progress list have been established, display the main menu
        if (user != null && progressList != null) {
            MainInterface.displayMainMenu(inputScanner, connection, user, progressList); // Display the main menu after successful login
        }

        inputScanner.close(); // Close the scanner to prevent resource leaks
        connection = null; // Clear the connection variable to allow garbage collection
    }
}
