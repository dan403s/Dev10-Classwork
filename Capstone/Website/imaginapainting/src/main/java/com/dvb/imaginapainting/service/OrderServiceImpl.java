/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.data.OrderRepository;
import com.dvb.imaginapainting.entities.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order findById(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(int orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public boolean existsById(int orderId) {
        return orderRepository.existsById(orderId);
    }

    @Override
    public List<Order> findByBuyerId(int buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    @Override
    public List<Order> findBySellerId(int sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    @Transactional
    public int addOrder(LocalDate date, BigDecimal total, String firstName, String lastName, String streetAddress, String aptUnit, String city, String zip, String stateId, int buyerId) {
        orderRepository.addOrder(date, total, firstName, lastName, streetAddress, aptUnit, city, zip, stateId, buyerId);
        return orderRepository.getLastInsertId();
    }

    @Override
    public void addOrderProduct(int orderId, int productId) {
        orderRepository.addOrderProduct(orderId, productId);
    }

    @Override
    public void addSellerOrder(int sellerId, int orderId) {
        orderRepository.addSellerOrder(sellerId, orderId);
    }

}
