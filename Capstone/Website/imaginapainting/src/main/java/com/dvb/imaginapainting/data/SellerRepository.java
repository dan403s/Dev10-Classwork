/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Bart
 */
@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    
    @Query(value = "SELECT s.* FROM seller s INNER JOIN product p ON s.seller_id = p.seller_id WHERE p.product_id = ?1", nativeQuery = true)
    Seller findByProduct(int productId);
    
    @Query(value = "SELECT s.* FROM seller s INNER JOIN `user` u ON s.user_id = u.user_id WHERE u.user_id = ?1", nativeQuery = true)
    Seller findByUserId(int userId);

}
