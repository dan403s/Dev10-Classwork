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
public class SkipSumTest {
    
    SkipSum skipSum = new SkipSum();
    
    public SkipSumTest() {
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
     * Test of skipSum method, of class SkipSum.
     */
    @Test
    public void testSkipSum1() {
        assertEquals(7, skipSum.skipSum(3, 4));
    }
    
    /**
     * Test of skipSum method, of class SkipSum.
     */
    @Test
    public void testSkipSum2() {
        assertEquals(20, skipSum.skipSum(9, 4));
    }
    
    /**
     * Test of skipSum method, of class SkipSum.
     */
    @Test
    public void testSkipSum3() {
        assertEquals(21, skipSum.skipSum(10, 11));
    }
    
}
