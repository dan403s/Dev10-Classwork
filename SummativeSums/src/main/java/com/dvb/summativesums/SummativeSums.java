/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.summativesums;

/**
 *
 * @author Daniel Bart
 */
public class SummativeSums {

    // main method
    public static void main(String[] args) {
        // declare variables and initialize if necessary
        int result1, result2, result3;
        int[] intArray1 = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] intArray2 = {999, -60, -77, 14, 160, 301};
        int[] intArray3 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99};

        // call method created to add together the array elements and store in variables
        result1 = addArrayInts(intArray1);
        result2 = addArrayInts(intArray2);
        result3 = addArrayInts(intArray3);

        // print out the results
        System.out.println("#1 Array Sum: " + result1);
        System.out.println("#2 Array Sum: " + result2);
        System.out.println("#3 Array Sum: " + result3);
    }

    // static method that takes in an array of ints, adds them together and returns the result
    public static int addArrayInts(int[] intArray) {
        // declare variable for sum and initialize
        int sum = 0;

        // run for loop and add each iteration of array to sum variable
        for (int i = 0; i < intArray.length; i++) {
            sum += intArray[i];
        }

        return sum;
    }

}
