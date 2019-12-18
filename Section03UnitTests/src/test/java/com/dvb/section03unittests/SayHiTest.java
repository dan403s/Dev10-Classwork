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
public class SayHiTest {
    
    SayHi sayHi = new SayHi();

    public SayHiTest() {
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

    @Test
    public void testBob() {
        String expectedResult = "Hello Bob!";
        assertEquals(expectedResult, sayHi.sayHi("Bob"));
    }

    @Test
    public void testAlice() {
        String expectedResult = "Hello Alice!";
        assertEquals(expectedResult, sayHi.sayHi("Alice"));
    }

    @Test
    public void testX() {
        String expectedResult = "Hello X!";
        assertEquals(expectedResult, sayHi.sayHi("X"));
    }

}
