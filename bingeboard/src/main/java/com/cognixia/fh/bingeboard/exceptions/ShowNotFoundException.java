package com.cognixia.fh.bingeboard.exceptions;

public class ShowNotFoundException extends Exception {
    public ShowNotFoundException(String showName) {
        super(showName + " not found.");
    }
}
