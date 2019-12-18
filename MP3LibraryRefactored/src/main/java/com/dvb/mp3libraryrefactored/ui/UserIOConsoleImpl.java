/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3libraryrefactored.ui;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class UserIOConsoleImpl implements UserIO {

    // instantiate new Scanner object
    Scanner sc = new Scanner(System.in);

    // print message
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    // prompt user and return response
    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        String responseString = sc.nextLine();
        double responseDouble = Float.parseFloat(responseString);
        return responseDouble;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean isValid = false;
        double responseDouble;
        do {
            System.out.println(prompt);
            String responseString = sc.nextLine();
            responseDouble = Float.parseFloat(responseString);
            if (responseDouble >= min && responseDouble <= max) {
                isValid = true;
            }
        } while (!isValid);
        return responseDouble;
    }

    // prompt user and return response
    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        String responseString = sc.nextLine();
        float responseFloat = Float.parseFloat(responseString);
        return responseFloat;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean isValid = false;
        float responseFloat;
        do {
            System.out.println(prompt);
            String responseString = sc.nextLine();
            responseFloat = Float.parseFloat(responseString);
            if (responseFloat >= min && responseFloat <= max) {
                isValid = true;
            }
        } while (!isValid);
        return responseFloat;
    }

    // prompt user and return response
    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        String responseString = sc.nextLine();
        int responseInt = Integer.parseInt(responseString);
        return responseInt;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public int readInt(String prompt, int min, int max) {
        boolean isValid = false;
        int responseInt;
        do {
            System.out.println(prompt);
            String responseString = sc.nextLine();
            responseInt = Integer.parseInt(responseString);
            if (responseInt >= min && responseInt <= max) {
                isValid = true;
            }
        } while (!isValid);
        return responseInt;
    }

    // prompt user and return response
    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        String responseString = sc.nextLine();
        long responseLong = Long.parseLong(responseString);
        return responseLong;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public long readLong(String prompt, long min, long max) {
        boolean isValid = false;
        long responseLong;
        do {
            System.out.println(prompt);
            String responseString = sc.nextLine();
            responseLong = Long.parseLong(responseString);
            if (responseLong >= min && responseLong <= max) {
                isValid = true;
            }
        } while (!isValid);
        return responseLong;
    }

    // prompt user and return response
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String responseString = sc.nextLine();
        return responseString;
    }

    // prompt user and return response - response cannot be blank ("")
    @Override
    public String readStringNoBlanks(String prompt) {
        System.out.println(prompt);
        String responseString = sc.nextLine();
        if (responseString.equals("")) {
            responseString = " ";
        }

        return responseString;
    }

}
