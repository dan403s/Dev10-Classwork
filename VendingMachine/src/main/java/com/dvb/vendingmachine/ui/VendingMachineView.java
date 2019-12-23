/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.ui;

import com.dvb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class VendingMachineView {

    // declare objects to be instantiated for dependency injection
    UserIO io;

    // constructor to initialize object for dependency injection
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    // display inventory -----------------------------------------------------------
    public void displayInventoryBanner() {
        io.print("\nHere is the inventory:");
    }

    public void displayInventory(Map<Item, Integer> inventorySortedMap) {
        Set<Item> inventorySet = inventorySortedMap.keySet();

        for (Item currentListKey : inventorySet) {
            Integer itemCount = inventorySortedMap.get(currentListKey);
            if (itemCount != 0) {
                io.print("Item number: " + currentListKey.getId() +  " Item name: " + currentListKey.getItemName() + " Price: " + currentListKey.getItemCost().setScale(2, RoundingMode.HALF_UP) + " Count in inventory: " + itemCount);
            }
        }
    }
    
    // prompt user to enter money --------------------------------------------------
    public double promptToEnterMoney() {
        return io.readDouble("\nHow much money do you want to put into the vending machine?");
    }
    
    // display total money user has
    public void displayTotalUserMoney(BigDecimal money) {
        io.print("\nYou currently have $" + money.setScale(2, RoundingMode.HALF_UP) + " in the machine.");
    }
    
    // prompt user for choice: buy something, add money, exit and get change --------
    public int promptForUserChoice() {
        return io.readInt("\nPress 1 to buy something. Press 2 to add more money. Press 3 to exit and get your change.", 1, 3);
    }
    
    // prompt user for choice of item to purchase
     public int promptForPurchaseChoice() {
        return io.readInt("\nPress the number of the corresponding item that you wish to purchase. ", 1, 10);
    }
     
     public void displayItemPurchased(Item item) {
         io.print("\nItem Purchased: " + item.getItemName());
         io.print("Item Price: " + item.getItemCost().setScale(2, RoundingMode.HALF_UP));
         io.print("Item ID: " + item.getId());
     }
    
    // general press enter to continue prompt --------------------------------------
    public void displayContinueBanner() {
        io.readString("\nPlease press enter to continue.");
    }

    // exiting ---- ----------------------------------------------------------------
    public int promptForExit() {
        return io.readInt("\nDo you wish to continue? Press 1 for yes. Press 2 for no.", 1, 2);
    }
    
    public void displayExitBanner() {
        io.print("\nThank you for vending with us.");
    }
    
    // giving change --------------------------------------------------------------
    public void displayGiveChangeBanner(){
        io.print("\nHere is your change:");
    }
    
    public void displayChangeDue(List<Integer> coinCounts) {
        int quarterCount = coinCounts.get(0);
        int dimeCount = coinCounts.get(1);
        int nickelCount = coinCounts.get(2);
        int pennyCount = coinCounts.get(3);
        
        io.print("\nQuarters: " + quarterCount);
        io.print("Dimes: " + dimeCount);
        io.print("Nickels: " + nickelCount);
        io.print("Pennies: " + pennyCount);
    }

    // error message ---------------------------------------------------------------
    public void displayErrorMessage(String errorMsg) {
        io.print("\nError!");
        io.print(errorMsg);
    }

}
