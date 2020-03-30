/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.countingvalleys;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("Number of steps: ");
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        System.out.println("Sequence of steps: ");
        String s = scanner.nextLine();

        int result = countingValleys(n, s);
        
        System.out.println(result);

    }

    static int countingValleys(int n, String s) {

        int valleyNumber = 0;
        int currentPosition = 0;
        int downs = 0;
        int ups = 0;

        char[] charArr = s.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == 'U') {
                ups++;
                currentPosition++;
                if (currentPosition == 0) {
                    valleyNumber++;
                }
            } else {
                downs++;
                currentPosition--;
            }
        }

        return valleyNumber;

    }

}
