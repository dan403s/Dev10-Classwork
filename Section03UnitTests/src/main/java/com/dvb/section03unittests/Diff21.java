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
public class Diff21 {

    public int diff21(int n) {
        int absVal = Math.abs(21 - n);
        if(n > 21) {
            return absVal * 2;
        } else {
            return absVal;
        }
        
    }

}
