/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.interestcalculator;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class InterestCalculator {
    
    public static void main(String[] args) {
        // instantiate InterestCalculator to use public non-static method
        InterestCalculator interestCalculatorInstance = new InterestCalculator();
        
        // instantiate new scanner object
        Scanner sc = new Scanner(System.in);
        
        // declare variables and initialize if necessary
        float annualInterestRate;
        double initialPrincipal, currentBalance, interestEarned = 0;
        int numberOfYears, numberOfCompoundingPeriods = 0;
        String annualInterestRateString, initialPrincipalString, numberOfYearsString, numberOfCompoundingPeriodsString;
        
        // prompt user for interest rate and store in variable
        System.out.println("Enter the annual interest rate: ");
        annualInterestRateString = sc.nextLine();
        annualInterestRate = Float.parseFloat(annualInterestRateString);
        
        // prompt user for initial amount of principal and store in variable
        System.out.println("Enter the initial amount of principal: ");
        initialPrincipalString = sc.nextLine();
        initialPrincipal = Double.parseDouble(initialPrincipalString);
        
        // prompt user for number of years and store in variable
        System.out.println("Enter the number of years: ");
        numberOfYearsString = sc.nextLine();
        numberOfYears = Integer.parseInt(numberOfYearsString);
        
        // prompt user for quarterly, monthly or daily
        System.out.println("Do you want quarterly, monthly or daily? ");
        numberOfCompoundingPeriodsString = sc.nextLine();
        if(numberOfCompoundingPeriodsString.equalsIgnoreCase("quarterly")) {
            numberOfCompoundingPeriods = 4;
        } else if (numberOfCompoundingPeriodsString.equalsIgnoreCase("monthly")) {
            numberOfCompoundingPeriods = 12;
        } else if (numberOfCompoundingPeriodsString.equalsIgnoreCase("daily")) {
            numberOfCompoundingPeriods = 365;
        }
        
        // initial beginning year balance to inital principal
        currentBalance = initialPrincipal;
        
        // loop through each year
        for(int i = 1; i <= numberOfYears; i++) {
            System.out.println("Year Number: " + i);
            System.out.printf("Beginning Year Balance: %.2f\n", currentBalance);
            
            for(int j = 0; j < numberOfCompoundingPeriods; j++) {
                double interestGenerated = interestCalculatorInstance.getInterest(currentBalance, annualInterestRate, numberOfCompoundingPeriods);
                interestEarned += interestGenerated;
                currentBalance += interestGenerated;
            }
            
            System.out.printf("Annual Interest: %.2f\n",interestEarned);
            interestEarned = 0;
            System.out.printf("Ending Year Balance: %.2f\n", currentBalance);
            System.out.println();
        }
    }
    
    public double getInterest(double beginningBalance, float interestRate, float period) {
        return beginningBalance * ((interestRate/period)/100);
    }
    
}
