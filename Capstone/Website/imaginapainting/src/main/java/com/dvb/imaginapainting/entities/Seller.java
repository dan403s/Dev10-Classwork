/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Daniel Bart
 */
@Entity
@Data
public class Seller {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sellerId;
    
    @Size(max = 256, message = "Public name must be less than or equal to 256 characters.")
        private String publicName;
    
    @Size(max = 256, message = "Public company must be less than or equal to 256 characters.")
    private String publicCompany;
    
    @Size(max = 5000, message = "Public introduction must be less than or equal to 5000 characters.")
    private String publicIntroduction;
    
    @Size(max = 5000, message = "Photo URL must be less than or equal to 5000 characters.")
    private String photoUrl;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @OneToMany
    @JoinColumn(name = "seller_id", nullable = true)
    private List<Product> products;
    
    @ManyToMany
    @JsonIgnore 
    @JoinTable(name = "seller_order",
            joinColumns = {
                @JoinColumn(name = "seller_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "order_id")})
    private List<Order> orders;
    
}