/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.differentkettleoffish;

/**
 *
 * @author Daniel Bart
 */
public class DifferentKettleOfFish {

    public static void main(String[] args) {
        System.out.println("WHILE LOOP----------------");
        int fish = 1;
        while (fish < 11) {
            if (fish == 3) {
                System.out.println("RED fish!");
            } else if (fish == 4) {
                System.out.println("BLUE fish!");
            } else {
                System.out.println(fish + " fish!");
            }

            fish++;
        }
        
        System.out.println("FOR LOOP----------------");
        
        for(int i = 1; i <= 10; i++) {
            if (i == 3) {
                System.out.println("RED fish!");
            } else if (i == 4) {
                System.out.println("BLUE fish!");
            } else {
                System.out.println(i + " fish!");
            }
        }
        
        // the count moved from outside the loop into the initialization
        // the count incrementor moved from inside the loop to the incrementation
        // the condition stayed in the same place
    }

}
