package com.dvb.passingtheturingtest;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class PassingTheTuringTest {
    
    public static void main(String[] args) {
        String name, color, food;
        int number;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("What is your name? ");
        name = inputReader.next();
        System.out.println("My name is AI");
        
        System.out.println("What is your favorite color? ");
        color = inputReader.next();
        System.out.println(color + " is nice. Mine is blue.");
        
        System.out.println("What is your favorite food? ");
        food = inputReader.next();
        System.out.println("Mmm, " + food + " tastes good");
        
        System.out.println("What is your favorite number? ");
        number = inputReader.nextInt();
        int number2 = number * 100;
        System.out.println(number + " is good. If you multiply it by 100 it is " + number2);
        
        System.out.println("Goodbye!");
    }
    
}
