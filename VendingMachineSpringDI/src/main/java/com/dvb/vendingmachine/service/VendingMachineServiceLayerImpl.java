/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.service;

import com.dvb.vendingmachine.dao.VendingMachineAuditDao;
import com.dvb.vendingmachine.dao.VendingMachineDao;
import com.dvb.vendingmachine.dao.VendingMachinePersistenceException;
import com.dvb.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 *
 * @author Daniel Bart
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    // declare variables for dependency injection
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    // declare variables and initialize if necessary
    // totalUserMoney tracks how much money user has left
    private BigDecimal totalUserMoney = new BigDecimal("0");

    // constructor to instantiate object for dependency injection
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    // setter for totalUserMoney for testing purposes for beforeeach
    public void setTotalUserMoney(BigDecimal money) {
        this.totalUserMoney = money.setScale(2, RoundingMode.HALF_UP);
    }

    // pass-through method to pull HashMap from DAO and return it to Controller
    @Override
    public SortedMap<Item, Integer> getInventory() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("Inventory Accessed.");
        return dao.getInventory();
    }

    // take user money
    @Override
    public void takeUserMoney(BigDecimal money) throws VendingMachinePersistenceException, NegativeMoneyException {
        validateNonNegativeMoneyEntered(money);
        this.totalUserMoney = this.totalUserMoney.add(money.setScale(2, RoundingMode.HALF_UP));
        auditDao.writeAuditEntry("User added $" + money.setScale(2, RoundingMode.HALF_UP) + ".");
    }

    // get user money total
    @Override
    public BigDecimal getTotalUserMoney() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("Total user money was accessed.");
        return totalUserMoney;
    }

    // purchase item
    @Override
    public Item purchaseItem(int userChoice) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        Map<Item, Integer> itemAndStock = dao.getItem(userChoice);
        Set<Item> itemKeySet = itemAndStock.keySet();
        Item purchasedItem = null;
        Integer inventoryCount;
        for (Item item : itemKeySet) {
            purchasedItem = item;
        }
        inventoryCount = itemAndStock.get(purchasedItem);
        BigDecimal itemPrice = purchasedItem.getItemCost();
        // validate that totalUserMoney is enough
        validateFunds(itemPrice);
        // validate that item is in stock
        validateInventory(inventoryCount);
        // reduce totalUserMoney
        this.totalUserMoney = this.totalUserMoney.subtract(itemPrice);
        // decrement dao inventory
        dao.reduceItem(purchasedItem, inventoryCount);
        auditDao.writeAuditEntry("User made a purchase. Item purchased: " + purchasedItem.getItemName() + ".");
        return purchasedItem;
    }

    // get change
    @Override
    public List<Integer> getChange() throws UnsupportedCoinException, VendingMachinePersistenceException {
        // instantiate new change object
        Change change = new Change(totalUserMoney);
        // create new int array
        List<Integer> coinsArray = new ArrayList<>();
        // call change method to convert user money into coins
        change.createChange();
        // use Change getters to get coin counts
        int quarterCount = change.getQuartersDue();
        int dimeCount = change.getDimesDue();
        int nickelCount = change.getNickelsDue();
        int pennyCount = change.getPenniesDue();
        // load array
        coinsArray.add(quarterCount);
        coinsArray.add(dimeCount);
        coinsArray.add(nickelCount);
        coinsArray.add(pennyCount);

        auditDao.writeAuditEntry("Change given. Quarters: " + quarterCount + " Dimes: " + dimeCount + " Nickels: " + nickelCount + " Pennies: " + pennyCount);
        return coinsArray;

    }

    // validate if there is enough money, throw exception if not
    private void validateFunds(BigDecimal price) throws InsufficientFundsException {
        if (totalUserMoney.compareTo(price) == -1) {
            throw new InsufficientFundsException("You do not have enough money. Give me more money.");
        }
    }

    // validate that inventory is in stock, throw exception if not
    private void validateInventory(Integer count) throws NoItemInventoryException {
        if (count < 1) {
            throw new NoItemInventoryException("That item is out of stock. Please try again.");
        }
    }
    
    // validate that user enters money amount greater than or equal to zero
    private void validateNonNegativeMoneyEntered(BigDecimal moneyEntered) throws NegativeMoneyException {
            if(moneyEntered.compareTo(new BigDecimal(0)) < 0) {
                throw new NegativeMoneyException("You must enter 0 or more!!!");
            } 
    }
    

}
