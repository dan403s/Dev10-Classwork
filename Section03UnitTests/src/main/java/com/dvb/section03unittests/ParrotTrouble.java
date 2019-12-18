/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.section03unittests;

/**
 *
 * @author Daniel Bart
 */
public class ParrotTrouble {

    public boolean parrotTrouble(boolean isTalking, int hour) {
        if(hour < 7 || hour > 20) {
            if(isTalking) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
