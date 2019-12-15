/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.addressbook.ui;

/**
 *
 * @author Daniel Bart
 */
public interface UserIO {

    // only print message
    void print(String msg);

    // read prompt and return double
    double readDouble(String prompt);

    // read prompt and return double within min and max
    double readDouble(String prompt, double min, double max);

    // read prompt and return float
    float readFloat(String prompt);

    // read prompt and return float within min and max
    float readFloat(String prompt, float min, float max);

    // read prompt and return int
    int readInt(String prompt);

    // read prompt and return int within min and max
    int readInt(String prompt, int min, int max);

    // read prompt and return long
    long readLong(String prompt);

    // read prompt and return long within min and max
    long readLong(String prompt, long min, long max);

    // read prompt and return String
    String readString(String prompt);

}
