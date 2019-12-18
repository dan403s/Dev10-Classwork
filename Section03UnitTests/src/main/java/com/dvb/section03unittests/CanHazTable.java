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
public class CanHazTable {

    public int canHazTable(int yourStyle, int dateStyle) {
        if(yourStyle >= 8 || dateStyle >= 8) {
            return 2;
        } else if (yourStyle <= 2 || dateStyle<= 2) {
            return 0;
        } else {
            return 1;
        }
    }

}
