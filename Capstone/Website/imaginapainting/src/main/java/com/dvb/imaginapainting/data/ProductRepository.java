/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Bart
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    @Query(value = "SELECT p.* FROM product p INNER JOIN seller s ON p.seller_id = s.seller_id INNER JOIN user u ON s.user_id = u.user_id WHERE acct_status = 1 AND product_status = 1", nativeQuery = true)
    List<Product> findAllWhereSellerIsActiveAndProductIsActive();
    
}
