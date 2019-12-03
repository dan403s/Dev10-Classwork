package com.dvb.menuofchampions;

/**
 *
 * @author Daniel Bart
 */
public class MenuOfChampions {
    
    public static void main(String[] args) {
        String restaurantName, food1, food2, food3;
        float food1Price, food2Price, food3Price;
        
        restaurantName = "Dan's";
        food1 = "Chili";
        food2 = "Beans";
        food3 = "Hot Dogs";
        
        food1Price = 3.50f;
        food2Price = 5.99f;
        food3Price = 10.10f;
        
        System.out.println("  ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( )\n" +
            "-. X X X X X X X X X X X X X X X X X X X X X X X ,-\n" +
            "  X X X X X X X X X X X X X X X X X X X X X X X X\n" +
            " (_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_)\n");
        
        System.out.println("WELCOME TO " + restaurantName);
        
        System.out.println("Today's Menue Is ...\n");
        
        System.out.println("  ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( ( )\n" +
            "-. X X X X X X X X X X X X X X X X X X X X X X X ,-\n" +
            "  X X X X X X X X X X X X X X X X X X X X X X X X\n" +
            " (_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_(_)\n");
        
        System.out.printf("%s $ %.2f %n", food1, food1Price);
        System.out.printf("%s $ %.2f %n", food2, food2Price);
        System.out.printf("%s $ %.2f %n", food3, food3Price);
    }
    
}