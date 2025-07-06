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

        if (showName.equalsIgnoreCase("exit")) {
            return; // Exit the method if the user wants to return to the menu
        }

        try {
            progressList.removeFromProgressList(connection, showName);
        } catch (ShowNotInProgListException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error removing show from progress list: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }

        System.out.println("Successfully removed " + showName + " from your watchlist.");
    }
}
