/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.service;

import com.dvb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Daniel Bart
 */
public class VendingMachineServiceLayerTest {

    // declare object
    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
//
//        service = new VendingMachineServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        service.setTotalUserMoney(new BigDecimal("0"));
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of setTotalUserMoney() method
     */
    @Test
    public void testSetTotalUserMoney() throws Exception {
        // create test BigDecimal and pass it into service method to set money
        BigDecimal testAmount = new BigDecimal("0.50");
        service.setTotalUserMoney(testAmount);
        // get amount back from service and assert that it equals the original amount
        BigDecimal returnedAmount = service.getTotalUserMoney();
        assertEquals(testAmount, returnedAmount);
    }

    /**
     * Test of getInventory() method
     */
    @Test
    public void testGetInventory() throws Exception {
        assertEquals(1, service.getInventory().size());
    }

    /**
     * Test of takeUserMoney() and getTotalUserMoney methods
     */
    @Test
    public void testTakeUserMoneyAndGetTotalUserMoney() throws Exception {
        // create test BigDecimal and pass it into service method to take money
        BigDecimal testAmount = new BigDecimal("0.50");
        service.takeUserMoney(testAmount);
        // get amount back from service and assert that it equals the original amount
        BigDecimal returnedAmount = service.getTotalUserMoney();
        assertEquals(testAmount, returnedAmount);
        // add another amount to takeUserMoney method which will add that amount to the original amount
        BigDecimal secondTestAmount = new BigDecimal("0.25");
        service.takeUserMoney(secondTestAmount);
        BigDecimal secondReturnedAmount = service.getTotalUserMoney();
        // assert that getTotalUserMoney returns the same amount as the first and second test amounts added together
        assertEquals(testAmount.add(secondTestAmount), secondReturnedAmount);

    }

    /**
     * Test of purchaseItem() method
     */
    @Test
    public void testPurchaseItem() throws Exception {
        // add money
        BigDecimal testAmount = new BigDecimal("1.50");
        service.takeUserMoney(testAmount);
        // call purchaseItem with user choice of 1, as that is the only choice available, returns item if no exceptions
        Item returnItem = service.purchaseItem(1);
        // if no exceptions, this test passes
        // assertEquals returnItem to Item in SortedMap
        assertEquals(returnItem, service.getInventory().firstKey());
    }

    /**
     * Test of purchaseItem() method with InsufficientFundsException thrown
     */
    @Test
    public void testPurchaseItemInsufficientFunds() throws Exception {
        // add money
        BigDecimal testAmount = new BigDecimal("0.50");
        service.takeUserMoney(testAmount);
        // test passes if InsufficientFundsException thrown
        try {
            service.purchaseItem(1);
            // if code makes it here, you fail
            fail("Expected InsufficientFundsException was not thrown.");
        } catch (InsufficientFundsException e) {
            // it would return without statement
            return;
        }
    }

    /**
     * Test of purchaseItem() method with NoItemInventoryException thrown
     */
    @Test
    public void testPurchaseItemNoItemInventory() throws Exception {
        // add money
        BigDecimal testAmount = new BigDecimal("10.00");
        service.takeUserMoney(testAmount);
        // test passes if NoItemInventoryException thrown
        try {
            // need to run 4 times because there are 3 Snickers Bars in inventory
            service.purchaseItem(1);
            service.purchaseItem(1);
            service.purchaseItem(1);
            service.purchaseItem(1);
            // if code makes it here, you fail
            fail("Expected NoItemInventoryException was not thrown.");
        } catch (NoItemInventoryException e) {
            // it would return without statement
            return;
        }
    }

    /**
     * Test of getChange() method
     */
    @Test
    public void testGetChange() throws Exception {
        // create list of integers to represent coins for a specific amount to test
        List<Integer> testCoinsArray = new ArrayList<>();
        // 4.91 in change
        int quarters = 19;
        int dimes = 1;
        int nickels = 1;
        int pennies = 1;
        // load the array!!!
        testCoinsArray.add(quarters);
        testCoinsArray.add(dimes);
        testCoinsArray.add(nickels);
        testCoinsArray.add(pennies);
        // add 4.91 using takeUserMoney method
        service.takeUserMoney(new BigDecimal("4.91"));
        // call getChange and store in new List
        List<Integer> resultsArray = service.getChange();
        // assertEquals on test array and results array
        assertEquals(testCoinsArray, resultsArray);
    }

}
