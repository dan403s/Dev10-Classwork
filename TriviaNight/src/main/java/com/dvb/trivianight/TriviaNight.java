/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.trivianight;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class TriviaNight {

    public static void main(String[] args) {
        int totalCorrectAnswers = 0, totalWrongAnswers = 0, question1Answer,
                question2Answer, question3Answer, userAnswer1, userAnswer2,
                userAnswer3;

        Scanner sc = new Scanner(System.in);

        System.out.println("FIRST QUESTION!\n"
                + "What is the Lowest Level Programming Language?\n"
                + "1) Source Code\n"
                + "2) Assembly Language\n"
                + "3) C# Code\n"
                + "4) Machine Code\n"
        );
        question1Answer = 4;
        String question1AnswerString = sc.nextLine();
        userAnswer1 = Integer.parseInt(question1AnswerString);
        if (userAnswer1 == question1Answer) {
            totalCorrectAnswers++;
        } else {
            totalWrongAnswers++;
        }
        System.out.println("Your Answer: " + userAnswer1 + "\n");

        System.out.println("SECOND QUESTION!\n"
                + "Website Security CAPTCHA Forms Are Descended From the Work of?\n"
                + "1) Grace Hopper\n"
                + "2) Alan Turing\n"
                + "3) Charles Babbage\n"
                + "4) Larry Page\n"
        );
        question2Answer = 2;
        String question2AnswerString = sc.nextLine();
        userAnswer2 = Integer.parseInt(question2AnswerString);
        if (userAnswer2 == question2Answer) {
            totalCorrectAnswers++;
        } else {
            totalWrongAnswers++;
        }
        System.out.println("Your Answer: " + userAnswer2 + "\n");

        System.out.println("LAST QUESTION!\n"
                + "Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?\n"
                + "1) Serenity\n"
                + "2) The Battlestar Galactica\n"
                + "3) The USS Enterprise\n"
                + "4) The Millennium Falcon\n"
        );
        question3Answer = 3;
        String question3AnswerString = sc.nextLine();
        userAnswer3 = Integer.parseInt(question3AnswerString);
        if (userAnswer3 == question3Answer) {
            totalCorrectAnswers++;
        } else {
            totalWrongAnswers++;
        }
        System.out.println("Your Answer: " + userAnswer3 + "\n");
                
        if(totalCorrectAnswers == 3) {
            System.out.println("Congratulations, you got all 3 correct!");
        } else if(totalWrongAnswers == 3) {
            System.out.println("Bummer, you got all 3 wrong.");
        } else {
            System.out.println("You got " + totalCorrectAnswers + " correct!");
        }
    }

}
