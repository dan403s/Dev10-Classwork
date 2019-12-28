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
public class AlarmClockTest {
    
    AlarmClock alarmClock = new AlarmClock();
    
    public AlarmClockTest() {
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
     * Test of alarmClock method, of class AlarmClock.
     */
    @Test
    public void testAlarmClock1() {
        assertEquals("7:00", alarmClock.alarmClock(1, false));
    }
    
    /**
     * Test of alarmClock method, of class AlarmClock.
     */
    @Test
    public void testAlarmClock2() {
        assertEquals("7:00", alarmClock.alarmClock(5, false));
    }
    
    /**
     * Test of alarmClock method, of class AlarmClock.
     */
    @Test
    public void testAlarmClock3() {
        assertEquals("10:00", alarmClock.alarmClock(0, false));
    }
    
    /**
     * Test of alarmClock method, of class AlarmClock.
     */
    @Test
    public void testAlarmClock4() {
        assertEquals("10:00", alarmClock.alarmClock(1, true));
    }
    
    /**
     * Test of alarmClock method, of class AlarmClock.
     */
    @Test
    public void testAlarmClock5() {
        assertEquals("Off", alarmClock.alarmClock(6, true));
    }
    
    /**
     * Test of alarmClock method, of class AlarmClock.
     */
    @Test
    public void testAlarmClock6() {
        assertEquals("Off", alarmClock.alarmClock(0, true));
    }
    
}
