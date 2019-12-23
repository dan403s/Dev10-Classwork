/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.dao;

import com.dvb.vendingmachine.dto.Item;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 *
 * @author Daniel Bart
 */
public interface VendingMachineDao {

    /**
     * This method takes no parameters and returns the HashMap that stores the
     * Item object key and an Integer representing the inventory count.
     *
     * @return TreeMap with Item key and Integer representing inventory count
     */
    SortedMap<Item, Integer> getInventory() throws VendingMachinePersistenceException;

    /**
     * This method takes an int that the user types and returns the SortedMap
     * with the Item as key and inventory count as value.Unlike getInventory,
     * this returns only one key value pair
     *
     * @param userChoice
     * @return TreeMap with Item key and Integer representing inventory count
     */
    Map<Item, Integer> getItem(int userChoice) throws VendingMachinePersistenceException;

    /**
     * This method takes in the Item object and reduces the inventory in the
     * TreeMap of all items and inventory counts
     *
     * @param itemToReduce Item object
     * @param count of current inventory item
     */
    void reduceItem(Item itemToReduce, Integer count) throws VendingMachinePersistenceException;

}
