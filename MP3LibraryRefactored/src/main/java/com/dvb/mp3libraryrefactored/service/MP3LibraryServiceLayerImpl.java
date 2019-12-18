/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3libraryrefactored.service;

import com.dvb.mp3libraryrefactored.dao.MP3LibraryDao;
import com.dvb.mp3libraryrefactored.dao.MP3LibraryPersistenceException;
import com.dvb.mp3libraryrefactored.dto.MP3;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public class MP3LibraryServiceLayerImpl implements MP3LibraryServiceLayer {

    MP3LibraryDao dao;

    public MP3LibraryServiceLayerImpl(MP3LibraryDao dao) {
        this.dao = dao;
    }

    @Override
    public MP3 createMp3(String title, MP3 mp3) throws MP3LibraryDuplicateIdException, MP3LibraryDataValidationException, MP3LibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MP3 removeMp3(String title) throws MP3LibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MP3 editMp3(String title, MP3 mp3) throws MP3LibraryDuplicateIdException, MP3LibraryDataValidationException, MP3LibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MP3> showAllMp3s() throws MP3LibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MP3 showMp3Details(String title) throws MP3LibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MP3> searchLibrary(String searchTerm) throws MP3LibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void validateMp3Data(MP3 mp3) throws MP3LibraryDataValidationException {
        if (mp3.getTitle() == null || mp3.getTitle().trim().length() == 0
                || mp3.getReleaseDate() == null || mp3.getReleaseDate().trim().length() == 0
                || mp3.getAlbum() == null || mp3.getAlbum().trim().length() == 0
                || mp3.getArtistName() == null || mp3.getArtistName().trim().length() == 0
                || mp3.getGenre() == null || mp3.getGenre().trim().length() == 0
                || mp3.getUserRatingNote() == null || mp3.getUserRatingNote().trim().length() == 0) {
            throw new MP3LibraryDataValidationException("ERROR: All fields [Title, Release Date, Album, Artist Name, Genre, User Rating Note] are required.");
        }
    }

}
