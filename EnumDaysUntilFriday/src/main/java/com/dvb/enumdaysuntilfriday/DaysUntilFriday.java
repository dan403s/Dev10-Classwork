/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.enumdaysuntilfriday;

/**
 *
 * @author Daniel Bart
 */
public class DaysUntilFriday {

    public int calculateDaysToFriday(String day) throws UnsupportedDayException {

        try {
            DaysOfTheWeek daySelected = DaysOfTheWeek.valueOf(day.toUpperCase());

            switch (daySelected) {
                case MONDAY:
                    return 4;
                case TUESDAY:
                    return 3;
                case WEDNESDAY:
                    return 2;
                case THURSDAY:
                    return 1;
                case FRIDAY:
                    return 0;
                case SATURDAY:
                    return 6;
                case SUNDAY:
                    return 5;
                default:
                    throw new UnsupportedDayException("You typed other...that is not good for you.");
            }
        } catch (IllegalArgumentException e) {
            throw new UnsupportedDayException("Could not find day in enum...", e);
        }

    }

}
