package com.cognixia.fh.bingeboard.exceptions;

// This exception is thrown when a show is not found in the user's progress list.
public class ShowNotInProgListException extends Exception {
    public ShowNotInProgListException(String showName) {
        super("The show '" + showName + "' is not in your progress list.");
    }
}
