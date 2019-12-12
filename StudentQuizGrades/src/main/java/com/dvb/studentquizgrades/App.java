/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.studentquizgrades;

/**
 *
 * @author Daniel Bart
 */
public class App {

    public static void main(String[] args) {

        // instantiate new StudentQuizGrades object
        StudentQuizGrades studentQuizGradesInstance = new StudentQuizGrades();

        // call methods
        studentQuizGradesInstance.preLoadHashMap();
        do {
            studentQuizGradesInstance.promptForMenuChoice();
        } while (true);

    }

}
