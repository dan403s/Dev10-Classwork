/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.lovesme;

/**
 *
 * @author Daniel Bart
 */
public class LovesMe {
    
    public static void main(String[] args) {
        int count = 0;
        String lovesMe = "It LOVES me!";
        String lovesMeNot = "It LOVES me NOT!!!";
        
        System.out.println("Well here goes nothing...");
        
        while(count < 34) {
            if(count % 2 == 0) {
                System.out.println(lovesMeNot);
            } else {
                System.out.println(lovesMe);
            }
            count++;
        }
        
        System.out.println("I knew it! It LOVES MEEEEEEEEEEEEEE!");
    }
    
    // I used a while loop because it did not have to run at least once and since I knew the number of iterations
}
