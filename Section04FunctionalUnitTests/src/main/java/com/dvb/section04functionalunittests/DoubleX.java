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
public class DoubleX {

    public boolean doubleX(String str) {
        char[] charArr = str.toCharArray();
        int xAtIndex = 0;

        for (int i = 0; i < charArr.length - 1; i++) {
            if (charArr[i] == 'x') {
                xAtIndex = i;
                break;
            }
        }

        if (charArr[xAtIndex + 1] == 'x') {
            return true;
        } else {
            return false;
        }

    }

}
