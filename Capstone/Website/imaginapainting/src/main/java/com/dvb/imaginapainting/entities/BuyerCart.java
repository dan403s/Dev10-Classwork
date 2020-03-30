/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.entities;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Daniel Bart
 */
@Entity
@Data
public class BuyerCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buyerCartId;    

    @NotNull(message = "Cart total must not be empty.")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "9999999999.99", inclusive = true)
    private BigDecimal total;

    @ManyToMany
    @JoinTable(name = "buyer_cart_product",
            joinColumns = {
                @JoinColumn(name = "buyer_cart_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "product_id")})
    private List<Product> products;

}
