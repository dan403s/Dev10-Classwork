/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {

    // declare constants and initialize them
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    // add new Map to store students by studentId as key and Student object as value
    private Map<String, Student> students = new HashMap<>();

    // add new student string as key and Student object as value, return null if 
    // not in HashMap and old Student object if already in HashMap
    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterDaoException {
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    }

    // get all HashMap Student object values and store them in a new ArrayList, which will be returned
    @Override
    public List<Student> getAllStudents() throws ClassRosterDaoException {
        loadRoster();
        return new ArrayList<Student>(students.values());
    }

    // get specific Student object from ID
    @Override
    public Student getStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        return students.get(studentId);
    }

    // remove Student object, if student ID doesn't exist, return null, if Student 
    // object successfully removed, return old Student object
    @Override
    public Student removeStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }

    // unmarshall Student text file into Student object that is returned
    private Student unmarshallStudent(String studentAsText) {
        // studentAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1234::Ada::Lovelace::Java-September1842
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in studentTokens.
        // Which should look like this:
        // ______________________________________
        // |    |   |        |                  |
        // |1234|Ada|Lovelace|Java-September1842|
        // |    |   |        |                  |
        // --------------------------------------
        //  [0]  [1]    [2]         [3]
        String[] studentTokens = studentAsText.split(DELIMITER);

        // Given the pattern above, the student Id is in index 0 of the array
        String studentId = studentTokens[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        Student studentFromFile = new Student(studentId);

        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.
        // index 1 - first name
        studentFromFile.setFirstName(studentTokens[1]);

        // index 2 - last name
        studentFromFile.setLastName(studentTokens[2]);

        // index 3 - cohort
        studentFromFile.setCohort(studentTokens[3]);

        return studentFromFile;

    }

    // load roster file into memory; open file for reading, for each line in the file, 
    // do the following: read the line into a string variable, pass the line to our 
    // unmarshallStudent method to parse it into Student, put the Student object into the 
    // student map;; close file
    private void loadRoster() throws ClassRosterDaoException {
        // declare new Scanner object
        Scanner scanner;

        // try-catch-throw
        try {
            // instantiate new Scanner object for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterDaoException("-_- Could not load roster data into memory.", e);
        }

        // currentLine holds most recent line read from the file
        String currentLine;

        //currentStudent holds the most recent student unmarshalled
        Student currentStudent;

        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNext()) {
            currentLine = scanner.nextLine();
            currentStudent = unmarshallStudent(currentLine);
            students.put(currentStudent.getStudentId(), currentStudent);
        }

        scanner.close();
    }

    // marshall Student object in memory into text file that is returned
    private String marshallStudent(Student aStudent) {
        // We need to turn a Student object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 
        // Start with the student id, since that's supposed to be first.
        String studentAsText = aStudent.getStudentId() + DELIMITER;

        // add the rest of the properties in the correct order:
        // firstname
        studentAsText += aStudent.getFirstName() + DELIMITER;

        // lastname
        studentAsText += aStudent.getLastName() + DELIMITER;

        // cohort
        studentAsText += aStudent.getCohort();

        return studentAsText;
    }

    // write Student objects from memory into file; open file for writing, go through Student 
    // objects in map one by one, for each student, do the following: turn a Student into text, 
    //  using marshallStudent method, spaced by delimiter, write the string to the output file;; 
    // close file
    /**
     * Writes all students in the roster out to a ROSTER_FILE. See loadRoster
     * for file format.
     *
     * @throws ClassRosterDaoException if an error occurs writing to the file
     */
    private void writeRoster() throws ClassRosterDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new ClassRosterDaoException(
                    "Could not save student data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String studentAsText;
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList) {
            // turn a Student into a String
            studentAsText = marshallStudent(currentStudent);
            // write the Student object to the file
            out.println(studentAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
