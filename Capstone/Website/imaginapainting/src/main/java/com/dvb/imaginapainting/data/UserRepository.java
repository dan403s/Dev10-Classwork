/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Bart
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Query(value = "SELECT * FROM `user` WHERE username = ?1 AND acct_status != 2 AND acct_status != 3", nativeQuery = true)
    User findByUsername(String username);
    
}
