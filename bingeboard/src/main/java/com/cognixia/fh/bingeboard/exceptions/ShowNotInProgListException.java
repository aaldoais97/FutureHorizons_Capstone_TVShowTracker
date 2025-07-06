package com.cognixia.fh.bingeboard.exceptions;

public class ShowNotInProgListException extends Exception {
    public ShowNotInProgListException(String showName) {
        super("The show '" + showName + "' is not in your progress list.");
    }
}
