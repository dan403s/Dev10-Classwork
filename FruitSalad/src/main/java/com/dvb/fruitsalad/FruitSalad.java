/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.fruitsalad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public class FruitSalad {

    int berryCount;
    int appleCount;
    int orangeCount;
    int fruitCount;

    public static void main(String[] args) {
        FruitSalad fruitSaladInstance = new FruitSalad();

        List<String> finalFruitSaladArray = new ArrayList<>();

        String[] fruit = {
            "Kiwi Fruit", "Gala Apple", "Granny Smith Apple",
            "Cherry Tomato", "Gooseberry", "Beefsteak Tomato",
            "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange",
            "Pink Pearl Apple", "Raspberry", "Blood Orange",
            "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana",
            "Pineapple", "Florida Orange", "Kiku Apple", "Mango",
            "Satsuma Orange", "Watermelon", "Snozzberry"
        };
                
        finalFruitSaladArray = Arrays.asList(fruit);
        
        System.out.println(finalFruitSaladArray);

        finalFruitSaladArray = fruitSaladInstance.sortFruit(finalFruitSaladArray);

        System.out.println("THIS IS MY FINAL ARRAY USING ARRAY LIST:");
        for (String type : finalFruitSaladArray) {
            System.out.println(type);
        }
        
        System.out.println("\nBerries: " + fruitSaladInstance.berryCount);
        System.out.println("Apples: " + fruitSaladInstance.appleCount);
        System.out.println("Oranges: " + fruitSaladInstance.orangeCount);
        System.out.println("Fruits: " + fruitSaladInstance.fruitCount);

    }

    public List<String> sortFruit(List<String> fruitArray) {
        List<String> fruitArrayList = new ArrayList<>();
        berryCount = 0;
        appleCount = 0;
        orangeCount = 0;
        fruitCount = 0;

        for (String type : fruitArray) {
            if (type.toLowerCase().contains("berry") && fruitCount < 12) {
                fruitArrayList.add(type);
                berryCount++;
                fruitCount++;
            } else if (type.toLowerCase().contains("apple") && appleCount < 3 && fruitCount < 12) {
                fruitArrayList.add(type);
                appleCount++;
                fruitCount++;
            } else if (type.toLowerCase().contains("orange") && orangeCount < 2 && fruitCount < 12) {
                fruitArrayList.add(type);
                orangeCount++;
                fruitCount++;
            } else if(!type.toLowerCase().contains("tomato") && !type.toLowerCase().contains("orange") && !type.toLowerCase().contains("apple") && fruitCount < 12) {
                fruitArrayList.add(type);
                fruitCount++;
            }
        }

        return fruitArrayList;
    }

}
