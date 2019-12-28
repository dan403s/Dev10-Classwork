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
public class PosNeg {

    public boolean posNeg(int a, int b, boolean negative) {
        if (!negative) {
            if (a < 0 ^ b < 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (a < 0 && b < 0) {
                return true;
            } else {
                return false;
            }
        }
    }

}
