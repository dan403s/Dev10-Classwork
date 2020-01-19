package com.dvb.allthetrivia;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class AllTheTrivia {
    
    public static void main(String[] args) {
        String size, planet, volcano, element;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("1,024 Gigabytes is equal to one what? ");
        size = inputReader.next();
        
        System.out.println("In our Solar System, which is the only planet that rotates clockwise? ");
        planet = inputReader.next();
        
        System.out.println("The largest volcano ever discovered in our Solar System is located on which planet? ");
        volcano = inputReader.next();
        
        System.out.println("What is the most abundant element in the earth's atmosphere? ");
        element = inputReader.next();
        
        System.out.println("Wow, 1024 GB is a " + volcano + "!");
        System.out.println("I didn't know that the largest ever volcano was discovered on " + size + "!");
        System.out.println("That's amazing that " + planet + " is the most abundant element in the atmosphere...");
        System.out.println(element + " is the only planet that rotates clockwise, neat!");
    }
    
    
}
