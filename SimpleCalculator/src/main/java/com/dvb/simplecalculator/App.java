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
public class App {

    public static void main(String[] args) {
        // instantiate Calculator object
        Calculator calculatorInstance = new Calculator();

        do {
            // call runCalculator method
            calculatorInstance.runCalculator();
        } while (true);

    }

}
