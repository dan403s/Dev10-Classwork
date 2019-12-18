/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3libraryrefactored.service;

import com.dvb.mp3libraryrefactored.dao.MP3LibraryPersistenceException;
import com.dvb.mp3libraryrefactored.dto.MP3;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface MP3LibraryServiceLayer {
    
    // ADD MP3 ---------------------------------------------------------------------
    MP3 createMp3(String title, MP3 mp3) throws MP3LibraryDuplicateIdException, MP3LibraryDataValidationException, MP3LibraryPersistenceException;

    // REMOVE MP3 ------------------------------------------------------------------
    MP3 removeMp3(String title) throws MP3LibraryPersistenceException;

    // EDIT MP3 --------------------------------------------------------------------
    MP3 editMp3(String title, MP3 mp3) throws MP3LibraryDuplicateIdException, MP3LibraryDataValidationException, MP3LibraryPersistenceException;

    // LIST ALL MP3s ---------------------------------------------------------------
    List<MP3> showAllMp3s() throws MP3LibraryPersistenceException;

    // LIST MP3 DETAILS -------------------------------------------------------------
    MP3 showMp3Details(String title) throws MP3LibraryPersistenceException;

    // SEARCH LIBRARY FOR MP3 (PARTIAL OK) ------------------------------------------
    List<MP3> searchLibrary(String searchTerm) throws MP3LibraryPersistenceException;
    
}
