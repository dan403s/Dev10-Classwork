/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.studentquizgrades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class StudentQuizGrades {

    // instantiate new Prompter object
    Prompter prompterInstance = new Prompter();

    // declare fields and initialize if necessary
    String menuPrompt = "\nPress 1 to view all students and their grades.\n"
            + "Press 2 to add a student.\n"
            + "Press 3 to remove a student.\n"
            + "Press 4 to view quiz scores for a student.\n"
            + "Press 5 to view average quiz score for a student.\n"
            + "Press 6 to calculate class average quiz score.\n"
            + "Press 7 to list student(s) with highest quiz score.\n"
            + "Press 8 to list student(s) with lowest quiz score.\n"
            + "Press 9 to exit.\n";
    int menuChoice;
    HashMap<String, ArrayList<Integer>> studentQuizScores = new HashMap<>();
    Set<String> keys;

    // preload HashMap with a few students
    public void preLoadHashMap() {
        studentQuizScores.put("Al", new ArrayList<>(Arrays.asList(100, 60, 80)));
        studentQuizScores.put("Bill", new ArrayList<>(Arrays.asList(85, 65, 90)));
        studentQuizScores.put("Chuck", new ArrayList<>(Arrays.asList(95, 35, 25)));
        studentQuizScores.put("Danielle", new ArrayList<>(Arrays.asList(10, 10, 10)));
        studentQuizScores.put("Elon", new ArrayList<>(Arrays.asList(100, 100, 100)));
        keys = studentQuizScores.keySet();
    }

    // gives user menu options
    public void promptForMenuChoice() {

        // print out menu options and store int choice as a variable, repeat if user types int outside min-max bounds
        System.out.println("\nMenu Options:");
        menuChoice = prompterInstance.readInt(menuPrompt, 1, 9);

        // determine action based on choice
        switch (menuChoice) {
            case 1:
                showStudentsAndGrades();
                break;
            case 2:
                addStudent();
                break;
            case 3:
                removeStudent();
                break;
            case 4:
                showGrades();
                break;
            case 5:
                showAverage();
                break;
            case 6:
                showClassAverage();
                break;
            case 7:
                showHighestStudentGrade();
                break;
            case 8:
                showLowestStudentGrade();
                break;
            case 9:
                exit();
                break;
        }

        keys = studentQuizScores.keySet();

//        System.out.println("Current Student Roster and Respective Grades: \n");
//        showStudentsAndGrades();
    }

    // menu choice 1
    public void showStudentsAndGrades() {
        System.out.println("Student Roster: \n");
        for (String key : keys) {
            Iterator<Integer> iterator = studentQuizScores.get(key).iterator();
            System.out.println(key + " Quiz Results:");
            while (iterator.hasNext()) {
                System.out.println("Quiz Grade: " + iterator.next());
            }
        }
    }

    // menu choice 2
    public void addStudent() {
        // prompt for student name and store in variable
        String studentName = prompterInstance.readString("Student Name: ");
        studentQuizScores.put(studentName, new ArrayList<>());

        // prompt if there are any grades and repeat until user selects no
        Integer gradesToEnterChoice;

        do {
            gradesToEnterChoice = prompterInstance.readInt("Are there any grades to enter (Press 1 for yes or 2 for no)? ", 1, 2);
            if (gradesToEnterChoice == 2) {
                break;
            }
            Integer gradeToEnter = prompterInstance.readInt("Enter the grade: ");
            studentQuizScores.get(studentName).add(gradeToEnter);
        } while (gradesToEnterChoice == 1);

    }

    // menu choice 3
    public void removeStudent() {
        // prompt for student name and store in variable
        String studentName = prompterInstance.readString("Which student would you like to remove? ");

        // remove HashMap key
        studentQuizScores.remove(studentName);
    }

    // menu choice 4
    public void showGrades() {
        // prompt for student name and store in variable and iterate through that students ArrayList
        String studentName = prompterInstance.readString("Which student would you like to see grades? ");
        Iterator<Integer> iterator = studentQuizScores.get(studentName).iterator();
        while (iterator.hasNext()) {
            System.out.println("Quiz Grade: " + iterator.next());
        }
    }

    // menu choice 5
    public void showAverage() {
        // prompt for student name and store in variable and iterate through that students ArrayList and average it and print results
        String studentName = prompterInstance.readString("Which student would you like to see average grade? ");
        Iterator<Integer> iterator = studentQuizScores.get(studentName).iterator();
        Integer sum = 0;
        Integer count = 0;
        while (iterator.hasNext()) {
            sum += iterator.next();
            count++;
        }
        System.out.println("Average Grade: " + sum / count);
    }

    // menu choice 6
    public void showClassAverage() {
        Integer sum = 0;
        Integer count = 0;
        for (String key : keys) {
            Iterator<Integer> iterator = studentQuizScores.get(key).iterator();
            while (iterator.hasNext()) {
                sum += iterator.next();
                count++;
            }
        }
        System.out.println("Class Average: " + sum / count);
    }

    // menu choice 7
    public void showHighestStudentGrade() {
        // new HashMap with name and highest grade from student
        HashMap<String, Integer> hashMap = new HashMap<>();
        // new Set that holds new HashMap's keys
        Set<String> set;
        // Integer used to hold max grade
        Integer highestGradeThusFar = 0;
        // new ArrayList used to store names of students with highest grade
        ArrayList<String> studentArrayList = new ArrayList<>();
        // look in studentQuizScores HashMap and get highest value for each student and put the students name and value into new HashMap
        for (String key : keys) {
            hashMap.put(key, Collections.max(studentQuizScores.get(key)));
        }
        // add new HashMaps keys to new Set
        set = hashMap.keySet();
        // iterate through new Set to determine highest grade out of new HashMap
        for (String s : set) {
            Integer grade = hashMap.get(s);
            if (grade > highestGradeThusFar) {
                highestGradeThusFar = grade;
            }
        }
        // iterate through new HashMap and add student names with highest grade determined earlier
        for (String s : set) {
            Integer grade = hashMap.get(s);
            if (grade == highestGradeThusFar) {
                studentArrayList.add(s);
            }
        }
        // print out new ArrayList containing highest score and students who achieved this monumental feat
        System.out.println("Highest quiz grade in class: " + highestGradeThusFar);
        for (String student : studentArrayList) {
            System.out.println(student + " achieved this.");
        }
    }

    // menu choice 8
    public void showLowestStudentGrade() {
        // new HashMap with name and lowest grade from student
        HashMap<String, Integer> hashMap = new HashMap<>();
        // new Set that holds new HashMap's keys
        Set<String> set;
        // Integer used to hold min grade
        Integer lowestGradeThusFar = 100;
        // new ArrayList used to store names of students with lowest grade
        ArrayList<String> studentArrayList = new ArrayList<>();
        // look in studentQuizScores HashMap and get lowest value for each student and put the students name and value into new HashMap
        for (String key : keys) {
            hashMap.put(key, Collections.min(studentQuizScores.get(key)));
        }
        // add new HashMaps keys to new Set
        set = hashMap.keySet();
        // iterate through new Set to determine lowest grade out of new HashMap
        for (String s : set) {
            Integer grade = hashMap.get(s);
            if (grade < lowestGradeThusFar) {
                lowestGradeThusFar = grade;
            }
        }
        // iterate through new HashMap and add student names with lowest grade determined earlier
        for (String s : set) {
            Integer grade = hashMap.get(s);
            if (grade == lowestGradeThusFar) {
                studentArrayList.add(s);
            }
        }
        // print out new ArrayList containing lowest score and students who achieved this hilarious feat
        System.out.println("Lowest quiz grade in class: " + lowestGradeThusFar);
        for (String student : studentArrayList) {
            System.out.println(student + " got this.");
        }
    }

    // menu choice 9
    public void exit() {
        System.out.println("Thanks for playing this awesome quiz game. Exiting now.");
        System.exit(0);
    }

}
