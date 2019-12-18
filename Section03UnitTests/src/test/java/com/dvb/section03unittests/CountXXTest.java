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
public class CountXXTest {
    
    CountXX countXX = new CountXX();
    
    public CountXXTest() {
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
     * Test of countXX method, of class CountXX.
     */
    @Test
    public void testCountXX1() {
        assertEquals(1, countXX.countXX("abcxx"));
    }
    
    /**
     * Test of countXX method, of class CountXX.
     */
    @Test
    public void testCountXX2() {
        assertEquals(2, countXX.countXX("xxx"));
    }
    
    /**
     * Test of countXX method, of class CountXX.
     */
    @Test
    public void testCountXX3() {
        assertEquals(3, countXX.countXX("xxxx"));
    }
    
}
