/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.simplecalculator;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class Prompter {

    // instantiate new Scanner object
    Scanner sc = new Scanner(System.in);

    // declare variables and initialize if necessary
    private String userResponse;

    // public method to prompt the user and return a String
    public double promptUser(String prompt) {
        System.out.println(prompt);
        this.userResponse = sc.nextLine();
        double userResponseDouble;

        if (
            userResponse.equalsIgnoreCase("+") ||
            userResponse.equalsIgnoreCase("-") || 
            userResponse.equalsIgnoreCase("*") || 
            userResponse.equalsIgnoreCase("/") || 
            userResponse.equalsIgnoreCase("exit")
            ){
            return userResponseDouble = 0.0;
        } else {
            return userResponseDouble = Float.parseFloat(userResponse);
        }
    }

    public String getUserResponse() {
        return userResponse;
    }
}
