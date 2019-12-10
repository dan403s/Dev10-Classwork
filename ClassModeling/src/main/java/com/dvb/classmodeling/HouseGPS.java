/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.classmodeling;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */

// Accessing house in GPS
public class HouseGPS {
    
    // read-only variables include streetNumber, streetName, aptNumber, city, state, zip - the house cannot change location
    private int streetNumber;
    private String streetName;
    private int aptNumber;
    private String city;
    private String state;
    private int zip;
    private Photos[] photos;
    private String neighborhoodCrimeLevel;
    private CurrentLocation currentLocation;
    
    public HouseGPS(Photos[] photos, String neighborhoodCrimeLevel) {
        this.photos = photos;
        this.neighborhoodCrimeLevel = neighborhoodCrimeLevel;
    }
    
    public void addToFavorites(int streetNumberToAdd, String streetNameToAdd, int aptNumberToAdd, String cityToAdd, String stateToAdd, int zipToAdd, String nickName) {
        
    }
    
    public List<String> calculateDirections(CurrentLocation currentLocation) {
        List<String> directions = new ArrayList<>();        
        return directions;
    }

    public Photos[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photos[] photos) {
        this.photos = photos;
    }

    public String getNeighborhoodCrimeLevel() {
        return neighborhoodCrimeLevel;
    }

    public void setNeighborhoodCrimeLevel(String neighborhoodCrimeLevel) {
        this.neighborhoodCrimeLevel = neighborhoodCrimeLevel;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getAptNumber() {
        return aptNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }
    
}
