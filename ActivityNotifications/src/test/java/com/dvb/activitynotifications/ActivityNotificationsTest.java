/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.activitynotifications;

import static com.dvb.activitynotifications.ActivityNotifications.activityNotifications;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Daniel Bart
 */
public class ActivityNotificationsTest {

    public ActivityNotificationsTest() {
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
     * Test of activityNotifications method, of class ActivityNotifications.
     */
    @Test
    public void testActivityNotifications0() throws Exception {
        String[] nd = {"9", "5"};
        int result = notificationMethod("testCase0.txt", nd);
        
        assertEquals(2, result);
    }

    /**
     * Test of activityNotifications method, of class ActivityNotifications.
     */
    @Test
    public void testActivityNotifications1() throws Exception {
        String[] nd = {"200000", "10000"};
        int result = notificationMethod("testCase1.txt", nd);
        
        assertEquals(633, result);
    }

    /**
     * Test of activityNotifications method, of class ActivityNotifications.
     */
    @Test
    public void testActivityNotifications2() throws Exception {
        String[] nd = {"200000", "9999"};
        int result = notificationMethod("testCase2.txt", nd);
        
        assertEquals(770, result);
    }

    /**
     * Test of activityNotifications method, of class ActivityNotifications.
     */
    @Test
    public void testActivityNotifications3() throws Exception {
        String[] nd = {"200000", "10122"};
        int result = notificationMethod("testCase3.txt", nd);
        
        assertEquals(1026, result);
    }

    /**
     * Test of activityNotifications method, of class ActivityNotifications.
     */
    @Test
    public void testActivityNotifications4() throws Exception {
        String[] nd = {"200000", "30000"};
        int result = notificationMethod("testCase4.txt", nd);
        
        assertEquals(492, result);
    }

    /**
     * Test of activityNotifications method, of class ActivityNotifications.
     */
    @Test
    public void testActivityNotifications5() throws Exception {
        String[] nd = {"200000", "40001"};
        int result = notificationMethod("testCase5.txt", nd);
        
        assertEquals(926, result);
    }

    /**
     * Test of activityNotifications method, of class ActivityNotifications.
     */
    @Test
    public void testActivityNotifications6() throws Exception {
        String[] nd = {"5", "4"};
        int result = notificationMethod("testCase6.txt", nd);
        
        assertEquals(0, result);
    }

    public int notificationMethod(String fileName, String[] nd) throws Exception {
        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

//        System.out.println("Enter array: ");
//        String[] expenditureItems = scanner.nextLine().split(" ");
        String[] expenditureItems = new String(Files.readAllBytes(Paths.get(fileName))).split(" ");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        return result;
    }

}
