/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.service;

import com.dvb.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.dvb.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public interface FlooringMasteryServiceLayer {

    // training or production mode -------------------------------------------------
    void trainingOrProd(String trainingOrProd) throws FlooringMasteryPersistenceException;

    String returnTrainingOrProd() throws FlooringMasteryPersistenceException;

    // displaying orders for all ---------------------------------------------------
    List<Order> getAllOrders() throws FlooringMasteryPersistenceException, NoOrdersExistException;

    // displaying orders with user choice by date ----------------------------------
    List<Order> getAllOrders(LocalDate responseLocalDate) throws FlooringMasteryPersistenceException, NoOrdersExistException;

    // get productType Set from products map ---------------------------------------
    Set<String> getProductTypeSet() throws FlooringMasteryPersistenceException;

    // get state Set from taxes map ------------------------------------------------
    Set<String> getStateSet() throws FlooringMasteryPersistenceException;

    // add Order with validation ---------------------------------------------------
    Order addOrder(Order newOrder) throws FlooringMasteryPersistenceException, UnknownProductTypeException, UnknownStateException;

    // validate state matches map --------------------------------------------------
    void validateState(String userState) throws FlooringMasteryPersistenceException, UnknownStateException;

    // validate productType matches map --------------------------------------------
    void validateProductType(String userProductType) throws FlooringMasteryPersistenceException, UnknownProductTypeException;

    // display order with user choice by orderNumber -------------------------------
    Order getOrderByOrderNumber(String orderNumber) throws FlooringMasteryPersistenceException, NoOrdersExistException;

    // remove order ----------------------------------------------------------------
    Order removeOrder(Order orderToRemove) throws FlooringMasteryPersistenceException;

    // edit order ------------------------------------------------------------------
    Order editOrder(Order orderToEdit) throws FlooringMasteryPersistenceException, UnknownProductTypeException, UnknownStateException;

}
