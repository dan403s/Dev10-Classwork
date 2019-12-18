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
public class MakeTagsTest {

    MakeTags makeTags = new MakeTags();

    public MakeTagsTest() {
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
     * Test of makeTags method, of class MakeTags.
     */
    @Test
    public void testMakeTags1() {
        String expectedResult = "<i>Yay</i>";
        assertEquals(expectedResult, makeTags.makeTags("i", "Yay"));
    }

    /**
     * Test of makeTags method, of class MakeTags.
     */
    @Test
    public void testMakeTags2() {
        String expectedResult = "<i>Hello</i>";
        assertEquals(expectedResult, makeTags.makeTags("i", "Hello"));

    }

    /**
     * Test of makeTags method, of class MakeTags.
     */
    @Test
    public void testMakeTags3() {
        String expectedResult = "<cite>Yay</cite>";
        assertEquals(expectedResult, makeTags.makeTags("cite", "Yay"));

    }

}
