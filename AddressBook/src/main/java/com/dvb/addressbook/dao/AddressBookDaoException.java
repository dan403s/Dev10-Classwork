/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.addressbook.dao;

/**
 *
 * @author Daniel Bart
 */
public class AddressBookDaoException extends Exception {
    
    // for validation errors not related to exception being thrown
    public AddressBookDaoException(String message) {
        super(message);
    }
    
    // for errors related to exception being thrown; Throwable object is used because 
    // Throwable is the parent class to Exception and will handle the greatest number 
    // of possible errors
    public AddressBookDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
