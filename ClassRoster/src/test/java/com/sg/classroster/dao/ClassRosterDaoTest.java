/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Daniel Bart
 */
public class ClassRosterDaoTest {

    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

    public ClassRosterDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        
    }

    @AfterAll
    public static void tearDownClass() {
    }

    // if throws Exception, will cause error and can later fix
    @BeforeEach
    public void setUp() throws Exception {
        // set DAO in known good state
        // makes for black box testing as we don't know how Student objects
        // are stored but we do know that we can use API of DAO to get
        // setup into good state
        List<Student> studentList = dao.getAllStudents();
        for (Student student : studentList) {
            dao.removeStudent(student.getStudentId());
        }
    }

    @AfterEach
    public void tearDown() {

    }

    /**
     * Test of addStudent method, of class ClassRosterDao.
     */
    @Test
    public void testAddGetStudent() throws Exception {
        Student student = new Student("0001");

        student.setFirstName("Joe");
        student.setLastName("Smith");
        student.setCohort("Java-May-2000");

        dao.addStudent(student.getStudentId(), student);

        Student fromDao = dao.getStudent(student.getStudentId());

        assertEquals(student, fromDao);
    }

    /**
     * Test of getAllStudents method, of class ClassRosterDao.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");
        
        dao.addStudent(student1.getStudentId(), student1);
        
        Student student2 = new Student("0002");
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setCohort(".NET-May-2000");
        
        dao.addStudent(student2.getStudentId(), student2);
        
        assertEquals(2, dao.getAllStudents().size());
    }

    /**
     * Test of getStudent method, of class ClassRosterDao.
     */
    @Test
    public void testGetStudent() throws Exception {
        
    }

    /**
     * Test of removeStudent method, of class ClassRosterDao.
     */
    @Test
    public void testRemoveStudent() throws Exception {
        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");
        
        dao.addStudent(student1.getStudentId(), student1);
        
        Student student2 = new Student("0002");
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setCohort(".NET-May-2000");
        
        dao.addStudent(student2.getStudentId(), student2);
        
        dao.removeStudent(student1.getStudentId());
        assertEquals(1, dao.getAllStudents().size());
        assertNull(dao.getStudent(student1.getStudentId()));
        
        dao.removeStudent(student2.getStudentId());
        assertEquals(0, dao.getAllStudents().size());
        assertNull(dao.getStudent(student2.getStudentId()));
    }

}
