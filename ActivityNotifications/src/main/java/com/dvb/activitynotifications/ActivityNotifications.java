/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.activitynotifications;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class ActivityNotifications {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {

        int totalNotifications = 0;

        for (int i = 0; i < expenditure.length - d; i++) {
            int trailingFactor = 0;

            int[] sortedExpenditure = new int[d];
            System.arraycopy(expenditure, i, sortedExpenditure, 0, d);
            sort(sortedExpenditure);

            if (d % 2 == 0) {
                int n1 = sortedExpenditure[(d / 2)];
                int n2 = sortedExpenditure[(d / 2) - 1];
                trailingFactor = (n1 + n2);
                System.out.println("trailingFactor[" + ((d / 2) + i - 1) + "] & [" + ((d / 2) + i) + "]: " + trailingFactor);
                System.out.println(n1);
                System.out.println(n2);
            } else {
                int dd = (int) Math.floor(d / 2);
                trailingFactor = sortedExpenditure[dd];
                trailingFactor *= 2;
                System.out.println("trailingFactor[" + ((d / 2) + i - 1) + "] & [" + ((d / 2) + i) + "]: " + trailingFactor);
            }

            System.out.println("expenditure[" + (d + i) + "]: " + expenditure[d + i]);
            if (expenditure[d + i] >= trailingFactor) {
                totalNotifications++;
                System.out.println("Notification Sent.");
            }

        }

        return totalNotifications;

    }

    static void sort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

//        System.out.println("Enter size of array and trailing days for computation: ");
//        String[] nd = scanner.nextLine().split(" ");
        System.out.println("PRESS ENTER");
        String[] nd = {"200000", "10000"};

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

//        System.out.println("Enter array: ");
//        String[] expenditureItems = scanner.nextLine().split(" ");
        String[] expenditureItems = new String(Files.readAllBytes(Paths.get("testCase1.txt"))).split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        System.out.println("Result: " + result);
    }
}
