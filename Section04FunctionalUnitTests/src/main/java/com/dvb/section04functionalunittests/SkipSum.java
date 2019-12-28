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
public class SkipSum {

    public int skipSum(int a, int b) {
        int output = a + b;

        if (output >= 10 && output <= 19) {
            output = 20;
        }

        return output;
    }

}
