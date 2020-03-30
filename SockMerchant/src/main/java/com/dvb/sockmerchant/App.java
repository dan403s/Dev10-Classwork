/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.sockmerchant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("Enter number of socks: ");
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        System.out.println("Enter array of sock colors in integers: ");
        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        System.out.println(result);

    }

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {

        Arrays.sort(ar);
        int pairs = 0;
        for (int i = 0; i < n - 1; i++) {
            if (i < n && ar[i] == ar[i + 1]) {
                pairs++;
                i++;
            }
        }

        return pairs;

    }

    public static <T> List<T> convertArrayToList(T array[]) {

        List<T> list = Arrays.asList(array);

        return list;
    }

}
