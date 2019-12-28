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
public class SumTest {
    
    Sum sum = new Sum();
    
    public SumTest() {
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
     * Test of sum method, of class Sum.
     */
    @Test
    public void testSum1() {
        int[] x = {1, 2, 3};
        assertEquals(6, sum.sum(x));
    }
    
    /**
     * Test of sum method, of class Sum.
     */
    @Test
    public void testSum2() {
        int[] x = {5, 11, 2};
        assertEquals(18, sum.sum(x));
    }
    
    /**
     * Test of sum method, of class Sum.
     */
    @Test
    public void testSum3() {
        int[] x = {7, 0, 0};
        assertEquals(7, sum.sum(x));
    }
    
}
