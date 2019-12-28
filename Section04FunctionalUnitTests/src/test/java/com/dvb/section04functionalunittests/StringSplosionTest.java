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
public class StringSplosionTest {
    
    StringSplosion stringSplosion = new StringSplosion();
    
    public StringSplosionTest() {
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
     * Test of stringSplosion method, of class StringSplosion.
     */
    @Test
    public void testStringSplosion1() {
        assertEquals("CCoCodCode", stringSplosion.stringSplosion("Code"));
    }
    
    /**
     * Test of stringSplosion method, of class StringSplosion.
     */
    @Test
    public void testStringSplosion2() {
        assertEquals("aababc", stringSplosion.stringSplosion("abc"));
    }
    
    /**
     * Test of stringSplosion method, of class StringSplosion.
     */
    @Test
    public void testStringSplosion3() {
        assertEquals("aab", stringSplosion.stringSplosion("ab"));
    }
    
}
