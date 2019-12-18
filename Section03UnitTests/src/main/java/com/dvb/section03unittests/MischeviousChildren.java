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
public class MischeviousChildren {

    public boolean areWeInTrouble(boolean aSmile, boolean bSmile) {
        if(aSmile && bSmile) {
            return true;
        } else if(!aSmile && !bSmile) {
            return true;
        } else {
            return false;
        }
    }

}
