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
public class Makes10Test {
    
    Makes10 makes10 = new Makes10();
    
    public Makes10Test() {
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
     * Test of makes10 method, of class Makes10.
     */
    @Test
    public void testMakes101() {
        assertTrue(makes10.makes10(9, 10));
    }
    
    /**
     * Test of makes10 method, of class Makes10.
     */
    @Test
    public void testMakes102() {
        assertFalse(makes10.makes10(9, 9));
    }
    
    /**
     * Test of makes10 method, of class Makes10.
     */
    @Test
    public void testMakes103() {
        assertTrue(makes10.makes10(1, 9));
    }
    
}
