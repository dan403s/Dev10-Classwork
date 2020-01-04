/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.service;

import com.dvb.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Daniel Bart
 */
public class FlooringMasteryServiceLayerTest {

    // declare object
    private FlooringMasteryServiceLayer service;

    public FlooringMasteryServiceLayerTest() {
//        FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
//        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
//
//        service = new FlooringMasteryServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringMasteryServiceLayer.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testTrainingOrProd() throws Exception {
        // set mode to training
        String testMode = "training";
        service.trainingOrProd(testMode);
        // verify that mode is set to training
        String resultMode = service.returnTrainingOrProd();

        assertEquals(testMode, resultMode);
    }

    @Test
    public void testGetAllOrdersNoParam() throws Exception {
        // assert that getAllOrders() with no params returns with 1 Order
        assertEquals(1, service.getAllOrders().size());

        // create onlyOrder and getAllOrders() from DAO and assert equals
        Order onlyOrder;
        List<Order> orderList = new ArrayList<>();

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

        assertEquals(orderList, service.getAllOrders());
    }

    @Test
    public void testGetAllOrdersWithParam() throws Exception {
        // create LocalDate that matches DAO Order with same date and assertEquals 1
        LocalDate testLocalDate = LocalDate.parse("06/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        assertEquals(1, service.getAllOrders(testLocalDate).size());

        Order onlyOrder;
        List<Order> orderList = new ArrayList<>();

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

        assertEquals(orderList, service.getAllOrders(testLocalDate));
    }

    @Test
    public void testGetAllOrdersWithParamEmpty() throws Exception {
        // create LocalDate that does NOT match DAO Order and assertEquals 0
        LocalDate testLocalDate = LocalDate.parse("12/01/1920", DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        try {
            service.getAllOrders(testLocalDate);
            // if code makes it here, you fail
            fail("Expected NoOrdersExistException was not thrown.");
        } catch (NoOrdersExistException e) {
            // it would return without statement
            return;
        }
    }

    @Test
    public void testGetProductTypeSet() throws Exception {
        // assert that there are 2 products in the Set returned
        assertEquals(2, service.getProductTypeSet().size());
    }

    @Test
    public void testGetStateSet() throws Exception {
        // assert that there are 2 states in the Set returned
        assertEquals(2, service.getStateSet().size());
    }

    @Test
    public void testAddOrder() throws Exception {
        // create order that will pass validation and also assert equals for calculations
        Order onlyOrder;
        onlyOrder = new Order("01/01/2020:12:12:12");
        onlyOrder.setCustomerName("Luke Skywalker");
        onlyOrder.setState("PA");
        onlyOrder.setProductType("Laminate");
        onlyOrder.setArea(new BigDecimal("10.00").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setDate(LocalDate.parse("06/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        Order testCalculations = service.addOrder(onlyOrder);

        assertEquals(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP), testCalculations.getTaxRate());
        assertEquals(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP), testCalculations.getCostPerSquareFoot());
        assertEquals(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP), testCalculations.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("17.50").setScale(2, RoundingMode.HALF_UP), testCalculations.getMaterialCost().setScale(2, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("21.00").setScale(2, RoundingMode.HALF_UP), testCalculations.getLaborCost().setScale(2, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("2.60").setScale(2, RoundingMode.HALF_UP), testCalculations.getTax().setScale(2, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("41.10").setScale(2, RoundingMode.HALF_UP), testCalculations.getTotal().setScale(2, RoundingMode.HALF_UP));

    }

    @Test
    public void testAddOrderUnknownState() throws Exception {
        // create order that will fail validation
        Order onlyOrder;
        onlyOrder = new Order("01/01/2020:12:12:12");
        onlyOrder.setCustomerName("Luke Skywalker");
        onlyOrder.setState("NC");
        onlyOrder.setProductType("Laminate");
        onlyOrder.setArea(new BigDecimal("10.00").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setDate(LocalDate.parse("06/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        try {
            service.addOrder(onlyOrder);
            // if code makes it here, you fail
            fail("Expected UnknownStateException was not thrown.");
        } catch (UnknownStateException e) {
            // it would return without statement
            return;
        }
    }

    @Test
    public void testAddOrderUnknownProductType() throws Exception {
        // create order that will fail validation
        Order onlyOrder;
        onlyOrder = new Order("01/01/2020:12:12:12");
        onlyOrder.setCustomerName("Luke Skywalker");
        onlyOrder.setState("PA");
        onlyOrder.setProductType("FAIL");
        onlyOrder.setArea(new BigDecimal("10.00").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setDate(LocalDate.parse("06/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        try {
            service.addOrder(onlyOrder);
            // if code makes it here, you fail
            fail("Expected UnknownProductTypeException was not thrown.");
        } catch (UnknownProductTypeException e) {
            // it would return without statement
            return;
        }
    }

    @Test
    public void testValidateState() throws Exception {
        service.validateState("oh");
        return;
    }

    @Test
    public void testValidateStateUnknownState() throws Exception {
        try {
            service.validateState("nc");
            // if code makes it here, you fail
            fail("Expected UnknownStateException was not thrown.");
        } catch (UnknownStateException e) {
            // it would return without statement
            return;
        }
    }

    @Test
    public void testValidateProductType() throws Exception {
        service.validateProductType("cARPET");
        return;
    }

    @Test
    public void testValidateProductTypeUnknownProductType() throws Exception {
        try {
            service.validateProductType("tEST");
            // if code makes it here, you fail
            fail("Expected UnknownProductTypeException was not thrown.");
        } catch (UnknownProductTypeException e) {
            // it would return without statement
            return;
        }
    }

    @Test
    public void testGetOrderByOrderNumber() throws Exception {
        // get order by order number that is actual order number
        Order testOrder = service.getOrderByOrderNumber("01/01/2020:12:12:12");
        assertNotNull(testOrder);
    }

    @Test
    public void testGetOrderByOrderNumberNoOrderExists() throws Exception {
        try {
            Order testOrder = service.getOrderByOrderNumber("12/12/1212:12:12:12");
            // if code makes it here, you fail
            fail("Expected NoOrdersExistException was not thrown.");
        } catch (NoOrdersExistException e) {
            // it would return without statement
            return;
        }
    }

    @Test
    public void testRemoveOrder() throws Exception {
        // call remove method, if order number matches dao stub, return it
        Order onlyOrder;
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

        Order testOrder = service.removeOrder(onlyOrder);

        assertEquals(onlyOrder, testOrder);
        assertNotNull(testOrder);
    }

    @Test
    public void testRemoveOrderNotInDao() throws Exception {
        // call remove method, if order number matches dao stub, return it
        Order onlyOrder;
        onlyOrder = new Order("01/01/0101:01:01:01"); // NEW ORDERNUMBER TO ADD SO IT IS NOT DUPLICATED IN TREEMAP (IT WOULD JUST REPLACE ANYWAYS)
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

        Order testOrder = service.removeOrder(onlyOrder);

        assertNotEquals(onlyOrder, testOrder);
        assertNull(testOrder);

    }

    @Test
    public void testEditOrder() throws Exception {
        // edit order that will pass validation and also assert equals for calculations
        Order onlyOrder;
        onlyOrder = new Order("01/01/2020:12:12:12");
        onlyOrder.setCustomerName("Luke Skywalker");
        onlyOrder.setState("PA");
        onlyOrder.setProductType("Laminate");
        onlyOrder.setArea(new BigDecimal("10.00").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setDate(LocalDate.parse("06/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        Order testCalculations = service.editOrder(onlyOrder);

        assertEquals(new BigDecimal("6.75").divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP), testCalculations.getTaxRate());
        assertEquals(new BigDecimal("1.75").setScale(2, RoundingMode.HALF_UP), testCalculations.getCostPerSquareFoot());
        assertEquals(new BigDecimal("2.10").setScale(2, RoundingMode.HALF_UP), testCalculations.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("17.50").setScale(2, RoundingMode.HALF_UP), testCalculations.getMaterialCost().setScale(2, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("21.00").setScale(2, RoundingMode.HALF_UP), testCalculations.getLaborCost().setScale(2, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("2.60").setScale(2, RoundingMode.HALF_UP), testCalculations.getTax().setScale(2, RoundingMode.HALF_UP));
        assertEquals(new BigDecimal("41.10").setScale(2, RoundingMode.HALF_UP), testCalculations.getTotal().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testEditOrderUnknownState() throws Exception {
        // create order that will fail validation
        Order onlyOrder;
        onlyOrder = new Order("01/01/2020:12:12:12");
        onlyOrder.setCustomerName("Luke Skywalker");
        onlyOrder.setState("NC");
        onlyOrder.setProductType("Laminate");
        onlyOrder.setArea(new BigDecimal("10.00").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setDate(LocalDate.parse("06/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        try {
            service.editOrder(onlyOrder);
            // if code makes it here, you fail
            fail("Expected UnknownStateException was not thrown.");
        } catch (UnknownStateException e) {
            // it would return without statement
            return;
        }
    }

    @Test
    public void testEditOrderUnknownProductType() throws Exception {
        // create order that will fail validation
        Order onlyOrder;
        onlyOrder = new Order("01/01/2020:12:12:12");
        onlyOrder.setCustomerName("Luke Skywalker");
        onlyOrder.setState("PA");
        onlyOrder.setProductType("FAIL");
        onlyOrder.setArea(new BigDecimal("10.00").setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setDate(LocalDate.parse("06/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        try {
            service.editOrder(onlyOrder);
            // if code makes it here, you fail
            fail("Expected UnknownProductTypeException was not thrown.");
        } catch (UnknownProductTypeException e) {
            // it would return without statement
            return;
        }
    }

}
