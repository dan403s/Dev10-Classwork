package com.dvb.theorderofthings;

/**
 *
 * @author Daniel Bart
 */
public class TheOrderOfThings {
    
    public static void main(String[] args) {
        double number;
        String opinion, size, age, shape, color, origin, material, purpose;
        String noun;
        
        number = 5.0;
        opinion = "AWESOME";
        size = "teensy-weensy";
        age = "new";
        shape = "oblong";
        color = "yellow";
        origin = "Alpha-Centaurian";
        material = "platinum";
        purpose = "good";
        
        noun = "dragon";
        
        System.out.println(number + " " + opinion + " " + size + " " + age + " " + shape + " " + color + " " + origin + " " + material + " " + purpose + " " + noun);
    
        System.out.println("Fixed ---");
        System.out.println("Number: " + number + " " + "Opinion: " + opinion + " " + "Size: " + size + " " + "Age: " + age + " " + "Shape: " + shape + " " + "Color: " + color + " " + "Origin: " + origin + " " + "Material: " + material + " " + "Purpose: " + purpose + " " + "Noun: " + noun);
    
        System.out.println("Different Order ---");
        System.out.println("Age: " + age + " " + "Shape: " + shape + " " + "Color: " + color + " " + "Origin: " + origin + " " + "Material: " + material + " " + "Purpose: " + purpose + " " + "Noun: " + noun + " " + "Number: " + number + " " + "Opinion: " + opinion + " " + "Size: " + size);
    }
    
}
