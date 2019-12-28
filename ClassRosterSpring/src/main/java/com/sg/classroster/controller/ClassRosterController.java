/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.controller;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import com.sg.classroster.service.ClassRosterDataValidationException;
import com.sg.classroster.service.ClassRosterDuplicateIdException;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.ui.ClassRosterView;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public class ClassRosterController {

    // instantiate new ClassRosterView object
    private ClassRosterView view;
    // instantiate new ClassRosterDaoFileImpl class object with ClassRosterDao interface reference pointer
    private ClassRosterServiceLayer service;

    public ClassRosterController(ClassRosterView view, ClassRosterServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        // declare variables and initialize if necessary
        boolean keepGoing = true;
        int menuSelection = 0;

        // try-catch for error handling (print out error message if caught)
        try {
            // loop through menu choices as long as keepGoing is true
            while (keepGoing) {

                // store user selection in menuSelection as int
                menuSelection = getMenuSelection();

                // switch statement to determine what to do based upon menuSelection variable
                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    // coordinate menu selection
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    // coordinate new student creation
    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = view.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    // coordinate student list
    private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }

    // coordinate viewing individual student
    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }

    // coordinate removing individual student
    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentID = view.getStudentIdChoice();
        service.removeStudent(studentID);
        view.displayRemoveSuccessBanner();
    }

    // coordinate unknown command option
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    // coordinate exit message
    private void exitMessage() {
        view.displayExitBanner();
    }

}
