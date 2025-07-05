package com.cognixia.fh.bingeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ProgressListsIntrfc {
    public static ProgressLists createProgressList(Connection connection, int userId) {
        try {
            PreparedStatement createListStmt = connection.prepareStatement("INSERT INTO progress_lists (id) VALUES (?)");
            createListStmt.setInt(1, userId);
            int rowsAffected = createListStmt.executeUpdate();

            if (rowsAffected > 0) {
                // Progress list ID is the same as user ID since each user has a unique list
                return new ProgressLists(userId, userId);
            }
        } catch (SQLException e) {
            System.out.println("An SQL error occurred while creating the progress list: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
        } catch (Exception e) {
            System.out.println("An error occurred while creating the progress list: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }

        return null; // Return null if the progress list creation fails
    }
}
