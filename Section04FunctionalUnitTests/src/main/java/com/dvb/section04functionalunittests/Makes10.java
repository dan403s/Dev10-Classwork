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
public class Makes10 {
    
    public boolean makes10(int a, int b) {
        int sum = a + b;
        
        if(a == 10 || b == 10 || sum == 10) {
            return true;
        } else {
            return false;
        }
    }
    
}
