/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.simplesort;

import java.util.Arrays;

/**
 *
 * @author Daniel Bart
 */
public class SimpleSort {

    public static void main(String[] args) {
        SimpleSort simpleSortInstance = new SimpleSort();

        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};

        int[] wholeNumbers = new int[24];

        wholeNumbers = simpleSortInstance.combineArray(firstHalf, secondHalf);

        System.out.println("COMBINED ARRAY WITHOUT SORT");
        for (int i = 0; i < wholeNumbers.length; i++) {
            System.out.print(wholeNumbers[i] + " ");
        }
        wholeNumbers = simpleSortInstance.sortArray(wholeNumbers);

        System.out.println("\n\nCOMBINED ARRAY WITH SORT");
        for (int i = 0; i < wholeNumbers.length; i++) {
            System.out.print(wholeNumbers[i] + " ");
        }
    }

    public int[] combineArray(int[] oldArray1, int[] oldArray2) {
        int count1 = oldArray1.length;
        int count2 = oldArray2.length;
        int[] newArray = new int[count1 + count2];

        int combinedCount = 0;
        for (int element : oldArray1) {
            newArray[combinedCount] = element;
            combinedCount++;
        }

        for (int element : oldArray2) {
            newArray[combinedCount] = element;
            combinedCount++;
        }
        return newArray;
    }

    public int[] sortArray(int[] combinedArray) {
        int temp;
        for(int i = 1; i < combinedArray.length; i++) {
            for(int j = 0; j < combinedArray.length - 1; j++) {
                if(combinedArray[j] > combinedArray[j + 1]){
                    temp = combinedArray[j];
                    combinedArray[j] = combinedArray[j + 1];
                    combinedArray[j + 1] = temp;
                }
            }
        }
        return combinedArray;
    }

}
