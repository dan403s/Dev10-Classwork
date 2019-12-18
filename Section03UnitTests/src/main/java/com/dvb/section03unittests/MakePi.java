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
public class MakePi {

    public int[] makePi(int n) {
        final int[] PI = {3,1,4,1,5,9,2,6,5,3,5,9};
        int[] piArray = new int[n];
        for(int i = 0; i < n; i++){
            piArray[i] = PI[i];
        }
        return piArray;
    }
    
    

}
