/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.highroller;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class HighRoller {

    public static void main(String[] args) {
        Random diceRoller = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of die sides: ");
        String userSidesString = sc.nextLine();
        int userSides = Integer.parseInt(userSidesString);
        int rollResult;

        do {
            rollResult = diceRoller.nextInt(userSides) + 1;

            System.out.println("TIME TO ROOOOOOLL THE DICE!");
            System.out.println("I rolled a " + rollResult);

            if (rollResult == 1) {
                System.out.println("You rolled a critical failure!");
            } else if (rollResult == userSides) {
                System.out.println("You rolled a critical! Nice Job!");
            }
            
        } while (rollResult < userSides);

    }

}
