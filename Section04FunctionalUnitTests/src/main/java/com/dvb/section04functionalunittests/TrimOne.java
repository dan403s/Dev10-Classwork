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
public class TrimOne {

    public String trimOne(String str) {
        String output = "";
        output = str.substring(1, str.length() - 1);
        return output;
    }

}
