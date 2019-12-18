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
public class AbbaTest {
    
    Abba abba = new Abba();
    
    public AbbaTest() {
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
     * Test of abba method, of class Abba.
     */
    @Test
    public void testAbbaHiBye() {
        String expectedResult = "HiByeByeHi";
        assertEquals(expectedResult, abba.abba("Hi", "Bye"));
    }
    
    /**
     * Test of abba method, of class Abba.
     */
    @Test
    public void testAbbaYoAlice() {
        String expectedResult = "YoAliceAliceYo";
        assertEquals(expectedResult, abba.abba("Yo", "Alice"));
    }
    
    /**
     * Test of abba method, of class Abba.
     */
    @Test
    public void testAbbaWhatUp() {
        String expectedResult = "WhatUpUpWhat";
        assertEquals(expectedResult, abba.abba("What", "Up"));
    }
    
}
