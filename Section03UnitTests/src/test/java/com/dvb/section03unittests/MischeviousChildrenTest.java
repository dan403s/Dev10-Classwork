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
public class MischeviousChildrenTest {
    
    MischeviousChildren mischeviousChildren = new MischeviousChildren();
    
    public MischeviousChildrenTest() {
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
     * Test of areWeInTrouble method, of class MischeviousChildren.
     */
    @Test
    public void testAreWeInTroubleBothSmiling() {
        assertTrue(mischeviousChildren.areWeInTrouble(true, true));
    }
    
    /**
     * Test of areWeInTrouble method, of class MischeviousChildren.
     */
    @Test
    public void testAreWeInTroubleBothNotSmiling() {
        assertTrue(mischeviousChildren.areWeInTrouble(false, false));
    }
    
    /**
     * Test of areWeInTrouble method, of class MischeviousChildren.
     */
    @Test
    public void testAreWeInTroubleOneSmiling() {
        assertFalse(mischeviousChildren.areWeInTrouble(true, false));
    }
    
}
