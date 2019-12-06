/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.stillpositive;

/**
 *
 * @author Daniel Bart
 */
public class StillPositive {

    public static void main(String[] args) {
        StillPositive instance1 = new StillPositive();
        
        int[] numbers = {
            389, -447, 26, -485, 712, -884, 94, -64, 868, -776, 227,
            -744, 422, -109, 259, -500, 278, -219, 799, -311
        };
        
        int[] sortedArray = instance1.sortArray(numbers);
        System.out.println("SORTED:");
        for (int number : sortedArray) {
            System.out.print(number + " ");
        }
        
        System.out.println("\n\nGotta stay positive ...!");

        for (int i = 0; i < sortedArray.length; i++) {
            if (sortedArray[i] > 0) {
                System.out.print(sortedArray[i] + " ");
            }
        }
    }

    public int[] sortArray(int[] array) {
        int[] newArray = array;
        int temp;

        for (int i = 1; i < newArray.length; i++) {
            for (int j = 0; j < newArray.length - 1; j++) {
                if (newArray[j] > newArray[j + 1]) {
                    temp = newArray[j];
                    newArray[j] = newArray[j + 1];
                    newArray[j + 1] = temp;
                }
            }
        }

        return newArray;
    }

}
