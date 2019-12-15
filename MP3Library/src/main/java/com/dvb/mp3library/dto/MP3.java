/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3library.dto;

/**
 *
 * @author Daniel Bart
 */
public class MP3 {

    // declare variables and initialize if necessary
    // title will be key in HashMap; passed into constructor and only have getter
    private String title;

    // these variables will have getters and setters both
    private String releaseDate;
    private String album;
    private String artistName;
    private String genre;
    private String userRatingNote;

    // constructor takes in title
    public MP3(String title) {
        this.title = title;
    }

    // only getter for title
    public String getTitle() {
        return title;
    }

    // getters and setters for all other variables
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUserRatingNote() {
        return userRatingNote;
    }

    public void setUserRatingNote(String userRatingNote) {
        this.userRatingNote = userRatingNote;
    }

}
