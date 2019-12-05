package com.dvb.guessme;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class GuessMe {
    
    public static void main(String[] args) {
        int pickedInt = 25;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Guess my number: ");
        String guessString = sc.nextLine();
        int guessInt = Integer.parseInt(guessString);
        
        System.out.println("Your Guess: " + guessInt);
        
        if(guessInt == pickedInt) {
            System.out.println("Wow, nice guess. That's it!");
        } else if(guessInt < pickedInt) {
            System.out.println("Too low. I chose " + pickedInt + ".");
        } else if(guessInt > pickedInt) {
            System.out.println("Too high. I chose " + pickedInt + ".");
        }
    }
    
}
