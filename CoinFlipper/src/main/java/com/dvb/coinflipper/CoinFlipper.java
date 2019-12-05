/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.coinflipper;

import java.util.Random;

/**
 *
 * @author Daniel Bart
 */
public class CoinFlipper {
    
    public static void main(String[] args) {
        Random randomizer = new Random();
        
        int count = 0;
        System.out.println("Ready, Set, Flip....!!");
        
        while(count < 100) {
            boolean randomBoolean = randomizer.nextBoolean();
            if(randomBoolean == true) {
                System.out.println("You got heads.");
            } else {
                System.out.println("You got tails.");
            }
            count++;
        }
    }
    
}
