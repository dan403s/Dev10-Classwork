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
public class NearHundred {
    
    public boolean nearHundred(int n) {
        int absoluteValue = Math.abs(n);
        
        if (absoluteValue >= 90 && absoluteValue <= 110 || absoluteValue >= 190 && absoluteValue <= 210) {
            return true;
        } else {
            return false;
        }
    }
    
}
