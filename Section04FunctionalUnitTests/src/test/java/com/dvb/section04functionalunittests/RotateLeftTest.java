/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.section04functionalunittests;

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
public class RotateLeftTest {
    
    RotateLeft rotateLeft = new RotateLeft();
    
    public RotateLeftTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of rotateLeft method, of class RotateLeft.
     */
    @Test
    public void testRotateLeft1() {
        int[] test = {1, 2, 3};
        int[] results = {2, 3, 1};
        assertArrayEquals(results, rotateLeft.rotateLeft(test));
    }
    
    /**
     * Test of rotateLeft method, of class RotateLeft.
     */
    @Test
    public void testRotateLeft2() {
        int[] test = {5, 11, 9};
        int[] results = {11, 9, 5};
        assertArrayEquals(results, rotateLeft.rotateLeft(test));
    }
    
    /**
     * Test of rotateLeft method, of class RotateLeft.
     */
    @Test
    public void testRotateLeft3() {
        int[] test = {7, 0, 0};
        int[] results = {0, 0, 7};
        assertArrayEquals(results, rotateLeft.rotateLeft(test));
    }
    
    /**
     * Test of rotateLeft method, of class RotateLeft.
     */
    @Test
    public void testRotateLeft4() {
        int[] test = {1, 2, 3, 4};
        int[] results = {2, 3, 4, 1};
        assertArrayEquals(results, rotateLeft.rotateLeft(test));
    }
    
}
