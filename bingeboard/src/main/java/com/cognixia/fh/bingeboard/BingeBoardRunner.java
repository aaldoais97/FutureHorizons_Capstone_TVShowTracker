package com.cognixia.fh.bingeboard;

import java.util.Scanner;

import com.cognixia.fh.bingeboard.userinterface.Login;
import com.cognixia.fh.bingeboard.userinterface.MainInterface;

/**
 * Hello world!
 *
 */
public class BingeBoardRunner 
{
    public static void main( String[] args )
    {
        Scanner inputScanner = new Scanner(System.in);

        Login.signinPage(inputScanner); // Start the login process
        MainInterface.displayMainMenu(inputScanner); // Display the main menu after successful login

        inputScanner.close(); // Close the scanner to prevent resource leaks
    }
}
