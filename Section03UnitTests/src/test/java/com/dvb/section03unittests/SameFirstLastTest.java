/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.section03unittests;

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
public class SameFirstLastTest {
    
    SameFirstLast sameFirstLast = new SameFirstLast();
    
    public SameFirstLastTest() {
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
     * Test of sameFirstLast method, of class SameFirstLast.
     */
    @Test
    public void testSameFirstLast1() {
        int[] numbers = {1, 2, 3};
        assertFalse(sameFirstLast.sameFirstLast(numbers));
    }
    
    /**
     * Test of sameFirstLast method, of class SameFirstLast.
     */
    @Test
    public void testSameFirstLast2() {
        int[] numbers = {1, 2, 3, 1};
        assertTrue(sameFirstLast.sameFirstLast(numbers));
    }
    
    /**
     * Test of sameFirstLast method, of class SameFirstLast.
     */
    @Test
    public void testSameFirstLast3() {
        int[] numbers = {1, 2, 1};
        assertTrue(sameFirstLast.sameFirstLast(numbers));
    }
    
    /**
     * Test of sameFirstLast method, of class SameFirstLast.
     */
    @Test
    public void testSameFirstLast4() {
        int[] numbers = {};
        assertFalse(sameFirstLast.sameFirstLast(numbers));
    }
    
}
