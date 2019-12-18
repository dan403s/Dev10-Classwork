/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3libraryrefactored.dao;

import com.dvb.mp3libraryrefactored.dto.MP3;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface MP3LibraryDao {

    /**
     * Adds the given MP3 to the library and associates it with the given title.
     * If there is already an MP3 associated with the given title it will return
     * that MP3 object and overwrite it, otherwise it will return null.
     *
     * @param title with which MP3 is to be associated
     * @param mp3 to be added to the library
     * @return the MP3 object previously associated with the given title if it
     * exists, null otherwise
     * @throws com.dvb.mp3library.dao.MP3LibraryDaoException
     */
    MP3 addMp3(String title, MP3 mp3) throws MP3LibraryPersistenceException;

    /**
     * Deletes MP3 associated with the given title from the library. If there is
     * no MP3 associated with the given title it will return null, otherwise it
     * will return the deleted MP3 object.
     *
     * @param title with which MP3 is associated
     * @return the MP3 object associated with the title that was deleted, null
     * if there was no such MP3 object prior to the delete
     * @throws com.dvb.mp3library.dao.MP3LibraryDaoException
     */
    MP3 deleteMp3(String title) throws MP3LibraryPersistenceException;

    /**
     * Edits MP3 associated with the given title from the library. If there is
     * no MP3 associated with the given title it will return null, otherwise it
     * will return the replaced MP3 object.
     *
     * @param title with which MP3 is associated
     * @param mp3 to replace the current mp3
     * @return the replaced MP3 object or null
     * @throws com.dvb.mp3library.dao.MP3LibraryDaoException
     */
    MP3 changeMp3(String title, MP3 mp3) throws MP3LibraryPersistenceException;

    /**
     * Lists all MP3 objects within the HashMap. Returns ArrayList of all MP3
     * objects.
     *
     * @return ArrayList of all MP3 objects
     * @throws com.dvb.mp3library.dao.MP3LibraryDaoException
     */
    List<MP3> listAllMp3s() throws MP3LibraryPersistenceException;

    /**
     * Find MP3 object from HashMap associated with given title. Returns MP3
     * object if it exists. Returns null if it doesn't.
     *
     * @param title
     * @return MP3 object or null
     * @throws com.dvb.mp3library.dao.MP3LibraryDaoException
     */
    MP3 listMp3Details(String title) throws MP3LibraryPersistenceException;

    /**
     * Search for MP3 object from HashMap associated with given search term.
     * Searches the HashMap for partial matches based upon the given search
     * term. Returns MP3 object if found, null if not found.
     *
     * @param searchTerm
     * @return MP3 object or null
     * @throws com.dvb.mp3library.dao.MP3LibraryDaoException
     */
    List<MP3> searchLibrary(String searchTerm) throws MP3LibraryPersistenceException;

}
