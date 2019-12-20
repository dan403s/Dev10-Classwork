/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.enummathoperators;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class Prompter {

    Scanner sc = new Scanner(System.in);

    public int getOperand(String prompt) throws UnsupportedOperandException {
        try {
            System.out.println(prompt);
            String response = sc.nextLine();
            int responseInt = Integer.parseInt(response);
            return responseInt;
        } catch (NumberFormatException e) {
            throw new UnsupportedOperandException("Error: Nice try, that was not an integer.", e);
        }

    }
    
    public String promptExit(String prompt) {
        System.out.println(prompt);
        String response = sc.nextLine();
        return response;
    }

}
