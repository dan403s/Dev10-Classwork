/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3library.ui;

import com.dvb.mp3library.dto.MP3;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class MP3LibraryView {

    // declare new UserIOConsoleImpl object
    UserIO io;

    // constructor for dependency injection
    public MP3LibraryView(UserIO io) {
        this.io = io;
    }

    // INITIAL MENU ----------------------------------------------------------------
    // print menu and return user selection as int
    public int printMenuAndGetSelection() throws NumberFormatException {

        io.print("\nMain Menu");
        io.print("1. Add MP3");
        io.print("2. Remove MP3");
        io.print("3. Edit MP3");
        io.print("4. List All MP3s And Details");
        io.print("5. List MP3 Details For Single MP3");
        io.print("6. Search For MP3 Title (Partial Title OK)");
        io.print("7. Find all MP3s released in the last N years");
        io.print("8. Find all the MP3s in a given genre");
        io.print("9. Find all the MP3s by a given artist");
        io.print("10.Find all the MP3s released on a particular album ");
        io.print("11. Find the average age of the MP3s in the collection");
        io.print("12. Find the newest MP3 in your collection");
        io.print("13. Find the oldest MP3 in your collection");
        io.print("14. Find the average number of notes associated with MP3s in your collection");
        io.print("15. Exit");

        try {
            // return user selection in menuSelection as int
            return io.readInt("\nPlease select from the above choices.", 1, 15);
        } catch (NumberFormatException ex) {
            return 0;
        }

    }

    // GENERAL PRESS ENTER TO CONTINUE PROMPT
    public void displayContinueToMenuBanner() {
        io.readString("\nPlease hit enter to continue.");
    }

    // ADD MP3 ---------------------------------------------------------------------
    // display submenu banner
    public void displayAddMp3Banner() {
        io.print("\nAdd MP3 Menu");
    }

    // prompt user for new MP3 object details and return new MP3 object
    public MP3 getNewMp3Info() throws UnsupportedIOFormatException {
        // prompt user for MP3 details
        String title = io.readStringNoBlanks("\nPlease enter title: ");
        LocalDate releaseDate = io.readReleaseDate("Please enter release date in format MM/DD/YYYY: ");
        String album = io.readStringNoBlanks("Please enter album: ");
        String artistName = io.readStringNoBlanks("Please enter artist name: ");
        String genre = io.readStringNoBlanks("Please enter genre: ");
        String userRatingNote = io.readStringNoBlanks("Please enter any additional information: ");

        // instantiate new MP3 object
        MP3 currentMp3 = new MP3(title);

        // set MP3 details from above
        currentMp3.setReleaseDate(releaseDate);
        currentMp3.setAlbum(album);
        currentMp3.setArtistName(artistName);
        currentMp3.setGenre(genre);
        currentMp3.setUserRatingNote(userRatingNote);

        return currentMp3;
    }

    // prompt user to press enter to continue
    public void displayAddMp3SuccessBanner() {
        io.readString("\nMP3 successfully added. Please hit enter to continue.");
    }

    // REMOVE MP3 ------------------------------------------------------------------
    // displays submenu for deleting MP3 object
    public void displayRemoveMp3Banner() {
        io.print("\nRemove MP3 Menu");
    }

    // gets user input for title selection for deleteMP3 Method
    public String getTitleForDeleteMp3() {
        String title = io.readString("\nPlease enter title of MP3 to delete: ");
        return title;
    }

    // get user input for delete verification
    public String getRemoveMp3Verification() {
        String removeVerification = io.readString("\nReally Delete (y/n)? ");
        return removeVerification;
    }

    // display success banner for deleting MP3 object
    public void displayRemoveMp3SuccessBanner() {
        io.readString("\nMP3 Deleted. Please hit enter to continue.");
    }

    // display deleting cancel banner
    public void displayCancelRemoveMp3Banner() {
        io.readString("\nMP3 NOT Deleted. Please hit enter to continue.");
    }

    // EDIT MP3 --------------------------------------------------------------------
    // display submenu for editing MP3 object
    public void displayEditMp3Banner() {
        io.print("\nEdit MP3 Menu");
    }

    // get user input for title selection for changeMp3 method
    public String getTitleForEditMp3() {
        String title = io.readString("\nPlease enter the title of MP3 to edit: ");
        return title;
    }

    // gets user input for editing of MP3 object
    public MP3 getEditedMp3Info(String title) throws UnsupportedIOFormatException {

        // prompt user for MP3 details
        LocalDate releaseDate = io.readReleaseDate("\nPlease enter release date in format MM/DD/YYYY: ");
        String album = io.readStringNoBlanks("Please enter album: ");
        String artistName = io.readStringNoBlanks("Please enter artist name: ");
        String genre = io.readStringNoBlanks("Please enter genre: ");
        String userRatingNote = io.readStringNoBlanks("Please enter any additional information: ");

        // instantiate new MP3 object
        MP3 editedMp3 = new MP3(title);

        // set MP3 details from above
        editedMp3.setReleaseDate(releaseDate);
        editedMp3.setAlbum(album);
        editedMp3.setArtistName(artistName);
        editedMp3.setGenre(genre);
        editedMp3.setUserRatingNote(userRatingNote);

        return editedMp3;

    }

    // display banner to return to main menu
    public void displayEditSuccessBanner() {
        io.readString("\nMP3 successfully edited. Please hit enter to continue.");
    }

    // LIST ALL MP3s AND DETAILS ---------------------------------------------------
    // display submenu for list all MP3s
    public void displayAllMp3sBanner() {
        io.print("\nList All MP3s Menu");
    }

    // use ArrayList of MP3 objects and display info for each to screen
    public void displayMp3List(List<MP3> mp3List) {
        // if ArrayList is empty, display message to user, if not, iterate through ArrayList and print out details to user
        if (!mp3List.isEmpty()) {
            for (MP3 currentMp3 : mp3List) {
                displayMp3Details(currentMp3);
            }
        } else {
            io.print("\nNo records found...");
        }

    }

    // LIST MP3 DETAILS FOR SINGLE MP3 ---------------------------------------------
    // display submenu for list all MP3s
    public void displayMp3DetailsBanner() {
        io.print("\nList MP3 Details");
    }

    // prompt user for title
    public String getTitleForMp3Details() {
        String title = io.readString("\nPlease enter the title of the MP3 you wish to view: ");
        return title;
    }

    // display details for MP3 object
    public void displayMp3Details(MP3 mp3) {
        // if MP3 object is null, print out message, if not, print out details
        if (mp3 != null) {
            io.print("\nTitle: " + mp3.getTitle());
            io.print("Release Date: " + mp3.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            io.print("Album: " + mp3.getAlbum());
            io.print("Artist Name: " + mp3.getArtistName());
            io.print("Genre: " + mp3.getGenre());
            io.print("User Rating Note: " + mp3.getUserRatingNote());
        } else {
            io.print("\nNo such MP3...sorry.");
        }

    }

    // SEARCH FOR MP3 TITLE (PARTIAL TITLE OK) -------------------------------------
    // display submenu for searching for MP3
    public void displayMp3SearchBanner() {
        io.print("\nSearch For MP3 Menu");
    }

    // prompt user for search term
    public String getSearchTermForMp3Details() {
        String term = io.readString("\nPlease enter the search term for a title (partial OK): ");
        return term;
    }

    // EXIT ------------------------------------------------------------------------
    public void displayExitBanner() {
        io.print("\nThank you for listening.");
    }

    // UNKNOWN COMMAND -------------------------------------------------------------
    public void displayUnknownCommandBanner() {
        io.print("\nUnknown Command!!!");
    }

    // ERRORS ----------------------------------------------------------------------
    public void displayErrorMessage(String errorMsg) {
        io.print("\nERROR!!!");
        io.print(errorMsg);
    }

    // Find all MP3s released in the last N years ----------------------------------
    public void displayMp3ReleasedInLastNYearsBanner() {
        io.print("\nFind all MP3s released in the last N years menu");
    }

    public int getYearsForMp3sReleasedInLastNYears() {
        return io.readInt("Please enter the number of years: ");
    }

    // Find all the MP3s in a given genre ------------------------------------------
    // When searching by genre, the MP3s should be sorted into separate data structures by artist.
    public void displayListMp3ByGenreBanner() {
        io.print("\nFind all the MP3s in a given genre");
    }

    public String getGenreForListAllMp3sByGenre() {
        String name = io.readString("\nPlease enter the genre name: ");
        return name;
    }

    public void displayMp3HashMap(Map<String, List<MP3>> hashMap) {
        if (hashMap != null) {
            Set<String> artistKeySet = hashMap.keySet();
            for (String key : artistKeySet) {
                io.print("\nArtist Name IS: " + key + " =============================");
                List<MP3> mp3List = hashMap.get(key);
                displayMp3List(mp3List);
            }
        } else {
            io.print("No such genre...sorrrrrrrry");
        }

    }

    // Find all the MP3s by a given artist -----------------------------------------
    public void displayListMp3sByArtistBanner() {
        io.print("\nFind all the MP3s by a given artist menu");
    }

    public String getArtistForListAllMp3sByArtist() {
        String name = io.readString("\nPlease enter the artist name: ");
        return name;
    }

    // Find all the MP3s released on a particular album ----------------------------
    public void displayListMp3sByAlbumBanner() {
        io.print("\nFind all the MP3s on a given album menu");
    }

    public String getAlbumForListAllMp3sByAlbum() {
        String name = io.readString("\nPlease enter the album name: ");
        return name;
    }

    // Find the average age of the MP3s in the collection --------------------------
    public void displayListAverageAgeOfMp3sBanner() {
        io.print("\nFind the average age of the MP3s in the collection menu");
    }

    public void displayListAverageAgeOfMp3s(double age) {
        io.print("The average age of the MP3s is " + age + " years.");
    }

    // Find the newest MP3 in your collection --------------------------------------
    public void displayListNewestMp3Banner() {
        io.print("\nFind the newest MP3 in your collection menu");
    }

    // Find the oldest MP3 in your collection --------------------------------------
    public void displayListOldestMp3Banner() {
        io.print("\nFind the oldest MP3 in your collection menu");
    }

    // Find the average number of notes associated with MP3s in your collection ----
    public void displayListAverageNotesBanner() {
        io.print("\nFind the average number of notes associated with MP3s in your collection");
    }

    public void displayListAverageNotes(Double notes) {
        io.print("\nThe average number of notes per MP3 is " + notes + ".");
    }

}
