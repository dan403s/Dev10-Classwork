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
public class LongInMiddleTest {
    
    LongInMiddle longInMiddle = new LongInMiddle();
    
    public LongInMiddleTest() {
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
     * Test of longInMiddle method, of class LongInMiddle.
     */
    @Test
    public void testLongInMiddle1() {
        assertEquals("hiHellohi", longInMiddle.longInMiddle("Hello", "hi"));
    }
    
    /**
     * Test of longInMiddle method, of class LongInMiddle.
     */
    @Test
    public void testLongInMiddle2() {
        assertEquals("hiHellohi", longInMiddle.longInMiddle("hi", "Hello"));
    }
    
    /**
     * Test of longInMiddle method, of class LongInMiddle.
     */
    @Test
    public void testLongInMiddle3() {
        assertEquals("baaab", longInMiddle.longInMiddle("aaa", "b"));
    }
    
    /**
     * Test of longInMiddle method, of class LongInMiddle.
     */
    @Test
    public void testLongInMiddle4() {
        assertEquals("HahaIDIDITHaha", longInMiddle.longInMiddle("IDIDIT", "Haha"));
    }
    
}
