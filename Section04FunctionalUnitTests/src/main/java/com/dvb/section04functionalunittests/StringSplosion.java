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
public class StringSplosion {
    
    public String stringSplosion(String str) {
        String output = "";
        
        for (int i = 0; i < str.length(); i++) {
            output += str.substring(0, i + 1);
        }
        
        return output;
    }
    
}
