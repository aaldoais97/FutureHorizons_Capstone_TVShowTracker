package com.cognixia.fh.bingeboard.userinterface;

import java.sql.Connection;
import java.util.Scanner;

import com.cognixia.fh.bingeboard.exceptions.ShowNotFoundException;

public class RemoveFromWatchList {
    static void displayMenu(Scanner inputScanner, Connection connection) {
        System.out.println("Enter the name of the show you want to remove from your watchlist:");
        String showName = inputScanner.nextLine();

        try {
            // Placeholder
            if (showName.isEmpty()) {
                throw new ShowNotFoundException("No show name provided.");
            }
            boolean removed = false; // Replace with actual call to remove the show
            if (removed) {
                System.out.println(showName + " has been removed from your watchlist.");
            } else {
                System.out.println("Show not found in your watchlist.");
            }
        } catch (ShowNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
