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
public class PlayOutsideTest {
    
    PlayOutside playOutside = new PlayOutside();
    
    public PlayOutsideTest() {
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
     * Test of playOutside method, of class PlayOutside.
     */
    @Test
    public void testPlayOutside1() {
        assertTrue(playOutside.playOutside(70, false));
    }
    
    /**
     * Test of playOutside method, of class PlayOutside.
     */
    @Test
    public void testPlayOutside2() {
        assertFalse(playOutside.playOutside(95, false));
    }
    
    /**
     * Test of playOutside method, of class PlayOutside.
     */
    @Test
    public void testPlayOutside3() {
        assertTrue(playOutside.playOutside(95, true));
    }
    
}
