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
public class EveryOtherTest {
    
    EveryOther everyOther = new EveryOther();
    
    public EveryOtherTest() {
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
     * Test of everyOther method, of class EveryOther.
     */
    @Test
    public void testEveryOther1() {
        assertEquals("Hlo", everyOther.everyOther("Hello"));
    }
    
    /**
     * Test of everyOther method, of class EveryOther.
     */
    @Test
    public void testEveryOther2() {
        assertEquals("H", everyOther.everyOther("Hi"));
    }
    
    /**
     * Test of everyOther method, of class EveryOther.
     */
    @Test
    public void testEveryOther3() {
        assertEquals("Hello", everyOther.everyOther("Heeololeo"));
    }
    
}
