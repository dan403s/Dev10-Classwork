/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.dao;

import com.dvb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Daniel Bart
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    // declare only Item and only Map with 1 Item in it
    Item onlyItem;
    SortedMap<Item, Integer> onlyMap;

    // instantiate onlyItem and onlyMap in constructor
    public VendingMachineDaoStubImpl() {
        Item onlyItem = new Item("1");
        onlyItem.setItemName("Snickers Bar");
        onlyItem.setItemCost(new BigDecimal("1.00"));

        onlyMap = new TreeMap<>(new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                Integer int1 = Integer.parseInt(i1.getId());
                Integer int2 = Integer.parseInt(i2.getId());
                return int1.compareTo(int2);
            }
        });
        
        // add onlyItem into TreeMap with 3 in inventory
        onlyMap.put(onlyItem, 3);
        
    }

    @Override
    public SortedMap<Item, Integer> getInventory() throws VendingMachinePersistenceException {
        return onlyMap;
    }

    @Override
    public Map<Item, Integer> getItem(int userChoice) throws VendingMachinePersistenceException {
        if(userChoice == Integer.parseInt(onlyMap.firstKey().getId())) {
            return onlyMap;
        } else {
            return null;
        }
    }

    @Override
    public void reduceItem(Item itemToReduce, Integer count) throws VendingMachinePersistenceException {
        count--;
        onlyMap.put(itemToReduce, count);
    }

}
