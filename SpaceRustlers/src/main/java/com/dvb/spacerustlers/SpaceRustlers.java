package com.dvb.spacerustlers;

/**
 *
 * @author Daniel Bart
 */
public class SpaceRustlers {
    
    public static void main(String[] args) {
        int spaceships = 10;
        int aliens = 25;
        int cows = 100;

        if(aliens > spaceships){
            System.out.println("Vrroom, vroom! Let's get going!");
        } else{
            System.out.println("There aren't enough green guys to drive these ships!");
        }

        // if runs the block of code if the conditions evaluate to true; else if does the same, but if the first if statement evaluates to true, then the else if will not be evaluated thus the code block will not run
        if(cows == spaceships){
            System.out.println("Wow, way to plan ahead! JUST enough room for all these walking hamburgers!");
        } else if (cows > spaceships){
            System.out.println("Dangit! I don't how we're going to fit all these cows in here!");
        } else {
            System.out.println("Too many ships! Not enough cows.");
        }
        // if you remove the else, nothing changes; however if (cows == spaceships) evaluated to true and (cows > spaceships) evaluated to true, both their respective code blocks would run
    
        if(aliens > cows) {
            System.out.println("Hurrah, we've got the grub! Hamburger party on Alpha Centauri!");
        } else {
            System.out.println("Oh no! The herds got restless and took over! Looks like _we're_ hamburger now!!");
        }
    }
    
}
