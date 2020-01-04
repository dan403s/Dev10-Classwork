/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.service;

import com.dvb.flooringmastery.dao.FlooringMasteryAuditDao;
import com.dvb.flooringmastery.dao.FlooringMasteryDao;
import com.dvb.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.dvb.flooringmastery.dto.Order;
import com.dvb.flooringmastery.dto.Product;
import com.dvb.flooringmastery.dto.Taxes;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    // declare variables for dependency injection
    private FlooringMasteryDao dao;
    private FlooringMasteryAuditDao auditDao;

    // constructor to instantiate object for dependency injection
    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao, FlooringMasteryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    // logic and validation methods ------------------------------------------------
    // pass-through training or production mode ------------------------------------
    @Override
    public void trainingOrProd(String trainingOrProd) throws FlooringMasteryPersistenceException {
        dao.writeTrainingOrProdMode(trainingOrProd);
        auditDao.writeAuditEntry("Mode Selected: " + trainingOrProd + ".");
    }

    @Override
    public String returnTrainingOrProd() throws FlooringMasteryPersistenceException {
        return dao.loadTrainingOrProdMode();
    }

    // display all orders ----------------------------------------------------------
    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException, NoOrdersExistException {
        List<Order> ordersList = dao.getOrders();
        if (ordersList.isEmpty()) {
            auditDao.writeAuditEntry("Attempted to retrieve all orders, but none existed.");
            throw new NoOrdersExistException("ERROR!!! NO ORDERS TO DISPLAY!!!");
        } else {
            auditDao.writeAuditEntry("Retrieved all orders.");
            return ordersList;
        }
    }

    // displaying orders with user choice by date ----------------------------------
    @Override
    public List<Order> getAllOrders(LocalDate responseLocalDate) throws FlooringMasteryPersistenceException, NoOrdersExistException {
        List<Order> ordersList = dao.getOrders(responseLocalDate);
        if (ordersList.isEmpty()) {
            auditDao.writeAuditEntry("Attempted to retrieve orders with date of " + responseLocalDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + ", but none existed.");
            throw new NoOrdersExistException("ERROR!!! THAT DATE CONTAINS NO ORDERS!!!");
        } else {
            auditDao.writeAuditEntry("Retrieved orders with date of " + responseLocalDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + ".");
            return ordersList;
        }
    }

    // get productType Set from products map ---------------------------------------
    @Override
    public Set<String> getProductTypeSet() throws FlooringMasteryPersistenceException {
        auditDao.writeAuditEntry("Retrieved Set of product types.");
        return dao.getProductTypeSet();
    }

    // get state Set from taxes map ------------------------------------------------
    @Override
    public Set<String> getStateSet() throws FlooringMasteryPersistenceException {
        auditDao.writeAuditEntry("Retrieved Set of states.");
        return dao.getStateSet();
    }

    // add Order with validation ---------------------------------------------------
    @Override
    public Order addOrder(Order newOrder) throws FlooringMasteryPersistenceException, UnknownProductTypeException, UnknownStateException {
        // validate state
        String state = newOrder.getState();
        validateState(state);
        // validate productType
        String productType = newOrder.getProductType();
        validateProductType(productType);

        newOrder = calculateCosts(newOrder);

        // pass to dao to create new object in hashmap and write to file
        dao.addOrder(newOrder);
        auditDao.writeAuditEntry("Added new order with order number: " + newOrder.getOrderNumber() + ".");

        return newOrder;
    }

    // display order by orderNumber ------------------------------------------------
    @Override
    public Order getOrderByOrderNumber(String orderNumber) throws FlooringMasteryPersistenceException, NoOrdersExistException {
        Order order = dao.getOrderByOrderNumber(orderNumber);
        if (order == null) {
            auditDao.writeAuditEntry("Tried to retrieve order, but no order exists with order number: " + orderNumber + ".");
            throw new NoOrdersExistException("ERROR!!! NO ORDER EXISTS WITH THAT ORDER NUMBER!!!");
        } else {
            auditDao.writeAuditEntry("Retrieved order with order number: " + orderNumber + ".");
            return order;
        }
    }

    // remove order ----------------------------------------------------------------
    @Override
    public Order removeOrder(Order orderToRemove) throws FlooringMasteryPersistenceException {
        Order removedOrder = dao.removeOrder(orderToRemove);
        auditDao.writeAuditEntry("Removed order with order number: " + orderToRemove.getOrderNumber() + ".");
        return removedOrder;
    }

    // edit order ------------------------------------------------------------------
    @Override
    public Order editOrder(Order orderToEdit) throws FlooringMasteryPersistenceException, UnknownProductTypeException, UnknownStateException {
        // validate state
        String state = orderToEdit.getState();
        validateState(state);
        // validate productType
        String productType = orderToEdit.getProductType();
        validateProductType(productType);

        orderToEdit = calculateCosts(orderToEdit);

        // pass to dao to edit object in hashmap and write to file
        dao.editOrder(orderToEdit);
        auditDao.writeAuditEntry("Edited order with order number: " + orderToEdit.getOrderNumber() + ".");

        return orderToEdit;
    }

    // validate state matches map --------------------------------------------------
    @Override
    public void validateState(String userState) throws FlooringMasteryPersistenceException, UnknownStateException {
        // convert state to lower case
        userState = userState.toLowerCase();
        // get state set
        Set<String> statesInSet = dao.getStateSet();
        // convert all state set elements to lower case values with list
        List<String> statesInList = new ArrayList<>();
        for (String state : statesInSet) {
            state = state.toLowerCase();
            statesInList.add(state);
        }
        // if user state is in state set, pass validation, else, fail
        if (statesInList.contains(userState)) {
            auditDao.writeAuditEntry("Validated state: " + userState + ".");
            return;
        } else {
            auditDao.writeAuditEntry("Could not validate state: " + userState + ".");
            throw new UnknownStateException("ERROR!!! STATE NOT FOUND IN LIST OF ACCEPTABLE STATES!!!");
        }
    }

    // validate productType matches map --------------------------------------------
    @Override
    public void validateProductType(String userProductType) throws FlooringMasteryPersistenceException, UnknownProductTypeException {
        // convert productType to lower case
        userProductType = userProductType.toLowerCase();
        // get productType set
        Set<String> productTypesInSet = dao.getProductTypeSet();
        // convert all productType set elements to lower case values with list
        List<String> productTypesInList = new ArrayList<>();
        for (String productType : productTypesInSet) {
            productType = productType.toLowerCase();
            productTypesInList.add(productType);
        }
        // if user productType is in state set, pass validation, else, fail
        if (productTypesInList.contains(userProductType)) {
            auditDao.writeAuditEntry("Validated product type: " + userProductType + ".");
            return;
        } else {
            auditDao.writeAuditEntry("Could not validate product type: " + userProductType + ".");
            throw new UnknownProductTypeException("ERROR!!! PRODUCT TYPE NOT FOUND IN LIST OF ACCEPTABLE PRODUCT TYPES!!!");
        }
    }

    // calculate costs for everything
    private Order calculateCosts(Order orderToCalculate) throws FlooringMasteryPersistenceException {
        // pull in productsMap and taxesMap
        Map<String, Product> productsMap = dao.getProductsMap();
        Map<String, Taxes> taxesMap = dao.getTaxesMap();
        // pull in taxRate and set to Order
        BigDecimal taxRate = taxesMap.get(orderToCalculate.getState().toUpperCase()).getTaxRate();
        orderToCalculate.setTaxRate(taxRate);
        // pull in costPerSquareFoot and set to Order
        BigDecimal costPerSquareFoot = productsMap.get(orderToCalculate.getProductType().substring(0, 1).toUpperCase() + orderToCalculate.getProductType().substring(1, orderToCalculate.getProductType().length()).toLowerCase()).getCostPerSquareFoot();
        orderToCalculate.setCostPerSquareFoot(costPerSquareFoot);
        // pull in laborCostPerSquareFoot and set to Order
        BigDecimal laborCostPerSquareFoot = productsMap.get(orderToCalculate.getProductType().substring(0, 1).toUpperCase() + orderToCalculate.getProductType().substring(1, orderToCalculate.getProductType().length()).toLowerCase()).getLaborCostPerSquareFoot();
        orderToCalculate.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        // calculate materialCost and set to Order
        BigDecimal materialCost = costPerSquareFoot.multiply(orderToCalculate.getArea());
        orderToCalculate.setMaterialCost(materialCost);
        // calculate laborCost and set to Order
        BigDecimal laborCost = laborCostPerSquareFoot.multiply(orderToCalculate.getArea());
        orderToCalculate.setLaborCost(laborCost);
        // calculate tax and set to Order
        BigDecimal subtotal = materialCost.add(laborCost);
        BigDecimal tax = subtotal.multiply(taxRate);
        orderToCalculate.setTax(tax);
        // calculate total and set to Order
        BigDecimal total = subtotal.add(tax);
        orderToCalculate.setTotal(total);

        auditDao.writeAuditEntry("Calculated costs for order number: " + orderToCalculate.getOrderNumber() + ".");
        return orderToCalculate;
    }

}
