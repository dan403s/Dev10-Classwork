/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.staypositive;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class StayPositive {

    public static void main(String[] args) {
        int userNumber;
        String userNumberString;
        int row10 = 1;

        Scanner sc = new Scanner(System.in);
        System.out.println("What number should I count down from? ");
        userNumberString = sc.nextLine();
        userNumber = Integer.parseInt(userNumberString);

        System.out.println("Here Goes Nothing!");

        while (userNumber >= 0) {
            if(row10 == 10) {
                System.out.print(userNumber + "\n");
                row10 = 1;
            } else {
                System.out.print(userNumber + " ");
                row10++;
            }
            
            userNumber--;
        }

        System.out.println("Whew, better stop there...!");
    }

}
