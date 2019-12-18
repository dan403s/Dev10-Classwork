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
public class FrontTimes {

    public String frontTimes(String str, int n) {
        if(str.length() >= 3) {
            return str.substring(0, 3).repeat(n);
        } else {
            return str.repeat(n);
        }
    }

}
