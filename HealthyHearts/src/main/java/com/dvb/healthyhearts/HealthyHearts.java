package com.dvb.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class HealthyHearts {
    
    public static void main(String[] args) {
        int age;
        float maxHeartRate, minTargetRate, maxTargetRate;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("How old are you? ");
        String ageString = inputReader.nextLine();
        
        age = Integer.parseInt(ageString);
        
        maxHeartRate = 220 - age;
        minTargetRate = 0.5f * maxHeartRate;
        maxTargetRate = 0.85f * maxHeartRate;
        
        System.out.printf("Your maximum heart rate should be %.0f beats per minute.\n", maxHeartRate);
        System.out.printf("Your target HR Zone is %.0f - %.0f beats per minute.\n", minTargetRate, maxTargetRate);
    }
    
}
