/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.dao;

import static com.dvb.vendingmachine.dao.VendingMachineDaoFileImpl.inventoryFile;
import com.dvb.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Daniel Bart
 */
public class VendingMachineDaoTest {

    // declare and instantiate new object
    private VendingMachineDao dao = new VendingMachineDaoFileImpl();

    public VendingMachineDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        VendingMachineDaoFileImpl.inventoryFile = "inventory-test.txt";
    }

    @AfterAll
    public static void tearDownClass() {
        VendingMachineDaoFileImpl.inventoryFile = "inventory.txt";
    }

    // rewrite inventory-test.txt for inventory count reset
    @BeforeEach
    public void setUp() throws Exception {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(inventoryFile));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save inventory data.", e);
        }

        out.println("1::Snickers Bar::1.00::10");
        out.println("2::Doritos::2.00::5");
        out.println("3::Skittles::1.50::15");
        out.println("4::Sun Chips::2.50::5");
        out.println("5::M&Ms::1.25::15");

        out.flush();

        out.close();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * test of getInventory() method
     */
    @Test
    public void testGetInventory() throws Exception {
        // assert 5 is the inventory-test.txt file size
        assertEquals(5, dao.getInventory().size());

        // create new TreeMap and load it with inventory-test.txt key value pairs
        SortedMap<Item, Integer> vendingMachineInventory = new TreeMap<>(new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                Integer int1 = Integer.parseInt(i1.getId());
                Integer int2 = Integer.parseInt(i2.getId());
                return int1.compareTo(int2);
            }
        });

        Item item1 = new Item("1");
        item1.setItemName("Snickers Bar");
        item1.setItemCost(new BigDecimal("1.00"));
        vendingMachineInventory.put(item1, 10);

        Item item2 = new Item("2");
        item2.setItemName("Doritos");
        item2.setItemCost(new BigDecimal("2.00"));
        vendingMachineInventory.put(item2, 5);

        Item item3 = new Item("3");
        item3.setItemName("Skittles");
        item3.setItemCost(new BigDecimal("1.50"));
        vendingMachineInventory.put(item3, 15);

        Item item4 = new Item("4");
        item4.setItemName("Sun Chips");
        item4.setItemCost(new BigDecimal("2.50"));
        vendingMachineInventory.put(item4, 5);

        Item item5 = new Item("5");
        item5.setItemName("M&Ms");
        item5.setItemCost(new BigDecimal("1.25"));
        vendingMachineInventory.put(item5, 15);
        
        //asset TreeMap created is same as TreeMap pulled
        assertEquals(vendingMachineInventory, dao.getInventory());

    }

    /**
     * test of getItem() method
     */
    @Test
    public void testGetItem() throws Exception {
        //create HashMap to compare item made here to item pulled
        Map<Item, Integer> itemHashMap = new HashMap<>();
        
        Item item1 = new Item("1");
        item1.setItemName("Snickers Bar");
        item1.setItemCost(new BigDecimal("1.00"));
        itemHashMap.put(item1, 10);
        
        // assert HashMap created is same as Map pulled based on user choice and Id
        assertEquals(itemHashMap, dao.getItem(1));
        
    }

    /**
     * test of reduceItem() method
     */
    @Test
    public void testReduceItem() throws Exception {
        
        // add new Item to test
        Item item1 = new Item("1");
        item1.setItemName("Snickers Bar");
        item1.setItemCost(new BigDecimal("1.00"));
        
        // call reduceItem method and pass it Item and current inventory Integer
        dao.reduceItem(item1, 10);
        
        // call assertEquals with 9 as inventory Integer and the value returned as 9
        int inventoryCountAfterReduce = dao.getItem(1).get(item1);
        assertEquals(9, inventoryCountAfterReduce);
        
    }

}
