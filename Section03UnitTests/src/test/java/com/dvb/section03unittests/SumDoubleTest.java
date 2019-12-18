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
public class SumDoubleTest {

    SumDouble sumDouble = new SumDouble();

    public SumDoubleTest() {
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
     * Test of sumDouble method, of class SumDouble.
     */
    @Test
    public void testSumDouble1() {
        assertEquals(3, sumDouble.sumDouble(1, 2));
    }

    /**
     * Test of sumDouble method, of class SumDouble.
     */
    @Test
    public void testSumDouble2() {
        assertEquals(5, sumDouble.sumDouble(3, 2));

    }

    /**
     * Test of sumDouble method, of class SumDouble.
     */
    @Test
    public void testSumDouble3() {
        assertEquals(8, sumDouble.sumDouble(2, 2));

    }

}
