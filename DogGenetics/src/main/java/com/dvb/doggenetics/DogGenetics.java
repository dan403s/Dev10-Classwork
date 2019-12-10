/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.doggenetics;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class DogGenetics {

    // main method
    public static void main(String[] args) {
        // instantiate new scanner object
        Scanner sc = new Scanner(System.in);

        // instantiate new random object
        Random randomizer = new Random();

        // declare variables and initialize if necessary
        String dogName;
        int randomNumberSum = 0, remainingNumber;
        String[] dogTypes = {"St. Bernard", "Chihuahua", "Dramatic RedNosed Asian Pug", "Common Cur", "King Doberman"};

        // prompt user for dog name and store in variable
        System.out.print("What is your dog's name? ");
        dogName = sc.nextLine();

        // print out text before loop
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.");
        System.out.println("\n" + dogName + " is:\n");

        // loop through dog types and print random percentages that add up to 100%     
        for (int i = 0; i < dogTypes.length; i++) {
            if (i >= 0 && (i < dogTypes.length - 1)) {
                int randomNumber = randomizer.nextInt(101 - randomNumberSum);
                System.out.println(randomNumber + "% " + dogTypes[i]);
                randomNumberSum += randomNumber;
            } else {
                remainingNumber = 100 - randomNumberSum;
                System.out.println(remainingNumber + "% " + dogTypes[i]);
            }
        }

        // print out text
        System.out.println("\nWow, that's QUITE the dog!");
    }

}
