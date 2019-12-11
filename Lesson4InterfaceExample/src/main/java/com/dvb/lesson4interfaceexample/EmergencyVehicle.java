/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.lesson4interfaceexample;

/**
 *
 * @author Daniel Bart
 */
public interface EmergencyVehicle extends Vehicle {

    public void flashersOn();

    public void flashersOff();

    public void sirenOn();

    public void sirenOff();

}
