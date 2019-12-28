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
public class DoubleXTest {
    
    DoubleX doubleX = new DoubleX();
    
    public DoubleXTest() {
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
     * Test of doubleX method, of class DoubleX.
     */
    @Test
    public void testDoubleX1() {
        assertTrue(doubleX.doubleX("axxbb"));
    }
    
    /**
     * Test of doubleX method, of class DoubleX.
     */
    @Test
    public void testDoubleX2() {
        assertFalse(doubleX.doubleX("axaxxax"));
    }
    
    /**
     * Test of doubleX method, of class DoubleX.
     */
    @Test
    public void testDoubleX3() {
        assertTrue(doubleX.doubleX("xxxxx"));
    }
    
}
