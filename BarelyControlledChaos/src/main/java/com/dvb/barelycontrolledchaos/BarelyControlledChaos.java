/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.barelycontrolledchaos;

import java.util.Random;

/**
 *
 * @author Daniel Bart
 */
public class BarelyControlledChaos {
    
    
    public static void main(String[] args) {
        BarelyControlledChaos barely = new BarelyControlledChaos();
        
        String color = barely.returnColor(); // call color method here 
        String animal = barely.returnAnimal(); // call animal method again here 
        String colorAgain = barely.returnColor(); // call color method again here 
        int weight = barely.returnNumber(5, 200); // call number method, 
            // with a range between 5 - 200 
        int distance = barely.returnNumber(10, 20); // call number method, 
            // with a range between 10 - 20 
        int number = barely.returnNumber(10000, 20000); // call number method, 
            // with a range between 10000 - 20000 
        int time = barely.returnNumber(2, 6); // call number method, 
            // with a range between 2 - 6            
    
        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal 
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over " 
            + number + " " + colorAgain + " poppies for nearly " 
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, " 
            + "let me tell you!");
    } 
    
    
    public String returnColor() {
        Random randomizer = new Random();
    
        String[] colorArray = {
            "Red", "Green", "Blue", "Yellow", "Black"
        };
        int randomColorIndex = randomizer.nextInt(5);
        String randomColor = colorArray[randomColorIndex];
        return randomColor;
    }
    
    public String returnAnimal() {
        Random randomizer = new Random();
        
        String[] animalArray = {
            "Pig", "Horse", "Dragon", "Fly", "Botfly"
        };
        int randomAnimalIndex = randomizer.nextInt(5);
        String randomAnimal = animalArray[randomAnimalIndex];
        return randomAnimal;
    }
    
    public int returnNumber(int min, int max) {
        Random randomizer = new Random();
        
        int randomNumber = randomizer.nextInt(max + 1 - min) + min;
        return randomNumber;
    }
}
