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
public class ParrotTroubleTest {

    ParrotTrouble parrotTrouble = new ParrotTrouble();

    public ParrotTroubleTest() {
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
     * Test of parrotTrouble method, of class ParrotTrouble.
     */
    @Test
    public void testParrotTrouble1() {
        assertTrue(parrotTrouble.parrotTrouble(true, 6));
    }

    /**
     * Test of parrotTrouble method, of class ParrotTrouble.
     */
    @Test
    public void testParrotTrouble2() {
        assertFalse(parrotTrouble.parrotTrouble(true, 7));

    }

    /**
     * Test of parrotTrouble method, of class ParrotTrouble.
     */
    @Test
    public void testParrotTrouble3() {
        assertFalse(parrotTrouble.parrotTrouble(false, 6));

    }

}
