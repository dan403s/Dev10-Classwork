/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.controller;

import com.dvb.vendingmachine.dao.VendingMachinePersistenceException;
import com.dvb.vendingmachine.dto.Item;
import com.dvb.vendingmachine.service.InsufficientFundsException;
import com.dvb.vendingmachine.service.NegativeMoneyException;
import com.dvb.vendingmachine.service.NoItemInventoryException;
import com.dvb.vendingmachine.service.UnsupportedCoinException;
import com.dvb.vendingmachine.service.VendingMachineServiceLayer;
import com.dvb.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.SortedMap;

/**
 *
 * @author Daniel Bart
 */
public class VendingMachineController {

    // declare objects
    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    // pass object instantiation into constructor for dependency injection
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    // method that starts program
    public void run() {

        // declare variables and initialize if necessary
        boolean keepGoing = true;
        int userChoice = 0;

        // loop through menu choices as long as keepGoing is true
        while (keepGoing) {

            // try-catch for error handling (print out error message if caught)
            try {

                // coordinate displaying inventory and allow to exit
                getInventory();
                if (promptToExit() == 2) {
                    keepGoing = false;
                    break;
                }

                // coordinate adding money
                takeUserMoney();

                // store user selection in menuSelection as int
                userChoice = getUserChoice();

                // switch statement to determine what to do based upon menuSelection variable
                switch (userChoice) {
                    case 1:
                        purchaseItem();
                        break;
                    case 2:
                        takeUserMoney();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        throw new UnsupportedMenuOptionException("Error: Unknown Command");
                }

            } catch (VendingMachinePersistenceException | UnsupportedMenuOptionException | InsufficientFundsException | NoItemInventoryException e) {
                view.displayErrorMessage(e.getMessage());
                // if InsufficientFundsException thrown, display the total user money
                if (e.getMessage().equals("You do not have enough money. Give me more money.")) {
                    try {
                        view.displayTotalUserMoney(service.getTotalUserMoney());
                    } catch (VendingMachinePersistenceException ex) {
                        view.displayErrorMessage(ex.getMessage());
                    }
                }
            }
        }
        exitMessage();
        try {
            // get change at end of session
            getChange();
        } catch (UnsupportedCoinException | VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    // coordinate program methods --------------------------------------------------
    // coordinate getting inventory to display -------------------------------------
    private void getInventory() throws VendingMachinePersistenceException {
        view.displayInventoryBanner();
        SortedMap<Item, Integer> vendingMachineInventory = service.getInventory();
        view.displayInventory(vendingMachineInventory);
    }

    // coordinate entering money ---------------------------------------------------
    private void takeUserMoney() throws VendingMachinePersistenceException {
        BigDecimal currentUserMoneyTotal = service.getTotalUserMoney();
        view.displayTotalUserMoney(currentUserMoneyTotal);
        // loop try catch exception for entering money
        boolean isValid = false;
        do {
            try {
                BigDecimal userMoney = new BigDecimal(Double.toString(view.promptToEnterMoney()));
                service.takeUserMoney(userMoney);
                isValid = true;
            } catch (NegativeMoneyException e) {
                view.displayErrorMessage(e.getMessage());
            }
        } while (!isValid);

        BigDecimal newCurrentUserMoneyTotal = service.getTotalUserMoney();
        view.displayTotalUserMoney(newCurrentUserMoneyTotal);
    }

    // coordinate vend menu --------------------------------------------------------
    private int getUserChoice() {
        return view.promptForUserChoice();
    }

    // coordinate purchase menu ----------------------------------------------------
    private void purchaseItem() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        int purchaseChoice = view.promptForPurchaseChoice();
        Item itemPurchased = service.purchaseItem(purchaseChoice);
        view.displayItemPurchased(itemPurchased);
    }

    // coordinate getting change at end of session ---------------------------------
    private void getChange() throws UnsupportedCoinException, VendingMachinePersistenceException {
        view.displayGiveChangeBanner();
        List<Integer> changeDue = service.getChange();
        view.displayChangeDue(changeDue);
    }

    // coordinate exit -------------------------------------------------------------
    private int promptToExit() {
        return view.promptForExit();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
