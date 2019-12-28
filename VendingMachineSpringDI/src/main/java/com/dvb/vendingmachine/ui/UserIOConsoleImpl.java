/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.ui;

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
        String responseString;
        double responseDouble = 0;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseDouble = Float.parseFloat(responseString);
                    isValid = true;
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseDouble;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public double readDouble(String prompt, double min, double max) {
        String responseString;
        double responseDouble = 0;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseDouble = Float.parseFloat(responseString);
                    if (responseDouble >= min && responseDouble <= max) {
                        isValid = true;
                    } else {
                        throw new UnsupportedIOFormatException("Must enter number within range.");
                    }
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
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
        String responseString;
        int responseInt = 0;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseInt = Integer.parseInt(responseString);
                    isValid = true;
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!isValid);

        return responseInt;
    }

    // prompt user and return response between min and max, inclusive of both
    @Override
    public int readInt(String prompt, int min, int max) {
        String responseString;
        int responseInt = 0;
        boolean isValid = false;

        do {
            try {
                try {
                    System.out.println(prompt);
                    responseString = sc.nextLine();
                    responseInt = Integer.parseInt(responseString);
                    if (responseInt >= min && responseInt <= max) {
                        isValid = true;
                    } else {
                        throw new UnsupportedIOFormatException("Must enter number within range.");
                    }
                } catch (NumberFormatException e) {
                    throw new UnsupportedIOFormatException("ERROR!!! NOT A NUMBER!!!", e);
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
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
        boolean isValid = false;
        String responseString;
        do {
            System.out.println(prompt);
            responseString = sc.nextLine();
            if (!responseString.equals("")) {
                isValid = true;
            } else {
                System.out.println("Must type at least one character.");
            }
        } while (!isValid);

        return responseString;
    }

}
