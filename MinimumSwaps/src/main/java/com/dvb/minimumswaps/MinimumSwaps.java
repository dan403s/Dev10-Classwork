/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.minimumswaps;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class MinimumSwaps {
// Complete the minimumSwaps function below.

    static int minimumSwaps(int[] arr) {

        int n = arr.length - 1;
        int minSwaps = 0;
        for (int i = 0; i < n; i++) {
            if (i < arr[i] - 1) {
                swap(arr, i, Math.min(n, arr[i] - 1));
                minSwaps++;
                i--;
            }
        }
        return minSwaps;

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("Enter array number: ");
        String nString = scanner.nextLine();
        int n = Integer.parseInt(nString);
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        System.out.println("Enter array: ");
        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        System.out.println("Number of swaps: " + res);

    }
}
