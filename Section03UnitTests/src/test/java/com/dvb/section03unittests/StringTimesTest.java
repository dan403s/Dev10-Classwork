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
public class StringTimesTest {
    
    StringTimes stringTimes = new StringTimes();
    
    public StringTimesTest() {
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
     * Test of stringTimes method, of class StringTimes.
     */
    @Test
    public void testStringTimes1() {
        String expectedString = "Hi";
        assertEquals(expectedString, stringTimes.stringTimes("Hi", 1));
    }
    
    @Test
    public void testStringTimes2() {
        String expectedString = "HiHi";
        assertEquals(expectedString, stringTimes.stringTimes("Hi", 2));
    }
    
    @Test
    public void testStringTimes3() {
        String expectedString = "HiHiHi";
        assertEquals(expectedString, stringTimes.stringTimes("Hi", 3));
    }
    
}
