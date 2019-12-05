/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.fortimes;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class ForTimes {
    
    public static void main(String[] args) {
        int pickedInt;
        Scanner sc = new Scanner(System.in);
        System.out.print("Which times table shall I recite? ");
        String pickedIntString = sc.nextLine();
        pickedInt = Integer.parseInt(pickedIntString);
        
        for(int i = 1; i <= 15; i++) {
            int product = i * pickedInt;
            System.out.println(i + " * " + pickedInt + " is: " + product);
        }
    }
    
}
