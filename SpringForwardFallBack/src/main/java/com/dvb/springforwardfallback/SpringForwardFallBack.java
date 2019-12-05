/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.springforwardfallback;

/**
 *
 * @author Daniel Bart
 */
public class SpringForwardFallBack {

    public static void main(String[] args) {
        System.out.println("It's Spring...!");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + ", ");
        }

        System.out.println("\nOh no, it's fall...");
        for (int i = 10; i > 0; i--) {
            System.out.print(i + ", ");
        }
    }
    
    // the start/stop range for spring is 0 - 9
    // the start/stop range for fall is 10 - 1

}
