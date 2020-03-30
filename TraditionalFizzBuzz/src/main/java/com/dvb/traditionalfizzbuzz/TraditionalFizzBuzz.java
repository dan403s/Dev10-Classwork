/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.traditionalfizzbuzz;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class TraditionalFizzBuzz {

    public static void main(String[] args) {
        int userNumber;
        int fizzCount = 0;
        int buzzCount = 0;
        int allCount = 0;
        int fizzBuzzCount = 0;

        Scanner sc = new Scanner(System.in);

        System.out.print("How many fizzing and buzzing units do you need in your life? ");
        String userNumberString = sc.nextLine();
        userNumber = Integer.parseInt(userNumberString);

        for (int i = 0; true; i++) {
            if (i != 0 && i % 3 == 0 && i % 5 == 0) {
                System.out.println("fizz buzz");
                allCount++;
                fizzBuzzCount++;
            } else if (i != 0 && i % 3 == 0) {
                System.out.println("fizz");
                fizzCount++;
                allCount++;
            } else if (i != 0 && i % 5 == 0) {
                System.out.println("buzz");
                buzzCount++;
                allCount++;
            } else {
                System.out.println(i);
            }

            if (allCount == userNumber) {
                break;
            }
        }

        System.out.println("TRADITION");
        System.out.println("Fizzes = " + fizzCount);
        System.out.println("Buzzes = " + buzzCount);
        System.out.println("Fizz Buzzes = " + fizzBuzzCount);
    }

}
