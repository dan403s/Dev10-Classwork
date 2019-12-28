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
public class CountXX {

    public int countXX(String str) {
        char[] charArr = str.toCharArray();
        int count = 0;

        for (int i = 0; i < charArr.length - 1; i++) {
            if (charArr[i] == 'x' && charArr[i + 1] == 'x') {
                count++;
            }
        }
        return count;
    }

}
