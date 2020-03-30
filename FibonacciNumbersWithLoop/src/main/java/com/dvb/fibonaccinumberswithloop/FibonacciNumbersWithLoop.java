/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.fibonaccinumberswithloop;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class FibonacciNumbersWithLoop {
    
    public static void main(String[] args) {

        FibonacciMethod fibonacciMethod = new FibonacciMethod();

        Scanner sc = new Scanner(System.in);
        
        boolean doContinue = true;

        while (doContinue) {
            System.out.println("Which number do you want to see in the Fibonacci sequence? ");
            String userInputString = sc.nextLine();
            int userInput = Integer.parseInt(userInputString);

            fibonacciMethod.fib(userInput);
            
            System.out.println("Do you want to see another (Yes/No)? ");
            String userContinueString = sc.nextLine();
            
            if(userContinueString.equalsIgnoreCase("yes") || userContinueString.equalsIgnoreCase("y")) {
                doContinue = true;
            } else {
                doContinue = false;
            }
        }

    }
    
}
