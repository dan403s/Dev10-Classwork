/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessmemore;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class GuessMeMore {

    public static void main(String[] args) {
        Random randomizer = new Random();
        Scanner sc = new Scanner(System.in);

        int min = -10;
        int max = 10;
        max += 1;
        int pickedInt = randomizer.nextInt(max - min) + min;
        int count = 0;
        boolean isWon = false;

        while (count < 5 && !isWon) {
            System.out.println("Guess my number: ");
            String guessString = sc.nextLine();
            int guessInt = Integer.parseInt(guessString);

            System.out.println("Your Guess: " + guessInt);

            if (guessInt == pickedInt) {
                System.out.println("Wow, nice guess. That's it!");
                isWon = true;
            } else if (guessInt < pickedInt) {
                System.out.println("Too low.");
            } else if (guessInt > pickedInt) {
                System.out.println("Too high.");
            }
            
            count++;
        }
        
        if(!isWon) {
            System.out.println("Sorry, you didn't win. The number was " + pickedInt + ".");
        }

    }

}
