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
public class MP3LibraryDuplicateIdException extends Exception {

    public MP3LibraryDuplicateIdException(String message) {
        super(message);
    }
    
    public MP3LibraryDuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
