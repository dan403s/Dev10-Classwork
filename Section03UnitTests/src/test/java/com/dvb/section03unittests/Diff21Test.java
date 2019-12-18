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
public class Diff21Test {
    
    Diff21 diff21 = new Diff21();
    
    public Diff21Test() {
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
     * Test of diff21 method, of class Diff21.
     */
    @Test
    public void testDiff21First() {
        assertEquals(4, diff21.diff21(23));
    }
    
    /**
     * Test of diff21 method, of class Diff21.
     */
    @Test
    public void testDiff21Second() {
        assertEquals(11, diff21.diff21(10));
    }
    
    /**
     * Test of diff21 method, of class Diff21.
     */
    @Test
    public void testDiff21Third() {
        assertEquals(0, diff21.diff21(21));
    }
    
}
