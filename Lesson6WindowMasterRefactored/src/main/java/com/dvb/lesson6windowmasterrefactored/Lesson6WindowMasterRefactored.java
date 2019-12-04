package com.dvb.lesson6windowmasterrefactored;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class Lesson6WindowMasterRefactored {
    
    public static void main(String[] args) {
        float height;
        float width;        
        
        float areaOfWindow;
        float cost;
        float perimeterOfWindow;
                
        height = readValue("Please enter window height:");
        width = readValue("Please enter window width:");
        
        areaOfWindow = height * width;
        
        perimeterOfWindow = 2 * (height + width);
        
        cost = ((3.50f * areaOfWindow) + (2.25f * perimeterOfWindow));
        
        System.out.println("Window height = " + height);
        System.out.println("Window width = " + width);
        System.out.println("Window area = " + areaOfWindow);
        System.out.println("Window perimeter = " + perimeterOfWindow);
        System.out.println("Total Cost =  " + cost);
    }
    
    public static float readValue(String prompt) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println(prompt);
        String input = myScanner.nextLine();
        float floatValue = Float.parseFloat(input);
        return floatValue;
    }
    
}