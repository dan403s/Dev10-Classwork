/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.service;

import com.dvb.vendingmachine.dao.VendingMachinePersistenceException;
import com.dvb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.SortedMap;

/**
 *
 * @author Daniel Bart
 */
public interface VendingMachineServiceLayer {
    
    // setter for totalUserMoney, this is only for unit testing
    public void setTotalUserMoney(BigDecimal money);
    
    // pass-through method to pull HashMap from DAO and return it to Controller
    SortedMap<Item, Integer> getInventory() throws VendingMachinePersistenceException;
    
    // take user money
    void takeUserMoney(BigDecimal money) throws VendingMachinePersistenceException, NegativeMoneyException;
    
    // get user money total
    BigDecimal getTotalUserMoney() throws VendingMachinePersistenceException;
    
    // purchase item in vending machine
    Item purchaseItem(int userChoice) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException;
        
    // get change
    List<Integer> getChange() throws UnsupportedCoinException, VendingMachinePersistenceException;
}
