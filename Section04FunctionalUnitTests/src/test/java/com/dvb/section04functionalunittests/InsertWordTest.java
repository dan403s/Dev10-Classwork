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
public class InsertWordTest {
    
    InsertWord insertWord = new InsertWord();
    
    public InsertWordTest() {
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
     * Test of insertWord method, of class InsertWord.
     */
    @Test
    public void testInsertWord1() {
        assertEquals("<<Yay>>", insertWord.insertWord("<<>>", "Yay"));
    }
    
    /**
     * Test of insertWord method, of class InsertWord.
     */
    @Test
    public void testInsertWord2() {
        assertEquals("<<WooHoo>>", insertWord.insertWord("<<>>", "WooHoo"));
    }
    
    /**
     * Test of insertWord method, of class InsertWord.
     */
    @Test
    public void testInsertWord3() {
        assertEquals("[[word]]", insertWord.insertWord("[[]]", "word"));
    }
    
    /**
     * Test of insertWord method, of class InsertWord.
     */
    @Test
    public void testInsertWord4() {
        assertEquals("<<<Yay>>>", insertWord.insertWord("<<<>>>", "Yay"));
    }
    
    /**
     * Test of insertWord method, of class InsertWord.
     */
    @Test
    public void testInsertWord5() {
        assertEquals("<<<<Yay>>>>", insertWord.insertWord("<<<<>>>>", "Yay"));
    }
    
    /**
     * Test of insertWord method, of class InsertWord.
     */
    @Test
    public void testInsertWord6() {
        assertEquals("<<<<<Yay>>>>>", insertWord.insertWord("<<<<<>>>>>", "Yay"));
    }
    
}
