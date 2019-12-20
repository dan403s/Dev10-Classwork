/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3library.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Daniel Bart
 */
public class MP3 {

    // declare variables and initialize if necessary
    // title will be key in HashMap; passed into constructor and only have getter
    private String title;

    // these variables will have getters and setters both
    private LocalDate releaseDate;
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
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.title);
        hash = 41 * hash + Objects.hashCode(this.releaseDate);
        hash = 41 * hash + Objects.hashCode(this.album);
        hash = 41 * hash + Objects.hashCode(this.artistName);
        hash = 41 * hash + Objects.hashCode(this.genre);
        hash = 41 * hash + Objects.hashCode(this.userRatingNote);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MP3 other = (MP3) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.album, other.album)) {
            return false;
        }
        if (!Objects.equals(this.artistName, other.artistName)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.userRatingNote, other.userRatingNote)) {
            return false;
        }
        return true;
    }

    
    
}
