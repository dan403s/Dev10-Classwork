/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3libraryrefactored.service;

/**
 *
 * @author Daniel Bart
 */
public class MP3LibraryDataValidationException extends Exception {
    
    public MP3LibraryDataValidationException(String message) {
        super(message);
    }
    
    public MP3LibraryDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
