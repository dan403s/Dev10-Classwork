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
public class AlarmClock {

    public String alarmClock(int day, boolean vacation) {
        String alarmTime = "";
        
        if (vacation) {
            switch(day) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    alarmTime = "10:00";
                    break;
                case 6:
                case 0:
                    alarmTime = "Off";
                    break;
            }
        } else {
            switch(day) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    alarmTime = "7:00";
                    break;
                case 6:
                case 0:
                    alarmTime = "10:00";
                    break;
            }
        }
        
        return alarmTime;
    }

}
