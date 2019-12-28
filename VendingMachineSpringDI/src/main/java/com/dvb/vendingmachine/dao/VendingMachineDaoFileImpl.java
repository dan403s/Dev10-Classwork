/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.dao;

import com.dvb.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author Daniel Bart
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    // declare variables/constants and initialize them
    public static String inventoryFile = "inventory.txt";
    public static final String DELIMITER = "::";

    // add new Map to store Item as key and inventory count as value
    private SortedMap<Item, Integer> vendingMachineInventory = new TreeMap<>(new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                Integer int1 = Integer.parseInt(i1.getId());
                Integer int2 = Integer.parseInt(i2.getId());
                return int1.compareTo(int2);
            }
        });

    // public methods here
    @Override
    public SortedMap<Item, Integer> getInventory() throws VendingMachinePersistenceException {
        loadInventory();     
        return vendingMachineInventory;
    }
    
    @Override
    public Map<Item, Integer> getItem(int userChoice) throws VendingMachinePersistenceException {
        loadInventory();
        Map<Item, Integer> filteredMap = vendingMachineInventory.entrySet()
                .stream()               
                .filter(i -> i.getKey().getId().equals(Integer.toString(userChoice)))
                .collect(Collectors.toMap(i -> i.getKey(), i -> i.getValue()));
        
        return filteredMap;
    }

    @Override
    public void reduceItem(Item itemToReduce, Integer count) throws VendingMachinePersistenceException {
        loadInventory();
        count--;
        vendingMachineInventory.replace(itemToReduce, count);
        writeInventory();
    }

    // unmarshall Item text file into HashMap with object as key and integer count as value
    private Map<Item, Integer> unmarshallItems(String itemListAsText) {

        String[] itemTokens = itemListAsText.split(DELIMITER);

        String id = itemTokens[0];

        Item itemFromFile = new Item(id);
        
        itemFromFile.setItemName(itemTokens[1]);

        itemFromFile.setItemCost(new BigDecimal(itemTokens[2]).setScale(2, RoundingMode.HALF_UP));

        Integer inventoryCount = Integer.parseInt(itemTokens[3]);

        Map<Item, Integer> newHashMap = new HashMap<>();

        newHashMap.put(itemFromFile, inventoryCount);

        return newHashMap;

    }

    // load inventory file into memory; open file for reading, for each line in the file, 
    // do the following: read the line into a string variable, pass the line to our 
    // unmarshallItems method to parse it into a HashMap, put the HashMap into the 
    // inventory map; close file
    private void loadInventory() throws VendingMachinePersistenceException {
        // declare new Scanner object
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(inventoryFile)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load inventory data into memory.", e);
        }

        // currentLine holds most recent line read from the file
        String currentLine;

        //currentItemList holds the most recent student unmarshalled
        Map<Item, Integer> currentItemHashMap;

        // Go through inventoryFile line by line, decoding each line into a 
        // HashMap by calling the unmarshallItems method.
        // Process while we have more lines in the file
        while (sc.hasNext()) {
            currentLine = sc.nextLine();
            currentItemHashMap = unmarshallItems(currentLine);
            vendingMachineInventory.putAll(currentItemHashMap);
        }

        sc.close();
    }

    // marshall HashMap in memory into text file that is returned
    private String marshallItems(Map<Item, Integer> aHashMapOfItems) {

        Set<Item> inventorySet = aHashMapOfItems.keySet();

        Item itemForStringGetters = null;

        for (Item inventoryItemKey : inventorySet) {

            itemForStringGetters = inventoryItemKey;

        }
                        
        String itemListAsText = itemForStringGetters.getId() + DELIMITER;
                
        itemListAsText += itemForStringGetters.getItemName() + DELIMITER;

        itemListAsText += itemForStringGetters.getItemCost().setScale(2, RoundingMode.HALF_UP) + DELIMITER;

        itemListAsText += aHashMapOfItems.get(itemForStringGetters);

        return itemListAsText;

    }

    // write HashMap from memory into file
    private void writeInventory() throws VendingMachinePersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(inventoryFile));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save inventory data.", e);
        }

        // Write out the List of Items to the inventory file.
        String itemListAsText;

        Set<Item> inventorySet = vendingMachineInventory.keySet();

        for (Item inventoryItemKey : inventorySet) {

            Map<Item, Integer> currentItemHashMap = new HashMap<>();

            currentItemHashMap.put(inventoryItemKey, vendingMachineInventory.get(inventoryItemKey));

            itemListAsText = marshallItems(currentItemHashMap);

            out.println(itemListAsText);

            out.flush();
        }

        out.close();
    }

}
