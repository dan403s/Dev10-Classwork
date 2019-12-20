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
public class App {

    public static void main(String[] args) {

        Prompter prompter = new Prompter();
        DaysUntilFriday daysUntilFriday = new DaysUntilFriday();
        boolean isValid = true;

        do {
            try {
                String userResponse = prompter.readString("What is the day of the week? ");
                int days = daysUntilFriday.calculateDaysToFriday(userResponse);
                System.out.println("There are " + days + " days until Friday.");
                isValid = false;
            } catch (UnsupportedDayException e) {
                System.out.println(e.getMessage());
            }
        } while (isValid);

    }

}
