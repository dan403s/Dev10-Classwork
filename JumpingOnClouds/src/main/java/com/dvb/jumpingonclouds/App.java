/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.jumpingonclouds;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class App {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {

        int jumps = 0;

        for (int i = 0; i < c.length - 1; i++) {
            try {
                if (c[i + 1] == 0 && c[i + 2] == 0) {
                    jumps++;
                    i++;
                } else if (c[i + 1] == 0) {
                    jumps++;
                } else if (c[i + 2] == 0) {
                    jumps++;
                    i++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (c[i + 1] == 0) {
                    jumps++;
                }
            }

        }

        return jumps;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("Enter number of clouds: ");
        String nString = scanner.nextLine();
        int n = Integer.parseInt(nString);
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        System.out.println("Enter array of clouds: ");
        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);
        
        System.out.println(result);

    }
}
