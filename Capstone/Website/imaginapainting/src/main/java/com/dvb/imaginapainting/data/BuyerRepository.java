/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Bart
 */
@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    
    @Query(value = "SELECT b.* FROM buyer b INNER JOIN `user` u ON b.user_id = u.user_id WHERE u.user_id = ?1", nativeQuery = true)
    Buyer findByUserId(int userId);
    
}
