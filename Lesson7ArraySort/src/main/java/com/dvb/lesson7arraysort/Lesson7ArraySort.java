/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.lesson7arraysort;

/**
 *
 * @author Daniel Bart
 */
public class Lesson7ArraySort {
    
    public static void main(String[] args) {
        int[] numbers = {
            3, 7, 2, 4, 7, 12
        };
        int temp;
        
        for(int i = 1; i < numbers.length; i++) {
            for(int j = 0; j < numbers.length - 1; j++) {
                if(numbers[j] > numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                } 
            }
            
            System.out.println("i = " + i);
            
            for(int j = 0; j < numbers.length; j++) {
                System.out.println(numbers[j]);
            }
        }
    }
    
}
