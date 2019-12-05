/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.bewarethekraken;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class BewareTheKraken {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random randomizer = new Random();
        int randomNumber;
        String userStop;
        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");

        int depthDivedInFt = 0;

        // Turns out the ocean is only so deep, 36200 at the deepest survey,
        // so if we reach the bottom ... we should probably stop.
        while (depthDivedInFt < 36200) {
            System.out.println("So far, we've swum " + depthDivedInFt + " feet");

            System.out.println("Do you wanna stop? (y/n)");
            userStop = sc.nextLine();
            
            if(userStop.equals("y")) {
                System.out.println("Surfacing..........................");
                break;
            }
            
            randomNumber = randomizer.nextInt(4 - 1) + 1;
            switch(randomNumber) {
                case 1:
                    System.out.println("There's a blue fish.");
                    break;
                case 2:
                    System.out.println("There's a yellow fish.");
                    break;
                case 3:
                    System.out.println("There's an ugly fish.");
                    break;
                default:
            }
            
            if (depthDivedInFt >= 20000) {
                System.out.println("Uhhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }

            // I can swim, really fast! 500ft at a time!
            depthDivedInFt += 1000;
        }
        System.out.println("");
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }

    // if we turn the loop's condition to true, it ends at 20,000 regardless due to the if statement's break
}
