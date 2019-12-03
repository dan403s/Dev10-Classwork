package com.dvb.biggerbetteradder;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class BiggerBetterAdder {
    
    public static void main(String[] args) {
        int number1;
        double number2, number3;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Input integer: ");
        number1 = inputReader.nextInt();
        
        System.out.println("Input double: ");
        number2 = inputReader.nextDouble();
        
        System.out.println("Input double: ");
        number3 = inputReader.nextDouble();
        
        System.out.println("Number 1: " + number1);
        System.out.println("Number 2: " + number2);
        System.out.println("Number 3: " + number3);
        
        double sum1 = number1 + number2 + number3;
        
        System.out.println("Sum: " + sum1);
        System.out.println("Sum: " + sum1);
    }
    
}
