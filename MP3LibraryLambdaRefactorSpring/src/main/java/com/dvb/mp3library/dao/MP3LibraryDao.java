/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3library.dao;

import com.dvb.mp3library.dto.MP3;
import java.util.List;
import java.util.Map;

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
    MP3 addMp3(String title, MP3 mp3) throws MP3LibraryDaoException;

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
    MP3 deleteMp3(String title) throws MP3LibraryDaoException;

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
    MP3 changeMp3(String title, MP3 mp3) throws MP3LibraryDaoException;

    /**
     * Lists all MP3 objects within the HashMap. Returns ArrayList of all MP3
     * objects.
     *
     * @return ArrayList of all MP3 objects
     * @throws com.dvb.mp3library.dao.MP3LibraryDaoException
     */
    List<MP3> listAllMp3s() throws MP3LibraryDaoException;

    /**
     * Find MP3 object from HashMap associated with given title. Returns MP3
     * object if it exists. Returns null if it doesn't.
     *
     * @param title
     * @return MP3 object or null
     * @throws com.dvb.mp3library.dao.MP3LibraryDaoException
     */
    MP3 listMp3Details(String title) throws MP3LibraryDaoException;

    /**
     * Search for MP3 object from HashMap associated with given search term.
     * Searches the HashMap for partial matches based upon the given search
     * term. Returns MP3 object if found, null if not found.
     *
     * @param searchTerm
     * @return MP3 object or null
     * @throws com.dvb.mp3library.dao.MP3LibraryDaoException
     */
    List<MP3> searchLibrary(String searchTerm) throws MP3LibraryDaoException;

    // Find all MP3s released in the last N years
    List<MP3> listAllMp3sReleasedInLastNumberOfYears(int years) throws MP3LibraryDaoException;

    // Find all the MP3s in a given genre
    // When searching by genre, the MP3s should be sorted into separate data structures by artist.
    Map<String, List<MP3>> listAllMp3sInGenreByArtist(String genre) throws MP3LibraryDaoException;
    
    // Find all the MP3s by a given artist
    List<MP3> listAllMp3sByArtist(String artist) throws MP3LibraryDaoException;
    
    // Find all the MP3s released on a particular album
    List<MP3> listAllMp3sByAlbum(String album) throws MP3LibraryDaoException;
    
    // Find the average age of the MP3s in the collection
    double listAverageAgeOfAllMp3s() throws MP3LibraryDaoException;
    
    // Find the newest MP3 in your collection
    MP3 listNewestMp3() throws MP3LibraryDaoException;
    
    // Find the oldest MP3 in your collection
    MP3 listOldestMp3() throws MP3LibraryDaoException;
    
    // Find the average number of notes associated with MP3s in your collection
    double listAverageNumberOfNotesPerMp3() throws MP3LibraryDaoException;
    
}
