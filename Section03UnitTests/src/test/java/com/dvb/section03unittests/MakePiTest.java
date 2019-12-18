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
public class MakePiTest {
    
    MakePi makePi = new MakePi();
    
    public MakePiTest() {
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
     * Test of makePi method, of class MakePi.
     */
    @Test
    public void testMakePi1() {
        int[] pi = {3, 1, 4};
        assertArrayEquals(pi, makePi.makePi(3));
    }
    
    /**
     * Test of makePi method, of class MakePi.
     */
    @Test
    public void testMakePi2() {
        int[] pi = {3};
        assertArrayEquals(pi, makePi.makePi(1));
    }
    
    /**
     * Test of makePi method, of class MakePi.
     */
    @Test
    public void testMakePi3() {
        int[] pi = {3, 1, 4, 1, 5};
        assertArrayEquals(pi, makePi.makePi(5));
    }
    
    /**
     * Test of makePi method, of class MakePi.
     */
    @Test
    public void testMakePi4() {
        int[] pi = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        assertArrayEquals(pi, makePi.makePi(10));
    }
    
}
