/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.luckysevens;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class LuckySevens {

    public static void main(String[] args) {
        // instantiate new LuckySevens object
        LuckySevens luckySevensInstance = new LuckySevens();

        // instantiate scanner object
        Scanner sc = new Scanner(System.in);

        // declare variables and initialize if necessary
        int initialBet, currentMoney, totalRolls = 0, maxMoney = 0, rollsAtMaxMoney = 0, die1, die2;
        String initialBetString;
        boolean isInitialBetAcceptable;

        // prompt user how many dollars they have to bet and store in a variable, ask again if not in bounds
        do {
            System.out.println("How many dollars do you have? ");
            initialBetString = sc.nextLine();
            initialBet = Integer.parseInt(initialBetString);
            if (initialBet < 1) {
                isInitialBetAcceptable = false;
                System.out.println("Please select a positive number.");
            } else {
                isInitialBetAcceptable = true;
            }
        } while (!isInitialBetAcceptable);

        currentMoney = initialBet;

        // roll the dice and store the results in variables
        while (currentMoney > 0) {
            die1 = luckySevensInstance.rollDie();
            die2 = luckySevensInstance.rollDie();
            int diceTotal = die1 + die2;

            if (diceTotal == 7) {
                currentMoney += 4;
                totalRolls++;
                System.out.println("Round: " + totalRolls);
                System.out.println("Win");
                System.out.println("Current Money: " + currentMoney);
            } else {
                currentMoney--;
                totalRolls++;
                System.out.println("Round: " + totalRolls);
                System.out.println("Lose");
                System.out.println("Current Money: " + currentMoney);
            }

            // determine which round had most money
            if (currentMoney > maxMoney) {
                maxMoney = currentMoney;
                rollsAtMaxMoney = totalRolls;
            }
            
        }
        
        // print results
        System.out.println("You are broke after " + totalRolls + " rolls.");
        System.out.println("You should have quit after " + rollsAtMaxMoney + " rolls when you had $" + maxMoney + ".");

    }

    // die roll method
    public int rollDie() {
        // instantiate random object
        Random randomizer = new Random();

        // call random on a variable from 1 to 6
        int randomNumber = randomizer.nextInt(7 - 1) + 1;

        // return random variable
        return randomNumber;
    }

}
