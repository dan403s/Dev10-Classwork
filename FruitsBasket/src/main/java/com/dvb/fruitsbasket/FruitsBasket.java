/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.fruitsbasket;

import java.util.Arrays;

/**
 *
 * @author Daniel Bart
 */
public class FruitsBasket {

    public static void main(String[] args) {
        FruitsBasket fruitsBasketInstance = new FruitsBasket();

        String[] fruit = {
            "Orange", "Apple", "Orange", "Apple", "Orange",
            "Apple", "Orange", "Apple", "Orange", "Orange",
            "Orange", "Apple", "Orange", "Orange", "Apple",
            "Orange", "Orange", "Apple", "Apple", "Orange",
            "Apple", "Apple", "Orange", "Orange", "Apple",
            "Apple", "Apple", "Apple", "Orange", "Orange",
            "Apple", "Apple", "Orange", "Orange", "Orange",
            "Orange", "Apple", "Apple", "Apple", "Apple",
            "Orange", "Orange", "Apple", "Orange", "Orange",
            "Apple", "Orange", "Orange", "Apple", "Apple",
            "Orange", "Orange", "Apple", "Orange", "Apple",
            "Orange", "Apple", "Orange", "Apple", "Orange",
            "Orange"
        };

        int orangeCount = fruitsBasketInstance.countFruit(fruit, "Orange");
        System.out.println("Orange count before sorting: " + orangeCount);

        int appleCount = fruitsBasketInstance.countFruit(fruit, "Apple");
        System.out.println("Apple count before sorting: " + appleCount);

        String[] orangeArray = fruitsBasketInstance.separateArrays(fruit, "Orange", orangeCount);
        System.out.println("New orange array toString: " + Arrays.toString(orangeArray));

        String[] appleArray = fruitsBasketInstance.separateArrays(fruit, "Apple", appleCount);
        System.out.println("New apple array toString: " + Arrays.toString(appleArray));

        int orangeCountFromArray = fruitsBasketInstance.countFruit(orangeArray, "Orange");
        int appleCountFromArray = fruitsBasketInstance.countFruit(appleArray, "Apple");

        int totalCount = orangeCountFromArray + appleCountFromArray;

        System.out.println("New counts after array was sorted and split:");
        System.out.println("Total Count: " + totalCount);
        System.out.println("Orange Count: " + orangeCountFromArray);
        System.out.println("Apple Count: " + appleCountFromArray);
    }

    public int countFruit(String[] fruitTypeArray, String fruitType) {
        int fruitCount = 0;

        for (int i = 0; i < fruitTypeArray.length; i++) {
            if (fruitTypeArray[i].equalsIgnoreCase(fruitType)) {
                fruitCount++;
            }
        }
        return fruitCount;
    }

    public String[] separateArrays(String[] fruitTypeArray, String fruitType1, int fruitCount) {
        String[] newArray = new String[fruitCount];

        //sort array
        String temp;
        for (int i = 1; i < fruitTypeArray.length; i++) {
            for (int j = 0; j < fruitTypeArray.length - 1; j++) {
                if (fruitTypeArray[j].compareTo(fruitTypeArray[j + 1]) > 0) {
                    temp = fruitTypeArray[j];
                    fruitTypeArray[j] = fruitTypeArray[j + 1];
                    fruitTypeArray[j + 1] = temp;
                }
            }
        }

        //new array
        for (int i = 0; i < fruitTypeArray.length; i++) {
            if(fruitTypeArray[i].equalsIgnoreCase(fruitType1)) {
                for(int j = 0; j < fruitCount; j++) {
                    newArray[j] = fruitTypeArray[i];
                }
            } 
        }

        return newArray;
    }

}
