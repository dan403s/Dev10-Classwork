/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.classmodeling;

/**
 *
 * @author Daniel Bart
 */
public class House3D {
    
    private int numberOfRooms;
    private int numberOfWindows;
    private String colorOfTrim;
    private String colorOfExterior;
    private int numberOfFloors;
    private double length;
    private double width;

    public House3D(int numberOfRooms, int numberOfWindows, String colorOfTrim, String colorOfExterior, int numberOfFloors, double length, double width) {
        this.numberOfRooms = numberOfRooms;
        this.numberOfWindows = numberOfWindows;
        this.colorOfTrim = colorOfTrim;
        this.colorOfExterior = colorOfExterior;
        this.numberOfFloors = numberOfFloors;
        this.length = length;
        this.width = width;
    }

    public double calculateSquareFootage() {
        return this.length * this.width;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfWindows() {
        return numberOfWindows;
    }

    public void setNumberOfWindows(int numberOfWindows) {
        this.numberOfWindows = numberOfWindows;
    }

    public String getColorOfTrim() {
        return colorOfTrim;
    }

    public void setColorOfTrim(String colorOfTrim) {
        this.colorOfTrim = colorOfTrim;
    }

    public String getColorOfExterior() {
        return colorOfExterior;
    }

    public void setColorOfExterior(String colorOfExterior) {
        this.colorOfExterior = colorOfExterior;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    
}
