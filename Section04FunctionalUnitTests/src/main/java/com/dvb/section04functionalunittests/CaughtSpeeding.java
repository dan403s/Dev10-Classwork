/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.section04functionalunittests;

/**
 *
 * @author Daniel Bart
 */
public class CaughtSpeeding {
    
    public int caughtSpeeding(int speed, boolean isBirthday) {
        if(!isBirthday) {
            if(speed <= 60) {
                return 0;
            } else if (speed >= 61 && speed <= 80) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if(speed <= 65) {
                return 0;
            } else if (speed >= 66 && speed <= 85) {
                return 1;
            } else {
                return 2;
            }
        }
    }
    
}
