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
public class FirstLast6Test {
    
    FirstLast6 firstLast6 = new FirstLast6();
    
    public FirstLast6Test() {
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
     * Test of firstLast6 method, of class FirstLast6.
     */
    @Test
    public void testFirstLast61() {
        int[] numbers = {1, 2, 6};
        assertTrue(firstLast6.firstLast6(numbers));
    }
    
    /**
     * Test of firstLast6 method, of class FirstLast6.
     */
    @Test
    public void testFirstLast62() {
        int[] numbers = {6, 1, 2, 3};
        assertTrue(firstLast6.firstLast6(numbers));
    }
    
    /**
     * Test of firstLast6 method, of class FirstLast6.
     */
    @Test
    public void testFirstLast63() {
        int[] numbers = {13, 6, 1, 2, 3};
        assertFalse(firstLast6.firstLast6(numbers));
    }
    
}
