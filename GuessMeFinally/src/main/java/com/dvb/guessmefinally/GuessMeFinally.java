/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessmefinally;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class GuessMeFinally {

    public static void main(String[] args) {
        Random randomizer = new Random();
        Scanner sc = new Scanner(System.in);

        int min = -1;
        int max = 1;
        int pickedInt = randomizer.nextInt(max + 1 - min) + min;
        int count = 1;
        boolean isWon = false;
        
        System.out.println("I've chosen a number between " + min + " and " + max + ". Betcha can't guess it!");

        while (!isWon) {
            System.out.print("Your Guess: ");
            String guessString = sc.nextLine();
            int guessInt = Integer.parseInt(guessString);

            if (guessInt == pickedInt && count == 1) {
                System.out.println("Wow, nice guess. You got it in 1 try.");
                isWon = true;
            } else if (guessInt == pickedInt) {
                System.out.println("Finally! It's about time you got it in " + count + " tries.");
                isWon = true;
            } else if (guessInt < pickedInt) {
                System.out.println("Too low.");
            } else if (guessInt > pickedInt) {
                System.out.println("Too high.");
            }

            count++;
        }

    }

}
