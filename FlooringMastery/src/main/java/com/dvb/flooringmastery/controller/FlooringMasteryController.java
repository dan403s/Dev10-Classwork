/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.controller;

import com.dvb.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.dvb.flooringmastery.dto.Order;
import com.dvb.flooringmastery.service.FlooringMasteryServiceLayer;
import com.dvb.flooringmastery.service.NoOrdersExistException;
import com.dvb.flooringmastery.service.UnknownProductTypeException;
import com.dvb.flooringmastery.service.UnknownStateException;
import com.dvb.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class FlooringMasteryController {

    // declare objects
    private FlooringMasteryView view;
    private FlooringMasteryServiceLayer service;

    // pass object instantiation into constructor for dependency injection
    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    // method that starts program
    public void run() {

        // declare variables and initialize if necessary
        boolean keepGoing = true;
        int userChoice = 0;

        // prompt user for training or production mode
        try {
            trainingOrProd();
        } catch (FlooringMasteryPersistenceException e) { // this will never run mwahahahaha
            view.displayErrorMessage(e.getMessage());
        }

        // loop through menu choices as long as keepGoing is true
        while (keepGoing) {

            // try-catch for error handling (print out error message if caught)
            try {

                // store user selection in menuSelection as int
                userChoice = getUserChoice();

                // switch statement to determine what to do based upon menuSelection variable
                switch (userChoice) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        throw new UnsupportedMenuOptionException("Error: Unknown Command");
                }

            } catch (FlooringMasteryPersistenceException | UnsupportedMenuOptionException | NoOrdersExistException | UnknownProductTypeException | UnknownStateException e) {
                view.displayErrorMessage(e.getMessage());
                view.promptForContinue();
            }
        }
        exitMessage();

    }

    // coordinate program methods --------------------------------------------------
    // coordinate user selecting training or production mode -----------------------
    private void trainingOrProd() throws FlooringMasteryPersistenceException {
        String trainingOrProd = view.promptForTrainingOrProdMode();
        service.trainingOrProd(trainingOrProd);
    }

    // coordinate getting user choice for initial menu -----------------------------
    private int getUserChoice() {
        view.displayInitialMenu();
        return view.promptForUserChoice();
    }

    // coordinate displaying orders with user choice by date or all ----------------
    private void displayOrders() throws FlooringMasteryPersistenceException, NoOrdersExistException {
        view.displayDisplayOrderBanner();
        int displayAllOrByDateChoice = view.promptForDisplayOptions();
        if (displayAllOrByDateChoice == 1) {
            // display all orders
            List<Order> ordersList = service.getAllOrders();
            view.displayOrders(ordersList);
            view.promptForContinue();
        } else {
            LocalDate responseLocalDate = view.promptForDisplayOrdersDate();
            // display orders by date
            List<Order> ordersList = service.getAllOrders(responseLocalDate);
            view.displayOrders(ordersList);
            view.promptForContinue();
        }
    }

    // coordinate adding an order --------------------------------------------------
    private void addOrder() throws FlooringMasteryPersistenceException, UnknownProductTypeException, UnknownStateException {
        view.displayAddOrderBanner();
        Set<String> productTypeSet = service.getProductTypeSet();
        Set<String> stateSet = service.getStateSet();
        String state = "";
        String productType = "";

        boolean isValidState = false;
        do {
            try {
                state = view.promptForState(stateSet);
                service.validateState(state);
                isValidState = true;
            } catch (UnknownStateException e) {
                System.out.println(e.getMessage());
            }

        } while (!isValidState);

        boolean isValidProductType = false;
        do {
            try {
                productType = view.promptForProductType(productTypeSet);
                service.validateProductType(productType);
                isValidProductType = true;
            } catch (UnknownProductTypeException e) {
                System.out.println(e.getMessage());
            }

        } while (!isValidProductType);

        Order newOrder = view.promptForAddOrderData(state, productType);
        String addOrderCommit = view.promptForAddOrderCommit();
        if (addOrderCommit.toLowerCase().equals("y")) {
            service.addOrder(newOrder);
        } else {
            return;
        }
        view.promptForContinue();
    }

    // coordinate editing an order -------------------------------------------------
    private void editOrder() throws FlooringMasteryPersistenceException, NoOrdersExistException, UnknownProductTypeException, UnknownStateException {
        view.displayEditOrderBanner();
        String orderNumber = view.promptForOrderNumber();
        Order orderToEdit = service.getOrderByOrderNumber(orderNumber);

        Set<String> productTypeSet = service.getProductTypeSet();
        Set<String> stateSet = service.getStateSet();
        String state = "";
        String productType = "";

        boolean isValidState = false;
        do {
            try {
                state = view.promptForState(stateSet, orderToEdit);
                if (!state.isEmpty() && !state.isBlank()) {
                    service.validateState(state);
                }
                isValidState = true;
            } catch (UnknownStateException e) {
                System.out.println(e.getMessage());
            }

        } while (!isValidState);

        boolean isValidProductType = false;
        do {
            try {
                productType = view.promptForProductType(productTypeSet, orderToEdit);
                if (!productType.isEmpty() && !productType.isBlank()) {
                    service.validateProductType(productType);
                }
                isValidProductType = true;
            } catch (UnknownProductTypeException e) {
                System.out.println(e.getMessage());
            }

        } while (!isValidProductType);

        orderToEdit = view.promptForEditOrderData(state, productType, orderToEdit);

        String editOrderCommit = view.promptForEditOrderCommit();
        if (editOrderCommit.toLowerCase().equals("y")) {
            service.editOrder(orderToEdit);
        } else {
            return;
        }
        view.promptForContinue();
    }

    // coordinate removing an order ------------------------------------------------
    private void removeOrder() throws FlooringMasteryPersistenceException, NoOrdersExistException {
        view.displayRemoveOrderBanner();
        String orderNumber = view.promptForOrderNumber();
        Order orderToRemove = service.getOrderByOrderNumber(orderNumber);
        view.displayOrder(orderToRemove);
        String removeOrderCommit = view.promptForRemoveOrderCommit();
        if (removeOrderCommit.toLowerCase().equals("y")) {
            service.removeOrder(orderToRemove);
        } else {
            return;
        }
        view.promptForContinue();
    }

    // coordinate displaying exit message ------------------------------------------
    private void exitMessage() {
        view.displayExitBanner();
    }

}
