/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.random4digitnumberwithnorepeatingdigits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public class App {

    public static void main(String[] args) {

        App app = new App();

        for (int i = 1; i <= 100; i++) {
            String random4DigitNumber = app.generateRandom4DigitNumber();

            System.out.println("Random 4 Digit Number: " + i);
            System.out.println(random4DigitNumber);
        }

    }

    public String generateRandom4DigitNumber() {

        String randomNumberAsString;
        List<Integer> fullNumberList = new ArrayList<>();
        List<Integer> numberList4Digit = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            fullNumberList.add(i);
        }
        
        Collections.shuffle(fullNumberList);
        
        for (int i = 0; i < 4; i++) {
            numberList4Digit.add(fullNumberList.get(i));
        }
        
        randomNumberAsString = numberList4Digit.get(0) + "" + numberList4Digit.get(1) + "" + numberList4Digit.get(2) + "" + numberList4Digit.get(3);

        return randomNumberAsString;
    }

}
