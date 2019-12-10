/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.factorizerrefactored;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class FactorizerRefactored {
       
    public void factorANumber() {
        
        // new scanner object instantiation
        Scanner sc = new Scanner(System.in);

        // declare variables and initialize some
        int numberToFactor, totalNumberOfFactors = 0, factorsAddedTogether = 0;
        String numberToFactorString;

        // prompt the user and store their response
        System.out.print("What number would you like to factor? ");
        numberToFactorString = sc.nextLine();
        numberToFactor = Integer.parseInt(numberToFactorString);

        // print out the number of factors of the prompted number
        System.out.println("The factors of " + numberToFactor + " are:");

        // determine the factors of prompted number
        for (int i = 1; i < numberToFactor; i++) {
            // if prompted number mod iteration number equals 0, then print out the iteration number and increase the total number of factors count and add the iteration to the previously added iterations
            if (numberToFactor % i == 0) {
                System.out.println(i);
                totalNumberOfFactors++;
                factorsAddedTogether += i;
            }
        }

        System.out.println("The Number itself is not included as factor here.");

        // add one to total count of factors because we are not including the prompted number itself as a factor in the loop per the instructions
        totalNumberOfFactors += 1;

        // print out the total count of factors
        System.out.println("Total Count of Factors: " + totalNumberOfFactors);

        // determine if prompted number is a perfect number
        if (factorsAddedTogether == numberToFactor) {
            System.out.println(numberToFactor + " is a perfect number.");
        } else {
            System.out.println(numberToFactor + " is NOT a perfect number.");
        }

        // determine if prompted number is a prime number
        if (totalNumberOfFactors == 2) {
            System.out.println(numberToFactor + " is a prime number.");
        } else {
            System.out.println(numberToFactor + " is NOT a prime number.");

        }
        
    }
    
}
