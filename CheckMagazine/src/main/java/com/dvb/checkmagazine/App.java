/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.checkmagazine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter size of first and second string arrays: ");
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        System.out.println("Enter magazine array: ");
        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        System.out.println("Enter note array: ");
        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {

        if (note.length > magazine.length) {
            System.out.println("No");
            return;
        }

        Map<String, Integer> helper = new HashMap<>();

        for (String s : magazine) {
            if (!helper.containsKey(s)) {
                helper.put(s, 1);
            } else {
                helper.put(s, helper.get(s) + 1);
            }
        }

        for (String s : note) {
            if (helper.containsKey(s) && helper.get(s) > 0) {
                helper.put(s, helper.get(s) - 1);
            } else {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");

    }
}
