/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.dao;

import com.dvb.flooringmastery.dto.Order;
import com.dvb.flooringmastery.dto.Product;
import com.dvb.flooringmastery.dto.Taxes;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Daniel Bart
 */
public class FlooringMasteryDaoTest {

    // declare and instantiate new object
    private FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();

    public FlooringMasteryDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        // before each test method, set training mode
        String trainingOrProd = "training";
        dao.writeTrainingOrProdMode(trainingOrProd);

        // before each test, write default to test file
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("Orders-Training.txt"));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save inventory data.", e);
        }

        out.println("01/01/2020:10:12:26::Luke Skywalker::PA::6.75::Laminate::50.00::1.75::2.10::87.50::105.00::12.99::205.49::11/30/2020");
        out.println("01/01/2020:12:51:59::Yoda::IN::6.00::Carpet::100.00::2.25::2.10::225.00::210.00::26.10::461.10::02/01/2020");
        out.println("11/30/2019:12:31:03::Darth Vader::OH::6.25::Laminate::100.00::1.75::2.10::175.00::210.00::24.06::409.06::11/30/2020");

        out.flush();

        out.close();
    }

    @AfterEach
    public void tearDown() throws Exception {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("Orders-Training.txt"));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save inventory data.", e);
        }

        out.println("01/01/2020:10:12:26::Luke Skywalker::PA::6.75::Laminate::50.00::1.75::2.10::87.50::105.00::12.99::205.49::11/30/2020");
        out.println("01/01/2020:12:51:59::Yoda::IN::6.00::Carpet::100.00::2.25::2.10::225.00::210.00::26.10::461.10::02/01/2020");
        out.println("11/30/2019:12:31:03::Darth Vader::OH::6.25::Laminate::100.00::1.75::2.10::175.00::210.00::24.06::409.06::11/30/2020");

        out.flush();

        out.close();
    }

    @Test
    public void testWriteAndLoadTrainingOrProdMode() throws Exception {
        // set training mode
        String trainingOrProd = "training";
        dao.writeTrainingOrProdMode(trainingOrProd);
        // load training mode
        String result = dao.loadTrainingOrProdMode();

        assertEquals(result, trainingOrProd);
    }

    @Test
    public void testGetOrders() throws Exception {
        // assert 3 is the Orders-Training.txt file size
        assertEquals(3, dao.getOrders().size());

        // create List<Orders> and compare to getOrders() method results
        List<Order> ordersListCreatedHere = new ArrayList<>();

        // existing order in Map
        Order order1 = new Order("01/01/2020:10:12:26");
        order1.setCustomerName("Luke Skywalker");
        order1.setState("PA");
        order1.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("50.00").setScale(2, RoundingMode.HALF_UP));
        order1.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order1.setMaterialCost(new BigDecimal("87.50").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCost(new BigDecimal("105.00").setScale(2, RoundingMode.HALF_UP));
        order1.setTax(new BigDecimal("12.99").setScale(2, RoundingMode.HALF_UP));
        order1.setTotal(new BigDecimal("205.49").setScale(2, RoundingMode.HALF_UP));
        order1.setDate(LocalDate.parse("11/30/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        // add order to list
        ordersListCreatedHere.add(order1);

        // existing order in Map
        Order order2 = new Order("01/01/2020:12:51:59");
        order2.setCustomerName("Yoda");
        order2.setState("IN");
        order2.setTaxRate(new BigDecimal("6.00").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order2.setProductType("Carpet");
        order2.setArea(new BigDecimal("100.00").setScale(2, RoundingMode.HALF_UP));
        order2.setCostPerSquareFoot(new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP));
        order2.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order2.setMaterialCost(new BigDecimal("225.00").setScale(2, RoundingMode.HALF_UP));
        order2.setLaborCost(new BigDecimal("210.00").setScale(2, RoundingMode.HALF_UP));
        order2.setTax(new BigDecimal("26.10").setScale(2, RoundingMode.HALF_UP));
        order2.setTotal(new BigDecimal("461.10").setScale(2, RoundingMode.HALF_UP));
        order2.setDate(LocalDate.parse("02/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        // add order to list
        ordersListCreatedHere.add(order2);

        // existing order in Map
        Order order3 = new Order("11/30/2019:12:31:03");
        order3.setCustomerName("Darth Vader");
        order3.setState("OH");
        order3.setTaxRate(new BigDecimal("6.25").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order3.setProductType("Laminate");
        order3.setArea(new BigDecimal("100.00").setScale(2, RoundingMode.HALF_UP));
        order3.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        order3.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order3.setMaterialCost(new BigDecimal("175.00").setScale(2, RoundingMode.HALF_UP));
        order3.setLaborCost(new BigDecimal("210.00").setScale(2, RoundingMode.HALF_UP));
        order3.setTax(new BigDecimal("24.06").setScale(2, RoundingMode.HALF_UP));
        order3.setTotal(new BigDecimal("409.06").setScale(2, RoundingMode.HALF_UP));
        order3.setDate(LocalDate.parse("11/30/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        // add order to list
        ordersListCreatedHere.add(order3);

        List<Order> results = dao.getOrders();

        assertEquals(ordersListCreatedHere, results);
    }

    @Test
    public void testGetOrdersWithParameter() throws Exception {
        // assert 3 is the Orders-Training.txt file size
        LocalDate testLocalDate = LocalDate.parse("11/30/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        assertEquals(2, dao.getOrders(testLocalDate).size());

        // create List<Orders> and compare to getOrders() method results
        List<Order> ordersListCreatedHere = new ArrayList<>();

        // existing order in Map
        Order order1 = new Order("01/01/2020:10:12:26");
        order1.setCustomerName("Luke Skywalker");
        order1.setState("PA");
        order1.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("50.00").setScale(2, RoundingMode.HALF_UP));
        order1.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order1.setMaterialCost(new BigDecimal("87.50").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCost(new BigDecimal("105.00").setScale(2, RoundingMode.HALF_UP));
        order1.setTax(new BigDecimal("12.99").setScale(2, RoundingMode.HALF_UP));
        order1.setTotal(new BigDecimal("205.49").setScale(2, RoundingMode.HALF_UP));
        order1.setDate(LocalDate.parse("11/30/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        // add order to list
        ordersListCreatedHere.add(order1);

        // existing order in Map
        Order order2 = new Order("11/30/2019:12:31:03");
        order2.setCustomerName("Darth Vader");
        order2.setState("OH");
        order2.setTaxRate(new BigDecimal("6.25").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order2.setProductType("Laminate");
        order2.setArea(new BigDecimal("100.00").setScale(2, RoundingMode.HALF_UP));
        order2.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        order2.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order2.setMaterialCost(new BigDecimal("175.00").setScale(2, RoundingMode.HALF_UP));
        order2.setLaborCost(new BigDecimal("210.00").setScale(2, RoundingMode.HALF_UP));
        order2.setTax(new BigDecimal("24.06").setScale(2, RoundingMode.HALF_UP));
        order2.setTotal(new BigDecimal("409.06").setScale(2, RoundingMode.HALF_UP));
        order2.setDate(LocalDate.parse("11/30/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        // add order to list
        ordersListCreatedHere.add(order2);

        List<Order> results = dao.getOrders(testLocalDate);

        assertEquals(ordersListCreatedHere, results);
    }

    @Test
    public void testGetProductTypeSet() throws Exception {
        // assert 4 is the amount of products returned from dao in Set
        assertEquals(4, dao.getProductTypeSet().size());

        // create test Set and compare to Set returned from dao
        Set<String> testSet = new HashSet<>();
        testSet.add("Carpet");
        testSet.add("Laminate");
        testSet.add("Tile");
        testSet.add("Wood");

        Set<String> resultSet = dao.getProductTypeSet();

        assertEquals(testSet, resultSet);
    }

    @Test
    public void testGetStateSet() throws Exception {
        // assert 4 is the amount of products returned from dao in Set
        assertEquals(4, dao.getStateSet().size());

        // create test Set and compare to Set returned from dao
        Set<String> testSet = new HashSet<>();
        testSet.add("OH");
        testSet.add("PA");
        testSet.add("MI");
        testSet.add("IN");

        Set<String> resultSet = dao.getStateSet();

        assertEquals(testSet, resultSet);
    }

    @Test
    public void testGetProductsMap() throws Exception {
        // assert 4 is the amount of products returned from dao in Map
        assertEquals(4, dao.getProductsMap().size());

        // create test Map and compare to Map returned from dao
        Map<String, Product> testMap = new HashMap<>();

        Product product1 = new Product("Carpet");
        product1.setCostPerSquareFoot(new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP));
        product1.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        testMap.put(product1.getProductType(), product1);

        Product product2 = new Product("Laminate");
        product2.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        product2.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        testMap.put(product2.getProductType(), product2);

        Product product3 = new Product("Tile");
        product3.setCostPerSquareFoot(new BigDecimal("3.50").setScale(2, RoundingMode.HALF_UP));
        product3.setLaborCostPerSquareFoot(new BigDecimal("4.15").setScale(2, RoundingMode.HALF_UP));
        testMap.put(product3.getProductType(), product3);

        Product product4 = new Product("Wood");
        product4.setCostPerSquareFoot(new BigDecimal("5.15").setScale(2, RoundingMode.HALF_UP));
        product4.setLaborCostPerSquareFoot(new BigDecimal("4.75").setScale(2, RoundingMode.HALF_UP));
        testMap.put(product4.getProductType(), product4);

        Map<String, Product> resultMap = dao.getProductsMap();

        assertEquals(testMap, resultMap);
    }

    @Test
    public void testGetTaxesMap() throws Exception {
        // assert 4 is the amount of products returned from dao in Map
        assertEquals(4, dao.getTaxesMap().size());

        // create test Map and compare to Map returned from dao
        Map<String, Taxes> testMap = new HashMap<>();

        Taxes taxes1 = new Taxes("OH");
        taxes1.setTaxRate(new BigDecimal("6.25").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        testMap.put(taxes1.getState(), taxes1);

        Taxes taxes2 = new Taxes("PA");
        taxes2.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        testMap.put(taxes2.getState(), taxes2);

        Taxes taxes3 = new Taxes("MI");
        taxes3.setTaxRate(new BigDecimal("5.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        testMap.put(taxes3.getState(), taxes3);

        Taxes taxes4 = new Taxes("IN");
        taxes4.setTaxRate(new BigDecimal("6.00").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        testMap.put(taxes4.getState(), taxes4);

        Map<String, Taxes> resultMap = dao.getTaxesMap();

        assertEquals(testMap, resultMap);
    }

    @Test
    public void testAddOrder() throws Exception {
        // create new order and add to dao and call method to return order added and compare the two
        Order order1 = new Order("01/01/2020:12:12:12"); // NEW ORDERNUMBER TO ADD SO IT IS NOT DUPLICATED IN TREEMAP (IT WOULD JUST REPLACE ANYWAYS)
        order1.setCustomerName("Luke Skywalker");
        order1.setState("PA");
        order1.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("50.00").setScale(2, RoundingMode.HALF_UP));
        order1.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order1.setMaterialCost(new BigDecimal("87.50").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCost(new BigDecimal("105.00").setScale(2, RoundingMode.HALF_UP));
        order1.setTax(new BigDecimal("12.99").setScale(2, RoundingMode.HALF_UP));
        order1.setTotal(new BigDecimal("205.49").setScale(2, RoundingMode.HALF_UP));
        order1.setDate(LocalDate.parse("11/30/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addOrder(order1);

        Order resultOrder = dao.getOrderByOrderNumber("01/01/2020:12:12:12");

        assertEquals(order1, resultOrder);
    }

    @Test
    public void testGetOrderByOrderNumber() throws Exception {
        // assert that order created matches order from dao
        Order order1 = new Order("01/01/2020:10:12:26");
        order1.setCustomerName("Luke Skywalker");
        order1.setState("PA");
        order1.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("50.00").setScale(2, RoundingMode.HALF_UP));
        order1.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order1.setMaterialCost(new BigDecimal("87.50").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCost(new BigDecimal("105.00").setScale(2, RoundingMode.HALF_UP));
        order1.setTax(new BigDecimal("12.99").setScale(2, RoundingMode.HALF_UP));
        order1.setTotal(new BigDecimal("205.49").setScale(2, RoundingMode.HALF_UP));
        order1.setDate(LocalDate.parse("11/30/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        Order resultOrder = dao.getOrderByOrderNumber("01/01/2020:10:12:26");

        assertEquals(order1, resultOrder);
    }

    @Test
    public void testRemoveOrder() throws Exception {
        // assert that there are 3 orders and remove 1 then assert there are 2 then remove 1 and assert that there is 0
        assertEquals(3, dao.getOrders().size());

        // existing order in Map
        Order order1 = new Order("01/01/2020:10:12:26");
        order1.setCustomerName("Luke Skywalker");
        order1.setState("PA");
        order1.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("50.00").setScale(2, RoundingMode.HALF_UP));
        order1.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order1.setMaterialCost(new BigDecimal("87.50").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCost(new BigDecimal("105.00").setScale(2, RoundingMode.HALF_UP));
        order1.setTax(new BigDecimal("12.99").setScale(2, RoundingMode.HALF_UP));
        order1.setTotal(new BigDecimal("205.49").setScale(2, RoundingMode.HALF_UP));
        order1.setDate(LocalDate.parse("11/30/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        // existing order in Map
        Order order2 = new Order("01/01/2020:12:51:59");
        order2.setCustomerName("Yoda");
        order2.setState("IN");
        order2.setTaxRate(new BigDecimal("6.00").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order2.setProductType("Carpet");
        order2.setArea(new BigDecimal("100.00").setScale(2, RoundingMode.HALF_UP));
        order2.setCostPerSquareFoot(new BigDecimal("2.25").setScale(2, RoundingMode.HALF_UP));
        order2.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order2.setMaterialCost(new BigDecimal("225.00").setScale(2, RoundingMode.HALF_UP));
        order2.setLaborCost(new BigDecimal("210.00").setScale(2, RoundingMode.HALF_UP));
        order2.setTax(new BigDecimal("26.10").setScale(2, RoundingMode.HALF_UP));
        order2.setTotal(new BigDecimal("461.10").setScale(2, RoundingMode.HALF_UP));
        order2.setDate(LocalDate.parse("02/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        // existing order in Map
        Order order3 = new Order("11/30/2019:12:31:03");
        order3.setCustomerName("Darth Vader");
        order3.setState("OH");
        order3.setTaxRate(new BigDecimal("6.25").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order3.setProductType("Laminate");
        order3.setArea(new BigDecimal("100.00").setScale(2, RoundingMode.HALF_UP));
        order3.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        order3.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order3.setMaterialCost(new BigDecimal("175.00").setScale(2, RoundingMode.HALF_UP));
        order3.setLaborCost(new BigDecimal("210.00").setScale(2, RoundingMode.HALF_UP));
        order3.setTax(new BigDecimal("24.06").setScale(2, RoundingMode.HALF_UP));
        order3.setTotal(new BigDecimal("409.06").setScale(2, RoundingMode.HALF_UP));
        order3.setDate(LocalDate.parse("11/30/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.removeOrder(order1);
        assertEquals(2, dao.getOrders().size());

        dao.removeOrder(order2);
        assertEquals(1, dao.getOrders().size());

        dao.removeOrder(order3);
        assertEquals(0, dao.getOrders().size());

        // assert that all removed orders result in null when calling get orders by order number method
        assertNull(dao.getOrderByOrderNumber(order1.getOrderNumber()));
        assertNull(dao.getOrderByOrderNumber(order2.getOrderNumber()));
        assertNull(dao.getOrderByOrderNumber(order3.getOrderNumber()));

    }

    @Test
    public void testEditOrder() throws Exception {
        // create new order and add to dao then create edited copy with same order number and call dao edit method and then assert that the edited order equals the order returned from dao get order by order number method and original order does not equal it
        Order order1 = new Order("01/01/2020:12:12:12"); // NEW ORDERNUMBER TO ADD SO IT IS NOT DUPLICATED IN TREEMAP (IT WOULD JUST REPLACE ANYWAYS)
        order1.setCustomerName("Jimmy John");
        order1.setState("PA");
        order1.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("50.00").setScale(2, RoundingMode.HALF_UP));
        order1.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        order1.setMaterialCost(new BigDecimal("87.50").setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCost(new BigDecimal("105.00").setScale(2, RoundingMode.HALF_UP));
        order1.setTax(new BigDecimal("12.99").setScale(2, RoundingMode.HALF_UP));
        order1.setTotal(new BigDecimal("205.49").setScale(2, RoundingMode.HALF_UP));
        order1.setDate(LocalDate.parse("12/10/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.addOrder(order1);

        Order orderEdited = new Order("01/01/2020:12:12:12");
        orderEdited.setCustomerName("Pizza Hut");
        orderEdited.setState("PA");
        orderEdited.setTaxRate(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        orderEdited.setProductType("Laminate");
        orderEdited.setArea(new BigDecimal("50.00").setScale(2, RoundingMode.HALF_UP));
        orderEdited.setCostPerSquareFoot(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP));
        orderEdited.setLaborCostPerSquareFoot(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP));
        orderEdited.setMaterialCost(new BigDecimal("87.50").setScale(2, RoundingMode.HALF_UP));
        orderEdited.setLaborCost(new BigDecimal("105.00").setScale(2, RoundingMode.HALF_UP));
        orderEdited.setTax(new BigDecimal("12.99").setScale(2, RoundingMode.HALF_UP));
        orderEdited.setTotal(new BigDecimal("205.49").setScale(2, RoundingMode.HALF_UP));
        orderEdited.setDate(LocalDate.parse("02/10/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        dao.editOrder(orderEdited);

        assertEquals(orderEdited, dao.getOrderByOrderNumber(orderEdited.getOrderNumber()));
        assertNotEquals(order1, dao.getOrderByOrderNumber(order1.getOrderNumber()));

    }

}
