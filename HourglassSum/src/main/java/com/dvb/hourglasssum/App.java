/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.hourglasssum;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            System.out.println("Enter row: ");
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);
        
        System.out.println(result);

    }

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {

        int maxHourglass = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                int currentHourglass = 0;

                currentHourglass = (arr[i][j]
                        + arr[i][j + 1]
                        + arr[i][j + 2]
                        + arr[i + 1][j + 1]
                        + arr[i + 2][j]
                        + arr[i + 2][j + 1]
                        + arr[i + 2][j + 2]);

                maxHourglass = Math.max(currentHourglass, maxHourglass);
            }

        }

        return maxHourglass;

    }

}
