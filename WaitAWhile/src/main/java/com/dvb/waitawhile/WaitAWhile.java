/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.waitawhile;

/**
 *
 * @author Daniel Bart
 */
public class WaitAWhile {
    
    public static void main(String[] args) {
        int timeNow = 5;
        int bedTime = 10;

        while (timeNow < bedTime) {
            System.out.println("It's only " + timeNow + " o'clock!");
            System.out.println("I think I'll stay up just a liiiiittle longer....");
            timeNow++; // Time passes
        }

        System.out.println("Oh. It's " + timeNow + " o'clock.");
        System.out.println("Guess I should go to bed ...");
    }
    // if you change bedTime to 11, it will run the loop one more time
    // if you change timeNow to 11, it will not run the loop because the condition is false
    // if you comment out timeNow++, infinite loop
}
