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
public class SleepingInTest {
    
    SleepingIn sleepingIn = new SleepingIn();
    
    public SleepingInTest() {
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
     * Test of canSleepIn method, of class SleepingIn.
     */
    @Test
    public void testCanSleepIn1() {
        assertTrue(sleepingIn.canSleepIn(false, false));
    }
    
    /**
     * Test of canSleepIn method, of class SleepingIn.
     */
    @Test
    public void testCanSleepIn2() {
        assertFalse(sleepingIn.canSleepIn(true, false));
    }
    
    /**
     * Test of canSleepIn method, of class SleepingIn.
     */
    @Test
    public void testCanSleepIn3() {
        assertTrue(sleepingIn.canSleepIn(false, true));
    }
    
    /**
     * Test of canSleepIn method, of class SleepingIn.
     */
    @Test
    public void testCanSleepIn4() {
        assertTrue(sleepingIn.canSleepIn(true, true));
    }
    
}
