package com.cognixia.fh.bingeboard.exceptions;

/**
 * This exception is thrown when a show is not found in the database.
 * It can be used to indicate that a specific show by name or ID does not exist.
 */
public class ShowNotFoundException extends Exception {
    public ShowNotFoundException(String showName) {
        super(showName + " not found.");
    }

    public ShowNotFoundException(int showId) {
        super("Show with ID " + showId + " not found.");
    }
}
