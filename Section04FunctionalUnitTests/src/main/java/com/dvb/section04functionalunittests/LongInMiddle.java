package com.dvb.section04functionalunittests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel Bart
 */
public class LongInMiddle {
    
    public String longInMiddle(String a, String b) {
        int StringACount = a.length();
        int StringBCount = b.length();
        
        if (StringACount > StringBCount) {
            return b + a + b;
        } else {
            return a + b + a;
        }
    }
    
}
