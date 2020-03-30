/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.repeatedstring;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("Enter a character sequence: ");
        String s = scanner.nextLine();

        System.out.println("Enter number of characters to consider: ");
        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);
        
        System.out.println(result);

    }

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {

        long count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                count++;
            }
        }

        long repetitions = n / s.length();
        count = count * repetitions;

        for (int i = 0; i < n % s.length(); i++) {
            if (s.charAt(i) == 'a') {
                count++;
            }
        }

        return count;

    }

}
