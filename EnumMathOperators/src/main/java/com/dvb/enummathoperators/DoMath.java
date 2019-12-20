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
public class DoMath {
    
    public int calculate(MathOperators operator, int operand1, int operand2) throws UnsupportedOperatorException {
        switch(operator) {
            case PLUS:
                return operand1 + operand2;
            case MINUS:
                return operand1 - operand2;
            case MULTIPLY:
                return operand1 * operand2;
            case DIVIDE:
                return operand1 / operand2;
            default:
                throw new UnsupportedOperatorException("You must have put another constant in the enum. I will keep the loop going until you take the constant out of the enum.");
        }
    }
    
}
