/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Daniel Bart
 */
public interface UserIO {

    // print message
    void print(String msg);

    // prompt user and return response
    double readDouble(String prompt);

    // prompt user and return response between min and max, inclusive of both
    double readDouble(String prompt, double min, double max);

    // prompt user and return response
    float readFloat(String prompt);

    // prompt user and return response between min and max, inclusive of both
    float readFloat(String prompt, float min, float max);

    // prompt user and return response
    int readInt(String prompt);

    // prompt user and return response between min and max, inclusive of both
    int readInt(String prompt, int min, int max);

    // prompt user and return response
    long readLong(String prompt);

    // prompt user and return response between min and max, inclusive of both
    long readLong(String prompt, long min, long max);

    // prompt user and return response
    String readString(String prompt);

    // prompt user and return response - response cannot be blank ("")
    String readStringNoBlanks(String prompt);

    // prompt user and return response - response must be LocalDate formatted as "MM/dd/yyyy"
    LocalDate readLocalDate(String prompt);

    // prompt user and return BigDecimal
    BigDecimal readBigDecimalPositive(String prompt);

    // prompt user and return BigDecimal - if user types blank or empty string, return BigDecimal null
    BigDecimal readBigDecimalOrNull(String prompt);

    // prompt user and return response - response must either be blank/empty or formatted as "MM/dd/yyyy"
    LocalDate readLocalDateOrNull(String prompt);

    // prompt user and return response - response must be either y/n
    String readStringYesOrNo(String prompt);

    // prompt user and return response - response must be in present or future
    LocalDate readLocalDateOnlyPresentOrFuture(String prompt);
    
    // prompt user and return response - response can contain :
    String readStringColonsAllowed(String prompt);

}
