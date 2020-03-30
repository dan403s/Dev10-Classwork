/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.SpringBootSecurityDemo.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Daniel Bart
 */
public interface UserDetailsService {
    
    UserDetails loadUserByUsername(String username);
    
}
