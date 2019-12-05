/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.lazyteenager;

import java.util.Random;

/**
 *
 * @author Daniel Bart
 */
public class LazyTeenager {
    
    public static void main(String[] args) {
        Random randomizer = new Random();
        
        int count = 1;
        int chanceCheckOutOf20 = 1;
        boolean isClean = false;
        
        do {
            System.out.println("Clean your room!! (x" + count + ")");
            int chance = randomizer.nextInt(20);
            if(chance < chanceCheckOutOf20) {
                System.out.println("FINE! I'll CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS.");
                isClean = true;
                break;
            }
            count++;
            chanceCheckOutOf20++;
        } while(count < 16);
        
        if(!isClean) {
            System.out.println("Clean your room!! That's IT, I'm doing it!!! YOU'RE GROUNDED AND I'M TAKING YOUR XBOX!");
        }
    }
    
}
