/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.fourtimesfor;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class ForTimesFor {
    
    public static void main(String[] args) {
        int pickedInt;
        int totalPoints = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Which times table shall I recite? ");
        String pickedIntString = sc.nextLine();
        pickedInt = Integer.parseInt(pickedIntString);
        
        for(int i = 1; i <= 15; i++) {
            int product = i * pickedInt;
            System.out.print(i + " * " + pickedInt + " is: ");
            String userAnswerString = sc.nextLine();
            int userAnswer = Integer.parseInt(userAnswerString);
            
            if(userAnswer == product) {
                System.out.println("Correct");
                totalPoints++;
            } else {
                System.out.println("Sorry no, the answer is: " + product);
            }
        }
        
        System.out.println("You got " + totalPoints + " correct.");
        
        if(totalPoints < 7.5) {
            System.out.println("You got less than 50% correct. Study more.");
        } else if(totalPoints > 13.5) {
            System.out.println("Congrats you got more than 90%. Don't study more.");
        }
    }
    
}
