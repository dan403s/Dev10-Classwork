/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.enumdaysuntilfriday;

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
public class DaysUntilFridayTest {

    DaysUntilFriday daysUntilFriday = new DaysUntilFriday();

    public DaysUntilFridayTest() {
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
     * Test of calculateDaysToFriday method, of class DaysUntilFriday.
     */
    @Test
    public void testCalculateDaysToFriday1() throws Exception {
        int expected = 6;
        int result = daysUntilFriday.calculateDaysToFriday("Saturday");
        assertEquals(expected, result);
    }

    /**
     * Test of calculateDaysToFriday method, of class DaysUntilFriday.
     */
    @Test
    public void testCalculateDaysToFriday2() throws Exception {
        int expected = 5;
        int result = daysUntilFriday.calculateDaysToFriday("Sunday");
        assertEquals(expected, result);
    }

    /**
     * Test of calculateDaysToFriday method, of class DaysUntilFriday.
     */
    @Test
    public void testCalculateDaysToFriday3() throws Exception {
        int expected = 4;
        int result = daysUntilFriday.calculateDaysToFriday("Monday");
        assertEquals(expected, result);
    }

    /**
     * Test of calculateDaysToFriday method, of class DaysUntilFriday.
     */
    @Test
    public void testCalculateDaysToFriday4() throws Exception {
        int expected = 3;
        int result = daysUntilFriday.calculateDaysToFriday("Tuesday");
        assertEquals(expected, result);
    }

    /**
     * Test of calculateDaysToFriday method, of class DaysUntilFriday.
     */
    @Test
    public void testCalculateDaysToFriday5() throws Exception {
        int expected = 2;
        int result = daysUntilFriday.calculateDaysToFriday("Wednesday");
        assertEquals(expected, result);
    }

    /**
     * Test of calculateDaysToFriday method, of class DaysUntilFriday.
     */
    @Test
    public void testCalculateDaysToFriday6() throws Exception {
        int expected = 1;
        int result = daysUntilFriday.calculateDaysToFriday("Thursday");
        assertEquals(expected, result);
    }

    /**
     * Test of calculateDaysToFriday method, of class DaysUntilFriday.
     */
    @Test
    public void testCalculateDaysToFriday7() throws Exception {
        int expected = 0;
        int result = daysUntilFriday.calculateDaysToFriday("Friday");
        assertEquals(expected, result);
    }

    /**
     * Test of calculateDaysToFriday method, of class DaysUntilFriday.
     */
    @Test
    public void testCalculateDaysToFriday8() throws Exception {
        UnsupportedDayException e = assertThrows(UnsupportedDayException.class,()->
        daysUntilFriday.calculateDaysToFriday("Other"));
        assertEquals("You typed other...that is not good for you.", e.getMessage());
    }

    /**
     * Test of calculateDaysToFriday method, of class DaysUntilFriday.
     */
    @Test
    public void testCalculateDaysToFriday9() throws Exception {    
        UnsupportedDayException e = assertThrows(UnsupportedDayException.class,()->
        daysUntilFriday.calculateDaysToFriday("abc"));
        assertEquals("Could not find day in enum...", e.getMessage());
    }

}
