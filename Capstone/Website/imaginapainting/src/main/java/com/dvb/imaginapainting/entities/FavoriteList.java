/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;

/**
 *
 * @author Daniel Bart
 */
@Entity
@Data
public class FavoriteList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favoriteListId;    

    @ManyToMany
    @JoinTable(name = "favorite_list_product",
            joinColumns = {
                @JoinColumn(name = "favorite_list_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "product_id")})
    private List<Product> products;
    
}
