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
public class SimpleCalculator {
    // declare variables and initialize if necessary
    private double firstNumber;
    private double secondNumber;
    
    // constructor method that takes in two numbers
    public SimpleCalculator(double firstNumber, double secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    // getter
    public double getFirstNumber() {
        return firstNumber;
    }

    // setter
    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    // getter
    public double getSecondNumber() {
        return secondNumber;
    }

    // setter
    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }
    
    // public method to add
    public double add() {
        return firstNumber + secondNumber;
    }
    
    // public method to subtract
    public double subtract() {
        return firstNumber - secondNumber;
    }
    
    // public method to multiply
    public double multiply() {
        return firstNumber * secondNumber;
    }
    
    // public method to divide
    public double divide() {
        return firstNumber / secondNumber;
    }
}
