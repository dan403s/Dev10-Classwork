/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.interestcalculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
        BigDecimal interestEarned = new BigDecimal(0);
        int numberOfYears, numberOfCompoundingPeriods = 0;
        String annualInterestRateString, initialPrincipalString, numberOfYearsString, numberOfCompoundingPeriodsString;
        
        // prompt user for interest rate and store in variable
        System.out.println("Enter the annual interest rate: ");
        annualInterestRateString = sc.nextLine();
        BigDecimal annualInterestRate = new BigDecimal(annualInterestRateString);
        
        // prompt user for initial amount of principal and store in variable
        System.out.println("Enter the initial amount of principal: ");
        initialPrincipalString = sc.nextLine();
        BigDecimal initialPrincipal = new BigDecimal(initialPrincipalString);
        
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
        BigDecimal currentBalance = initialPrincipal;
        
        // loop through each year
        for(int i = 1; i <= numberOfYears; i++) {
            System.out.println("Year Number: " + i);
            System.out.printf("Beginning Year Balance: %.2f\n", currentBalance);
            
            for(int j = 0; j < numberOfCompoundingPeriods; j++) {
                BigDecimal interestGenerated = interestCalculatorInstance.getInterest(currentBalance, annualInterestRate, numberOfCompoundingPeriods);
                interestEarned = interestEarned.add(interestGenerated);
                currentBalance = currentBalance.add(interestGenerated);
            }
            
            System.out.printf("Annual Interest: %.2f\n",interestEarned);
            interestEarned = new BigDecimal(0);
            System.out.printf("Ending Year Balance: %.2f\n", currentBalance);
            System.out.println();
        }
    }
    
    public BigDecimal getInterest(BigDecimal beginningBalance, BigDecimal interestRate, int period) {
        BigDecimal secondPart = interestRate.divide(new BigDecimal(period), 10, RoundingMode.HALF_UP);
        return beginningBalance.multiply(secondPart);
        // return beginningBalance * ((interestRate/period)/100);
    }
    
}
