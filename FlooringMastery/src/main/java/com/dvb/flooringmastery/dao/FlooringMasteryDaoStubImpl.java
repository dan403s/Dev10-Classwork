/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.dao;

import com.dvb.flooringmastery.dto.Order;
import com.dvb.flooringmastery.dto.Product;
import com.dvb.flooringmastery.dto.Taxes;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {

    // declare variables and initialize with constructor
    String trainingOrProd;
    Order onlyOrder;
    List<Order> orderList = new ArrayList<>();
    Set<String> productSet = new HashSet<>();
    Set<String> stateSet = new HashSet<>();
    Map<String, Product> productMap = new HashMap<>();
    Map<String, Taxes> taxesMap = new HashMap<>();

    public FlooringMasteryDaoStubImpl() {
        trainingOrProd = "training";

        onlyOrder = new Order("01/01/2020:12:12:12"); // NEW ORDERNUMBER TO ADD SO IT IS NOT DUPLICATED IN TREEMAP (IT WOULD JUST REPLACE ANYWAYS)
        onlyOrder.setCustomerName("Luke Skywalker");
        onlyOrder.setState("PA");
        onlyOrder.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        onlyOrder.setProductType("Laminate");
        onlyOrder.setArea(new BigDecimal("50.00").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setMaterialCost(new BigDecimal("87.50").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setLaborCost(new BigDecimal("105.00").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTax(new BigDecimal("12.99").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTotal(new BigDecimal("205.49").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setDate(LocalDate.parse("06/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        orderList.add(onlyOrder);

        productSet.add("Carpet");
        productSet.add("Laminate");

        stateSet.add("OH");
        stateSet.add("PA");

        Product product1 = new Product("Carpet");
        product1.setCostPerSquareFoot(new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP));
        product1.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        productMap.put(product1.getProductType(), product1);

        Product product2 = new Product("Laminate");
        product2.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        product2.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        productMap.put(product2.getProductType(), product2);

        Taxes taxes1 = new Taxes("OH");
        taxes1.setTaxRate(new BigDecimal("6.25").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        taxesMap.put(taxes1.getState(), taxes1);

        Taxes taxes2 = new Taxes("PA");
        taxes2.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        taxesMap.put(taxes2.getState(), taxes2);

    }

    @Override
    public void writeTrainingOrProdMode(String trainingOrProd) throws FlooringMasteryPersistenceException {
        this.trainingOrProd = trainingOrProd;
    }

    @Override
    public String loadTrainingOrProdMode() throws FlooringMasteryPersistenceException {
        return trainingOrProd;
    }

    @Override
    public List<Order> getOrders() throws FlooringMasteryPersistenceException {
        return orderList;
    }

    @Override
    public List<Order> getOrders(LocalDate responseLocalDate) throws FlooringMasteryPersistenceException {
        if (responseLocalDate.equals(onlyOrder.getDate())) {
            return orderList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Set<String> getProductTypeSet() throws FlooringMasteryPersistenceException {
        return productSet;
    }

    @Override
    public Set<String> getStateSet() throws FlooringMasteryPersistenceException {
        return stateSet;
    }

    @Override
    public Map<String, Product> getProductsMap() throws FlooringMasteryPersistenceException {
        return productMap;
    }

    @Override
    public Map<String, Taxes> getTaxesMap() throws FlooringMasteryPersistenceException {
        return taxesMap;
    }

    @Override
    public Order addOrder(Order newOrder) throws FlooringMasteryPersistenceException {
        // do not use returned Order object in service layer; will always return null in dao
        return null;
    }

    @Override
    public Order getOrderByOrderNumber(String orderNumber) throws FlooringMasteryPersistenceException {
        if (orderNumber.equals(onlyOrder.getOrderNumber())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(Order orderToRemove) throws FlooringMasteryPersistenceException {
        if (orderToRemove.getOrderNumber().equals(onlyOrder.getOrderNumber())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order editOrder(Order orderToEdit) throws FlooringMasteryPersistenceException {
        if (orderToEdit.getOrderNumber().equals(onlyOrder.getOrderNumber())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

}
