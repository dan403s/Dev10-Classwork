/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface OrderService {

    // CRUD for Order objects
    /**
     *
     * @param orderId
     * @return 
     */
    Order findById(int orderId);

    /**
     * Find Order by orderId
     * @return 
     */
    List<Order> findAll();

    /**
     * Find all Order objects
     * @param order
     * @return 
     */
    Order save(Order order);

    /**
     * Save Order
     * @param orderId
     */
    void deleteById(int orderId);

    /**
     * Delete Order by orderId
     * @return 
     */
    long count();

    /**
     * Check if Order exists by orderId
     * @param orderId
     * @return 
     */
    boolean existsById(int orderId);

    /**
     * Find Order objects by buyerId
     * @param buyerId
     * @return 
     */
    List<Order> findByBuyerId(int buyerId);

    /**
     * Find order objects by sellerId
     * @param sellerId
     * @return 
     */
    List<Order> findBySellerId(int sellerId);

    /**
     * Add order
     * @param date
     * @param total
     * @param firstName
     * @param lastName
     * @param streetAddress
     * @param stateId
     * @param city
     * @param aptUnit
     * @param zip
     * @param buyerId
     * @return 
     */
    int addOrder(LocalDate date, BigDecimal total, String firstName, String lastName, String streetAddress, String aptUnit, String city, String zip, String stateId, int buyerId);

    /**
     * Add orderId and productId to order_product table
     * @param orderId
     * @param productId
     */
    void addOrderProduct(int orderId, int productId);

    /**
     * Add sellerId and orderId to seller_order table
     * @param sellerId
     * @param orderId
     */
    void addSellerOrder(int sellerId, int orderId);

}
