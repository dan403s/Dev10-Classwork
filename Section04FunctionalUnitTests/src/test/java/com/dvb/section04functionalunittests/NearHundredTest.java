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
public class NearHundredTest {
    
    NearHundred nearHundred = new NearHundred();
    
    public NearHundredTest() {
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
     * Test of nearHundred method, of class NearHundred.
     */
    @Test
    public void testNearHundred1() {
        assertTrue(nearHundred.nearHundred(103));
    }
    
    /**
     * Test of nearHundred method, of class NearHundred.
     */
    @Test
    public void testNearHundred2() {
        assertTrue(nearHundred.nearHundred(90));
    }
    
    /**
     * Test of nearHundred method, of class NearHundred.
     */
    @Test
    public void testNearHundred3() {
        assertFalse(nearHundred.nearHundred(89));
    }
    
    /**
     * Test of nearHundred method, of class NearHundred.
     */
    @Test
    public void testNearHundred4() {
        assertFalse(nearHundred.nearHundred(111));
    }
    
    /**
     * Test of nearHundred method, of class NearHundred.
     */
    @Test
    public void testNearHundred5() {
        assertTrue(nearHundred.nearHundred(-90));
    }
    
    /**
     * Test of nearHundred method, of class NearHundred.
     */
    @Test
    public void testNearHundred6() {
        assertFalse(nearHundred.nearHundred(-89));
    }
    
}
