/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.maybeitlovesme;

import java.util.Random;

/**
 *
 * @author Daniel Bart
 */
public class MaybeItLovesMe {
    
    public static void main(String[] args) {
        Random randomizer = new Random();
        int count = 0;
        int min = 13;
        int max = 89;
        max += 1;
        int numberOfPetals = randomizer.nextInt(max - min) + min;
        String lovesMe = "It LOVES me!";
        String lovesMeNot = "It LOVES me NOT!!!";
        boolean lovesMeBoolean = false;
        
        System.out.println("Well here goes nothing...");
        
        while(count < numberOfPetals) {
            if(count % 2 == 0) {
                System.out.println(lovesMeNot);
                lovesMeBoolean = false;
            } else {
                System.out.println(lovesMe);
                lovesMeBoolean = true;
            }
            count++;
        }
        
        if(lovesMeBoolean) {
        System.out.println("LOVES MEEEEEEEEEEEEEE!");
        } else {
            System.out.println("Awwwwwwwwwwwww.");
        }
    }
    
}
