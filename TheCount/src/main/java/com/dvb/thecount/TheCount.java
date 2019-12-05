/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.thecount;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class TheCount {
    
    public static void main(String[] args) {
        int startNumber, stopNumber, countByNumber, threeCount =  1;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        
        System.out.print("Start at: ");
        String startNumberString = sc.nextLine();
        startNumber = Integer.parseInt(startNumberString);
        
        System.out.print("Stop at: ");
        String stopNumberString = sc.nextLine();
        stopNumber = Integer.parseInt(stopNumberString);
        
        System.out.print("Count by: ");
        String countByNumberString = sc.nextLine();
        countByNumber = Integer.parseInt(countByNumberString);
        
        for(int i = startNumber; i <= stopNumber; i += countByNumber) {
            if(threeCount == 3) {
                System.out.print(i + " - Ah ah ah!\n");
                threeCount = 0;
            } else {
                System.out.print(i + " ");
            }
            threeCount++;
        }
    }
    
}
