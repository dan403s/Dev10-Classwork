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
public class MultipleEndings {

    public String multipleEndings(String str) {
        char[] charArr = str.toCharArray();

        String output = "";

        for (int i = 0; i < 3; i++) {
            output += charArr[charArr.length - 2];
            output += charArr[charArr.length - 1];
            
        }
        
        return output;
    }

}
