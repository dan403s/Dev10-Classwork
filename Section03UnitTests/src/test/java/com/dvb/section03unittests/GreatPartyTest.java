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
public class GreatPartyTest {

    // instantiate GreatParty object, since it doesn't have state, you only need once
    // instance and can use it for every test
    GreatParty party = new GreatParty();

    public GreatPartyTest() {

    }

//    @org.junit.jupiter.api.BeforeAll
//    public static void setUpClass() throws Exception {
//    }
//
//    @org.junit.jupiter.api.AfterAll
//    public static void tearDownClass() throws Exception {
//    }
//
//    @org.junit.jupiter.api.BeforeEach
//    public void setUp() throws Exception {
//    }
//
//    @org.junit.jupiter.api.AfterEach
//    public void tearDown() throws Exception {
//    }
    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    // do not need because object has no state
    @BeforeEach
    public void setUp() {
    }

    // do not need because object has no state
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of greatParty method, of class GreatParty.
     */
    
    // greatParty(30, false) → false
    @Test
    public void test30False() {
        // run greatParty method from GreatParty object with test conditions
        // JUnit method that expects a false to be returned when you call your method, 
        // test passes if false is returned, test fails otherwise
        assertFalse(party.greatParty(30, false));
    }
    
    // greatParty(50, false) → true
    @Test
    public void test50False() {
        // run greatParty method from GreatParty object with test conditions
        // JUnit method that expects a true to be returned when you call your method, 
        // test passes if true is returned, test fails otherwise
        assertTrue(party.greatParty(50, false));
    }
    
    // greatParty(70, true) → true
    @Test
    public void test70True() {
        // run greatParty method from GreatParty object with test conditions
        // JUnit method that expects a true to be returned when you call your method, 
        // test passes if true is returned, test fails otherwise
        assertTrue(party.greatParty(70, true));
    }
    
    // greatParty(39, true) → false
    @Test
    public void test39True() {
        assertFalse(party.greatParty(39, true));
    }
    
    // greatParty(39, false) → false
    @Test
    public void test39False() {
        assertFalse(party.greatParty(39, false));
    }
    
    // greatParty(40, true) → true
    @Test
    public void test40True() {
        assertTrue(party.greatParty(40, true));
    }
    
    // greatParty(40, false) → true
    @Test
    public void test40False() {
        assertTrue(party.greatParty(40, false));
    }
    
    // greatParty(60, true) → true
    @Test
    public void test60True() {
        assertTrue(party.greatParty(60, true));
    }
    
    // greatParty(60, false) → true
    @Test
    public void test60False() {
        assertTrue(party.greatParty(60, false));
    }
    
    // greatParty(61, true) → true
    @Test
    public void test61True() {
        assertTrue(party.greatParty(61, true));
    }
    
    // greatParty(61, false) → false
    @Test
    public void test61False() {
        assertFalse(party.greatParty(61, false));
    }

}
