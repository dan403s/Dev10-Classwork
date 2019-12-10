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
public class AirplaneSimulator {
    
    private String airplaneType;
    private double maxSpeed;
    private double minSpeed;
    private String direction;
    private double currentSpeed;
    private int previousNumberOfCrashes;
    private int numberOfBlinkingLightsAndRotatingKnobs;

    public AirplaneSimulator(String airplaneType, double maxSpeed, double minSpeed, int previousNumberOfCrashes, int numberOfBlinkingLightsAndRotatingKnobs) {
        this.airplaneType = airplaneType;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        this.previousNumberOfCrashes = previousNumberOfCrashes;
        this.numberOfBlinkingLightsAndRotatingKnobs = numberOfBlinkingLightsAndRotatingKnobs;
    }
    
    // change speed
    public void changeSpeed(double newSpeed) {
        
    }
    
    // change direction
    public void changeDirection(String newDirection) {
        
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public int getPreviousNumberOfCrashes() {
        return previousNumberOfCrashes;
    }

    public void setPreviousNumberOfCrashes(int previousNumberOfCrashes) {
        this.previousNumberOfCrashes = previousNumberOfCrashes;
    }

    public int getNumberOfBlinkingLightsAndRotatingKnobs() {
        return numberOfBlinkingLightsAndRotatingKnobs;
    }

    public void setNumberOfBlinkingLightsAndRotatingKnobs(int numberOfBlinkingLightsAndRotatingKnobs) {
        this.numberOfBlinkingLightsAndRotatingKnobs = numberOfBlinkingLightsAndRotatingKnobs;
    }
    
    
    
}
