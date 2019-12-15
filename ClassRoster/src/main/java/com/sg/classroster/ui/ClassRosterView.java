/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public class ClassRosterView {

    // instantiate new UserIOConsoleImpl object, using UserIO interface as pointer 
    // reference, this will limit objects methods allowed to be called to methods in interface
    UserIO io;

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    // print menu and return user selection as int
    public int printMenuAndGetSelection() {

        io.print("Main Menu");
        io.print("1. List Student IDs");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        // return user selection in menuSelection as int
        return io.readInt("Please select from the above choices.", 1, 5);

    }

    // get new student info as a Student object
    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter cohort");

        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);

        return currentStudent;
    }

    // display banner for new student creation
    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }

    // display banner for new student creation success
    public void displayCreateSuccessBanner() {
        io.readString("Student successfully created. Please hit enter to continue");
    }

    // take list of Student objects from DAO and display info for each to screen
    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            io.print(currentStudent.getStudentId() + ": "
                    + currentStudent.getFirstName() + " "
                    + currentStudent.getLastName());
        }
        io.readString("Please hit enter to continue.");
    }

    // display banner for all students selection
    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }

    // display banner for individual student
    public void displayDisplayStudentBanner() {
        io.print("=== Display Student ===");
    }

    // asks user for ID of student he/she wishes to display
    public String getStudentIdChoice() {
        return io.readString("Please enter the Student ID.");
    }

    // display student's information to user
    public void displayStudent(Student student) {
        if (student != null) {
            io.print(student.getStudentId());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }

    // display remove student banner
    public void displayRemoveStudentBanner() {
        io.print("=== Remove Student ===");
    }

    // display removed student success banner
    public void displayRemoveSuccessBanner() {
        io.readString("Student successfully removed. Please hit enter to continue.");
    }

    // display exit banner when exit
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    // display unknown command when user types unknown command
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    // display errors
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
