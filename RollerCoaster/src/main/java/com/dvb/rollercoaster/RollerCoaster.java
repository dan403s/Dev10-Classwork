/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.rollercoaster;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class RollerCoaster {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("We're going to go on a roller coaster...");
        System.out.println("Let me know when you want to get off...!");

        String keepRiding = "yes";
        int loopsLooped = 0;
        while (keepRiding.equals("yes") /* keepRiding.equals("y") */) {
            System.out.println("WHEEEEEEEEEEEEEeEeEEEEeEeeee.....!!!");
            System.out.print("Want to keep going? (yes/no) :");
            keepRiding = userInput.nextLine();
            loopsLooped++;
        }

        System.out.println("Wow, that was FUN!");
        System.out.println("We looped that loop " + loopsLooped + " times!!");
    }
    // if you change the condition to check for a "n", the loop will never run
    //there is no int in front of loopsLooped when we assigned it a value in the loop because it was declared outside the loop, if we put it inside the loop and set it to 0, it would always be zero

}
