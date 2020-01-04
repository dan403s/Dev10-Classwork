/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.controller;

/**
 *
 * @author Daniel Bart
 */
public class UnsupportedMenuOptionException extends Exception {

    // constructor that takes a String and passes it into Exception object's constructor; 
    // used if exception isn't thrown; for our validation
    public UnsupportedMenuOptionException(String message) {
        super(message);
    }

    // constructor that takes a String and Throwable object, because Throwable is 
    // the parent class for Exception; used it exception is thrown along with our message
    public UnsupportedMenuOptionException(String message, Throwable cause) {
        super(message, cause);
    }

}
