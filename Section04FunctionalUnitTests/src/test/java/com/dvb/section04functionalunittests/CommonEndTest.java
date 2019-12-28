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
public class CommonEndTest {
    
    CommonEnd commonEnd = new CommonEnd();
    
    public CommonEndTest() {
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
     * Test of commonEnd method, of class CommonEnd.
     */
    @Test
    public void testCommonEnd1() {
        int[] a = {1, 2, 3};
        int[] b = {7, 3};
        assertTrue(commonEnd.commonEnd(a, b));
    }
    
    /**
     * Test of commonEnd method, of class CommonEnd.
     */
    @Test
    public void testCommonEnd2() {
        int[] a = {1, 2, 3};
        int[] b = {7, 3, 2};
        assertFalse(commonEnd.commonEnd(a, b));
    }
    
    /**
     * Test of commonEnd method, of class CommonEnd.
     */
    @Test
    public void testCommonEnd3() {
        int[] a = {1, 2, 3};
        int[] b = {1, 3};
        assertTrue(commonEnd.commonEnd(a, b));
    }
    
    /**
     * Test of commonEnd method, of class CommonEnd.
     */
    @Test
    public void testCommonEnd4() {
        int[] a = {};
        int[] b = {7, 3};
        assertFalse(commonEnd.commonEnd(a, b));
    }
    
}
