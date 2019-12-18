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
public class PlayOutside {

    public boolean playOutside(int temp, boolean isSummer) {
        if(isSummer) {
            if(temp >= 60 && temp <= 100) {
                return true;
            } else {
                return false;
            }
        } else {
            if(temp >= 60 && temp <= 90) {
                return true;
            } else {
                return false;
            }
        }
    }

}
