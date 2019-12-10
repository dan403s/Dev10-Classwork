/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.simplecalculator;

/**
 *
 * @author Daniel Bart
 */
public class Calculator {

    // declare variables and initialize if necessary
    private String userChoiceString;
    private double userFirstNumber, userSecondNumber, answer;

    public void runCalculator() {

        // instantiate new Prompter object
        Prompter prompterInstance = new Prompter();

        // prompt user for operation, if not in list, loop
        do {
            // call promptUser method to prompt for operation choice
            prompterInstance.promptUser("Make your choice (+, -, *, /, or exit): ");

            // call getter for Prompter instance to return userResponse String and store in userChoiceString
            userChoiceString = prompterInstance.getUserResponse();

            System.out.println("You chose: " + userChoiceString);

            if (userChoiceString.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...NOW");
                System.exit(0);
            }

        } while (!userChoiceString.equalsIgnoreCase("+")
                && !userChoiceString.equalsIgnoreCase("-")
                && !userChoiceString.equalsIgnoreCase("*")
                && !userChoiceString.equalsIgnoreCase("/")
                && !userChoiceString.equalsIgnoreCase("exit"));

        userFirstNumber = prompterInstance.promptUser("Choose the first number: ");
        userSecondNumber = prompterInstance.promptUser("Choose the second number: ");

        // instantiate new SimpleCalculator object
        SimpleCalculator simpleCalculatorInstance = new SimpleCalculator(userFirstNumber, userSecondNumber);

        switch (userChoiceString) {
            case "+":
                answer = simpleCalculatorInstance.add();
                break;
            case "-":
                answer = simpleCalculatorInstance.subtract();
                break;
            case "*":
                answer = simpleCalculatorInstance.multiply();
                break;
            case "/":
                answer = simpleCalculatorInstance.divide();
                break;
            default:
                System.out.println("WE HAVE PROBLEMS");
        }
        
        System.out.printf("The answer is: %.2f %n%n", answer);
    }

}
