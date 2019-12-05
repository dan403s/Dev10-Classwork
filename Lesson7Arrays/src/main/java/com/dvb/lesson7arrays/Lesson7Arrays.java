/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.lesson7arrays;

import java.util.Arrays;

/**
 *
 * @author Daniel Bart
 */
public class Lesson7Arrays {

    public static void main(String[] args) {
        int[] teamScores = new int[10];
        teamScores[0] = 2;
        teamScores[1] = 45;
        teamScores[2] = 4;
        teamScores[3] = 8;
        teamScores[4] = 99;
        teamScores[5] = 23;
        teamScores[6] = 67;
        teamScores[7] = 1;
        teamScores[8] = 88;
        teamScores[9] = 42;

        System.out.println("teamScores: " + Arrays.toString(teamScores));

        int[] teamScores2 = {
            2, 45, 4, 8, 99, 23, 67, 1, 88, 42
        };

        System.out.println("teamScores2: " + Arrays.toString(teamScores2));

        int currentScore = teamScores2[2];
        System.out.println(currentScore);

        teamScores2[9] = 100;

        System.out.println(teamScores2[9]);

        for (int i = 0; i < teamScores2.length; i++) {
            System.out.println("Element: " + teamScores2[i]);
        }

        for (int score : teamScores2) {
            System.out.println("Element: " + score);
        }

        String[][] cityTeamNames = {
            {"Cleveland", "Browns", "Cavs", "Indians"},
            {"Columbus", "Bluejackets", "Buckeyes"},
            {"Pittsburgh", "Steelers", "Pirates", "Penguins"}
        };
        
        for(int i = 0; i < cityTeamNames.length; i++) {
            for(int j = 0; j < cityTeamNames[i].length; j++) {
                System.out.print(cityTeamNames[i][j] + " ");
            }
            System.out.println();
        }
        
        for(String[] teamName : cityTeamNames) {
            for(String name : teamName) {
                System.out.println(name);
            }
        }
    }

}
