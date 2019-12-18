/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.addressbook.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO)
 *
 * @author Daniel Bart
 */
public class Address {

    // declare variables and initialize if necessary
    // lastName will be key in HashMap; passed into constructor and only have getter
    private String lastName;

    // these variables will have getters and setters both
    private String firstName;
    private int streetNumber;
    private String streetName;
    private String city;
    private String state;
    private int zip;

    // constructor takes in lastName
    public Address(String lastName) {
        this.lastName = lastName;
    }

    // only getter for lastName
    public String getLastName() {
        return lastName;
    }

    // getters and setters for all other variables
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.lastName);
        hash = 79 * hash + Objects.hashCode(this.firstName);
        hash = 79 * hash + this.streetNumber;
        hash = 79 * hash + Objects.hashCode(this.streetName);
        hash = 79 * hash + Objects.hashCode(this.city);
        hash = 79 * hash + Objects.hashCode(this.state);
        hash = 79 * hash + this.zip;
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
        final Address other = (Address) obj;
        if (this.streetNumber != other.streetNumber) {
            return false;
        }
        if (this.zip != other.zip) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.streetName, other.streetName)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        return true;
    }
    
    

}
