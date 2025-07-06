package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.dao.ProgressLists;
import com.cognixia.fh.bingeboard.exceptions.ShowNotInProgListException;

public class RemoveFromWatchList {
    static void displayMenu(Scanner inputScanner, Connection connection, ProgressLists progressList) {
        System.out.println("Enter the name of the show you want to remove from your watchlist (enter 'exit' to return to menu):");
        String showName = inputScanner.nextLine();

        System.out.println(""); // Print a new line for better readability

        if (showName.equalsIgnoreCase("exit")) {
            return; // Exit the method if the user wants to return to the menu
        }

        try {
            progressList.removeFromProgressList(connection, showName);
        } catch (ShowNotInProgListException e) {
            System.out.println(e.getMessage());
            System.out.println();   // Print a new line for better readability
            return; // Exit the method if the show is not found in the progress list
        } catch (SQLException e) {
            System.out.println("Error removing show from progress list: " + e.getMessage());
            System.out.println();   // Print a new line for better readability
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return; // Exit the method if an SQL error occurs
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            System.out.println();   // Print a new line for better readability
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return; // Exit the method if an unexpected error occurs
        }

        System.out.println("Successfully removed " + showName + " from your watchlist.\n");
    }
}
