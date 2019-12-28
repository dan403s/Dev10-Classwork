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
public class MultipleEndingsTest {
    
    MultipleEndings multipleEndings = new MultipleEndings();
    
    public MultipleEndingsTest() {
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
     * Test of multipleEndings method, of class MultipleEndings.
     */
    @Test
    public void testMultipleEndings1() {
        assertEquals("lololo", multipleEndings.multipleEndings("Hello"));
    }
    
    /**
     * Test of multipleEndings method, of class MultipleEndings.
     */
    @Test
    public void testMultipleEndings2() {
        assertEquals("ababab", multipleEndings.multipleEndings("ab"));
    }
    
    /**
     * Test of multipleEndings method, of class MultipleEndings.
     */
    @Test
    public void testMultipleEndings3() {
        assertEquals("HiHiHi", multipleEndings.multipleEndings("Hi"));
    }
    
}
