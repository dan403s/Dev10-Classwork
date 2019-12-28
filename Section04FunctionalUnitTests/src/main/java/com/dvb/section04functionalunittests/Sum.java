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
public class Sum {
    
    public int sum(int[] numbers) {
        int output = 0;
        
        for(int number : numbers) {
            output += number;
        }
        
        return output;
    }
    
}
