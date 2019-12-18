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
public class FrontTimesTest {
    
    FrontTimes frontTimes = new FrontTimes();
    
    public FrontTimesTest() {
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
     * Test of frontTimes method, of class FrontTimes.
     */
    @Test
    public void testFrontTimesChocolate2() {
        String expectedResult = "ChoCho";
        assertEquals(expectedResult, frontTimes.frontTimes("Chocolate", 2));
    }
    
    /**
     * Test of frontTimes method, of class FrontTimes.
     */
    @Test
    public void testFrontTimesChocolate3() {
        String expectedResult = "ChoChoCho";
        assertEquals(expectedResult, frontTimes.frontTimes("Chocolate", 3));
    }
    
    /**
     * Test of frontTimes method, of class FrontTimes.
     */
    @Test
    public void testFrontTimesAbc3() {
        String expectedResult = "AbcAbcAbc";
        assertEquals(expectedResult, frontTimes.frontTimes("Abc", 3));
    }
    
    /**
     * Test of frontTimes method, of class FrontTimes.
     */
    @Test
    public void testFrontTimesAt4() {
        String expectedResult = "AtAtAtAt";
        assertEquals(expectedResult, frontTimes.frontTimes("At", 4));
    }
    
}
