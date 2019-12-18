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
public class SleepingIn {

    public boolean canSleepIn(boolean isWeekday, boolean isVacation) {
        if(!isWeekday || isVacation) {
            return true;
        } else {
            return false;
        }
    }

}
