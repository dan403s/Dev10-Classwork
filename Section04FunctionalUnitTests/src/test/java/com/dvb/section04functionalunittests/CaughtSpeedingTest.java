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
public class CaughtSpeedingTest {
    
    CaughtSpeeding caughtSpeeding = new CaughtSpeeding();
    
    public CaughtSpeedingTest() {
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
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding1() {
        assertEquals(0, caughtSpeeding.caughtSpeeding(60, false));
    }
    
    /**
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding2() {
        assertEquals(1, caughtSpeeding.caughtSpeeding(65, false));
    }
    
    /**
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding3() {
        assertEquals(0, caughtSpeeding.caughtSpeeding(65, true));
    }
    
    /**
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding4() {
        assertEquals(1, caughtSpeeding.caughtSpeeding(80, false));
    }
    
    /**
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding5() {
        assertEquals(2, caughtSpeeding.caughtSpeeding(81, false));
    }
    
    /**
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding6() {
        assertEquals(1, caughtSpeeding.caughtSpeeding(81, true));
    }
    
    /**
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding7() {
        assertEquals(2, caughtSpeeding.caughtSpeeding(85, false));
    }
    
    /**
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding8() {
        assertEquals(1, caughtSpeeding.caughtSpeeding(85, true));
    }
    
    /**
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding9() {
        assertEquals(2, caughtSpeeding.caughtSpeeding(90, true));
    }
    
}
