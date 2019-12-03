package com.dvb.doitbetter;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class DoItBetter {
    
    public static void main(String[] args) {
        double miles, hotdogs, languages;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("How many miles can you run? ");
        miles = inputReader.nextDouble();
        double milesBrag = miles * 2;
        System.out.println("I can run " + milesBrag + " miles.");
        
        System.out.println("How many hotdogs can you eat? ");
        hotdogs = inputReader.nextDouble();
        double hotdogsBrag = hotdogs * 2;
        System.out.println("I can eat " + hotdogsBrag + " hotdogs.");
        
        System.out.println("How many languages do you know? ");
        languages = inputReader.nextDouble();
        double languagesBrag = languages * 2;
        System.out.println("I know " + languagesBrag + " languages.");
    }
    
}
