/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.dao;

import com.dvb.flooringmastery.dto.Order;
import com.dvb.flooringmastery.dto.Product;
import com.dvb.flooringmastery.dto.Taxes;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public interface FlooringMasteryDao {

    /**
     * training or production mode
     *
     * @param trainingOrProd
     */
    void writeTrainingOrProdMode(String trainingOrProd) throws FlooringMasteryPersistenceException;

    /**
     * training or production mode
     *
     * @return String trainingOrProd
     */
    String loadTrainingOrProdMode() throws FlooringMasteryPersistenceException;

    /**
     * displaying orders for all
     *
     * @return Order list
     */
    List<Order> getOrders() throws FlooringMasteryPersistenceException;

    /**
     * displaying orders with user choice by date
     *
     * @param responseLocalDate
     * @return Order list
     */
    List<Order> getOrders(LocalDate responseLocalDate) throws FlooringMasteryPersistenceException;

    /**
     * get productType Set from products map
     *
     * @return String Set
     */
    Set<String> getProductTypeSet() throws FlooringMasteryPersistenceException;

    /**
     * get state Set from taxes map
     *
     * @return String Set
     */
    Set<String> getStateSet() throws FlooringMasteryPersistenceException;

    /**
     * return productsMap
     *
     * @return productsMap
     */
    Map<String, Product> getProductsMap() throws FlooringMasteryPersistenceException;

    /**
     * return taxesMap
     *
     * @return taxesMap
     */
    Map<String, Taxes> getTaxesMap() throws FlooringMasteryPersistenceException;

    /**
     * add order to ordersMap
     *
     * @param newOrder
     *
     */
    Order addOrder(Order newOrder) throws FlooringMasteryPersistenceException;

    /**
     * display order by orderNumber
     *
     * @param orderNumber
     * @return Order
     *
     */
    Order getOrderByOrderNumber(String orderNumber) throws FlooringMasteryPersistenceException;

    /**
     * remove order
     *
     * @param orderToRemove
     *
     */
    Order removeOrder(Order orderToRemove) throws FlooringMasteryPersistenceException;

    /**
     * edit order
     *
     * @param orderToEdit
     *
     */
    Order editOrder(Order orderToEdit) throws FlooringMasteryPersistenceException;
}
