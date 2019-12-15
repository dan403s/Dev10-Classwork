/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3library.dao;

import com.dvb.mp3library.dto.MP3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class MP3LibraryDaoFileImpl implements MP3LibraryDao {

    // declare constants and initialize them
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    // add new Map to store MP3s by title as key and MP3 object as value
    private Map<String, MP3> mp3Map = new HashMap<>();

    /*
     * Adds the given MP3 to the library and associates it with the given title.
     * If there is already an MP3 associated with the given title it will return
     * that MP3 object and overwrite it, otherwise it will return null.
     */
    @Override
    public MP3 addMp3(String title, MP3 mp3) throws MP3LibraryDaoException {
        loadLibray();
        MP3 newMp3 = mp3Map.put(title, mp3);
        writeLibrary();
        return newMp3;
    }

    /*
     * Deletes MP3 associated with the given title from the library. If there is
     * no MP3 associated with the given title it will return null, otherwise it
     * will return the deleted MP3 object.
     */
    @Override
    public MP3 deleteMp3(String title) throws MP3LibraryDaoException {
        loadLibray();
        MP3 deletedMp3 = mp3Map.remove(title);
        writeLibrary();
        return deletedMp3;
    }

    /*
     * Edits MP3 associated with the given title from the library. If there is
     * no MP3 associated with the given title it will return null, otherwise it
     * will return the replaced MP3 object.
     */
    @Override
    public MP3 changeMp3(String title, MP3 mp3) throws MP3LibraryDaoException {
        loadLibray();
        MP3 changedMp3 = mp3Map.replace(title, mp3);
        writeLibrary();
        return changedMp3;
    }

    /*
     * Lists all MP3 objects within the HashMap. Returns ArrayList of all
     * MP3 objects.
     */
    @Override
    public List<MP3> listAllMp3s() throws MP3LibraryDaoException {
        loadLibray();
        List<MP3> allMp3s = new ArrayList<>(mp3Map.values());
        return allMp3s;
    }

    /*
     * Find MP3 object from HashMap associated with given title. Returns MP3
     * object if it exists. Returns null if it doesn't.
     */
    @Override
    public MP3 listMp3Details(String title) throws MP3LibraryDaoException {
        loadLibray();
        MP3 listedMp3 = mp3Map.get(title);
        return listedMp3;
    }

    /*
     * Search for MP3 object from HashMap associated with given search term.
     * Searches the HashMap for partial matches based upon the given search
     * term. Returns MP3 object if found, null if not found.
     */
    @Override
    public List<MP3> searchLibrary(String searchTerm) throws MP3LibraryDaoException {
        loadLibray();
        List<MP3> searchResults = new ArrayList<>();
        // get the Set of keys from the map
        Set<String> keys = mp3Map.keySet();
        // iterate through Set and add MP3 object to ArrayList if it partially matches
        // searchTerm
        for (String k : keys) {
            if (k.startsWith(searchTerm)) {
                MP3 matchedMp3 = mp3Map.get(k);
                searchResults.add(matchedMp3);
            }
        }
        return searchResults;
    }

    // DATA MARSHALLING AND UNMARSHALLING ------------------------------------------
    // convert line of text in file to MP3 object
    private MP3 unmarshallMp3(String mp3AsText) {
        // split each line on the delimiter
        String[] mp3Tokens = mp3AsText.split(DELIMITER);

        // get HashMap key value from mp3Token array
        String title = mp3Tokens[0];

        // instantiate new MP3 object with title passed in the constructor
        MP3 mp3FromFile = new MP3(title);

        // use setters to input file text into MP3 object fields
        mp3FromFile.setReleaseDate(mp3Tokens[1]);
        mp3FromFile.setAlbum(mp3Tokens[2]);
        mp3FromFile.setArtistName(mp3Tokens[3]);
        mp3FromFile.setGenre(mp3Tokens[4]);
        mp3FromFile.setUserRatingNote(mp3Tokens[5]);

        return mp3FromFile;
    }

    // read library file into memory in HashMap
    private void loadLibray() throws MP3LibraryDaoException {
        // declare new Scanner object to read file
        Scanner sc;

        // try to load file, if not, throw our custom error and not the default 
        // (this hides implementation details and maintains encapsulation)
        try {
            // instantiate new Scanner object
            sc = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new MP3LibraryDaoException("-_- Could not load library data into memory.", e);
        }

        // currentLine holds most recent line read from file
        String currentLine;

        // currentMp3 holds most recent MP3 unmarshalled
        MP3 currentMp3;

        // go through LIBRARY_FILE line by line, decoding each line into an MP3 object
        // add MP3 objects to HashMap
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentMp3 = unmarshallMp3(currentLine);
            mp3Map.put(currentMp3.getTitle(), currentMp3);
        }
        // close scanner
        sc.close();

    }

    // convert MP3 object to line of text
    private String marshallMp3(MP3 anMp3) {
        // get title first because that is the key when we unmarshall
        String mp3AsText = anMp3.getTitle() + DELIMITER;

        // get all other variables and convert to string for file insertion
        mp3AsText += anMp3.getReleaseDate() + DELIMITER;
        mp3AsText += anMp3.getAlbum() + DELIMITER;
        mp3AsText += anMp3.getArtistName() + DELIMITER;
        mp3AsText += anMp3.getGenre() + DELIMITER;
        mp3AsText += anMp3.getUserRatingNote();

        return mp3AsText;

    }

    // write HashMap memory into library file
    private void writeLibrary() throws MP3LibraryDaoException {
        // declare PrintWriter
        PrintWriter out;

        // try to save to library file, if cannot throw our error
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new MP3LibraryDaoException("Could not save MP3 data.", e);
        }

        // write out MP3 objects to library file
        String mp3AsText;
        // call listAllMp3s method to return ArrayList of MP3 objects and
        // store in variable
        List<MP3> mp3List = this.listAllMp3s();
        // loop through all MP3 objects in ArrayList and write each to file
        for (MP3 currentMp3 : mp3List) {
            mp3AsText = marshallMp3(currentMp3);
            out.println(mp3AsText);
            // force PrintWriter to write to library file
            out.flush();
        }
        out.close();
    }

}
