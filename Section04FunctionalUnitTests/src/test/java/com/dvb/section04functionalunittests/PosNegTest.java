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
public class PosNegTest {
    
    PosNeg posNeg = new PosNeg();
    
    public PosNegTest() {
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
     * Test of posNeg method, of class PosNeg.
     */
    @Test
    public void testPosNeg1() {
        assertTrue(posNeg.posNeg(1, -1, false));
    }
    
    /**
     * Test of posNeg method, of class PosNeg.
     */
    @Test
    public void testPosNeg2() {
        assertTrue(posNeg.posNeg(-1, 1, false));
    }
    
    /**
     * Test of posNeg method, of class PosNeg.
     */
    @Test
    public void testPosNeg3() {
        assertTrue(posNeg.posNeg(-4, -5, true));
    }
    
    /**
     * Test of posNeg method, of class PosNeg.
     */
    @Test
    public void testPosNeg4() {
        assertFalse(posNeg.posNeg(-1, -1, false));
    }
    
    /**
     * Test of posNeg method, of class PosNeg.
     */
    @Test
    public void testPosNeg5() {
        assertFalse(posNeg.posNeg(1, 1, false));
    }
    
    /**
     * Test of posNeg method, of class PosNeg.
     */
    @Test
    public void testPosNeg6() {
        assertFalse(posNeg.posNeg(1, 1, true));
    }
    
}
