/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.hiddennuts;

import java.util.Random;

/**
 *
 * @author Daniel Bart
 */
public class HiddenNuts {

    public static void main(String[] args) {
        HiddenNuts instance1 = new HiddenNuts();
        
        String[] hidingSpots = new String[100];
        
        Random squirrel = new Random();
        
        hidingSpots[squirrel.nextInt(hidingSpots.length)] = "Nut";
        System.out.println("The nut has been hidden ...");

        String nutLocation = instance1.findMyNut(hidingSpots);
        
        System.out.println(nutLocation);
        
        
    }
    
    public String findMyNut(String[] array) {
        int nutLocation = 0;
        String[] newArray = array;
        
        for(int i = 0; i < newArray.length; i++) {
            if(newArray[i] != null) {
                nutLocation = i;
                break;
            }
        }
        
        return "Found it! It's in spot# " + nutLocation;
    }

}
