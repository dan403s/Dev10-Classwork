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
public class AirplaneATC {
    
    private double airSpeed;
    private String callSign;
    private String direction;
    private String destination;
    private String origin;
    private double altitude;
    private boolean willCrash;

    public AirplaneATC(double airSpeed, String callSign, String direction, String destination, String origin, double altitude) {
        this.airSpeed = airSpeed;
        this.callSign = callSign;
        this.direction = direction;
        this.destination = destination;
        this.origin = origin;
        this.altitude = altitude;
    }
    
    // determine if plane will crash
    public boolean willAirplaneCrash() {
        boolean willAirplaneCrash = false;
        return willAirplaneCrash;
    }

    public double getAirSpeed() {
        return airSpeed;
    }

    public void setAirSpeed(double airSpeed) {
        this.airSpeed = airSpeed;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    
}
