/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.enummathoperators;

/**
 *
 * @author Daniel Bart
 */
public class App {

    public static void main(String[] args) {

        Prompter prompter = new Prompter();
        DoMath doMath = new DoMath();
        int firstNumber;
        int secondNumber;
        boolean isNotValid = true;

        do {
            try {
                firstNumber = prompter.getOperand("Enter the first number por favor: ");
                secondNumber = prompter.getOperand("Enter the second number por favor: ");

                for (MathOperators operator : MathOperators.values()) {
                    int answer = doMath.calculate(operator, firstNumber, secondNumber);
                    System.out.println("The answer for " + operator + " is " + answer + ".");
                }

                isNotValid = false;
            } catch (UnsupportedOperandException | UnsupportedOperatorException e) {
                System.out.println(e.getMessage());
            }
        } while (isNotValid);

    }

}
