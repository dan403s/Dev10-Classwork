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
public class FirstHalfTest {
    
    FirstHalf firstHalf = new FirstHalf();
    
    public FirstHalfTest() {
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
     * Test of firstHalf method, of class FirstHalf.
     */
    @Test
    public void testFirstHalf1() {
        assertEquals("Woo", firstHalf.firstHalf("WooHoo"));
    }
    
    /**
     * Test of firstHalf method, of class FirstHalf.
     */
    @Test
    public void testFirstHalf2() {
        assertEquals("Hello", firstHalf.firstHalf("HelloThere"));
    }
    
    /**
     * Test of firstHalf method, of class FirstHalf.
     */
    @Test
    public void testFirstHalf3() {
        assertEquals("abc", firstHalf.firstHalf("abcdef"));
    }
    
    /**
     * Test of firstHalf method, of class FirstHalf.
     */
    @Test
    public void testFirstHalf4() {
        assertEquals("Ok", firstHalf.firstHalf("OkNot"));
    }
    
}
