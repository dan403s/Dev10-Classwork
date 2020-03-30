/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.primality;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("Enter total number of integers: ");
        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int pItr = 0; pItr < p; pItr++) {
            
            System.out.println("Enter integer: ");
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = primality(n);
            
            System.out.println(result);

        }

    }

    // Complete the primality function below.
    static String primality(int n) {

        if (n < 2) {
            return "Not prime";
        }
        if (n == 2 || n == 3) {
            return "Prime";
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return "Not prime";
        }

        long sqrtN = (long) Math.sqrt(n) + 1;

        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) {
                return "Not prime";
            }
        }
        return "Prime";

    }

}
