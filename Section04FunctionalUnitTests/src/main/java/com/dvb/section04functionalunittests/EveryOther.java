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
public class EveryOther {
    
    public String everyOther(String str) {
        char[] charArr = str.toCharArray();
        
        String output = "";
        
        for(int i = 0; i < charArr.length; i++) {
            if(i % 2 == 0) {
                output += charArr[i];
            }
        }
        
        return output;
    }
    
}
