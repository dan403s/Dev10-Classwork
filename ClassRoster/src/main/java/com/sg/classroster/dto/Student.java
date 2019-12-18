/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dto;

import java.util.Objects;

/**
 *
 * @author Daniel Bart
 */
public class Student {
    
    // declare variables and initialize if necessary
    private String firstName;
    private String lastName;
    private String studentId;
    // Java + cohort month/year
    private String cohort;

    // constructor passes in studentId at inception of Student object
    public Student(String studentId) {
        this.studentId = studentId;
    }

    // firstName getter
    public String getFirstName() {
        return firstName;
    }

    // firstName setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // lastName getter
    public String getLastName() {
        return lastName;
    }

    // lastName setter
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    // studentId getter
    public String getStudentId() {
        return studentId;
    }

    // cohort getter
    public String getCohort() {
        return cohort;
    }

    // cohort setter
    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.firstName);
        hash = 11 * hash + Objects.hashCode(this.lastName);
        hash = 11 * hash + Objects.hashCode(this.studentId);
        hash = 11 * hash + Objects.hashCode(this.cohort);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }
        if (!Objects.equals(this.cohort, other.cohort)) {
            return false;
        }
        return true;
    }
    
    
    
}
