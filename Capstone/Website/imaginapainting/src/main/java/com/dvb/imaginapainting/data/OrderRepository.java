/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Bart
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    
    @Query(value = "SELECT * FROM `order` o WHERE buyer_id = ?1", nativeQuery = true)
    List<Order> findByBuyerId(int buyerId);
    
    @Query(value = "SELECT o.* FROM `order` o INNER JOIN seller_order so ON o.order_id = so.order_id INNER JOIN seller s ON so.seller_id = s.seller_id WHERE s.seller_id = ?1", nativeQuery = true)
    List<Order> findBySellerId(int sellerId);
    
    @Modifying
    @Transactional 
    @Query(value = "INSERT INTO `order` (`date`, total, first_name, last_name, street_address, apt_unit, city, zip, state_id, buyer_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
    void addOrder(LocalDate date, BigDecimal total, String firstName, String lastName, String streetAddress, String aptUnit, String city, String zip, String stateId, int buyerId);
    
    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    int getLastInsertId();
    
    @Modifying
    @Transactional 
    @Query(value = "INSERT INTO order_product (order_id, product_id) VALUES (?1, ?2)", nativeQuery = true)
    void addOrderProduct(int orderId, int productId);
    
    @Modifying
    @Transactional 
    @Query(value = "INSERT INTO seller_order (seller_id, order_id) VALUE (?1, ?2)", nativeQuery = true)
    void addSellerOrder(int sellerId, int orderId);
    
}
