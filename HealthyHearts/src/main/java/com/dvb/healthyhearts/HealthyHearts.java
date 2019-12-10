/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class HealthyHearts {

    // main method
    public static void main(String[] args) {
        // instantiate new scanner object
        Scanner sc = new Scanner(System.in);

        // declare variables and initialize if necessary
        int userAge, maxHeartRate;
        float minTargetHeartRate, maxTargetHeartRate;
        String userAgeString;

        // prompt user for age and store in variable
        System.out.print("What is your age? ");
        userAgeString = sc.nextLine();
        userAge = Integer.parseInt(userAgeString);

        // calculate heart rate stats
        maxHeartRate = 220 - userAge;
        minTargetHeartRate = 0.5f * maxHeartRate;
        maxTargetHeartRate = 0.85f * maxHeartRate;

        // print out results to console
        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute.");
        System.out.printf("Your target HR Zone is %.0f - %.0f beats per minute.%n", minTargetHeartRate, maxTargetHeartRate);
    }

}
