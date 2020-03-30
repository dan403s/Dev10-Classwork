/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Daniel Bart
 */
@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "`date`")
    @NotNull(message = "Order date must not be empty.")
    private LocalDate date;

    @NotNull(message = "Order total must not be empty.")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "9999999999.99", inclusive = true)
    private BigDecimal total;

    @NotBlank(message = "First name must not be empty.")
    @Size(max = 256, message = "First name must be less than or equal to 256 characters.")
    private String firstName;

    @NotBlank(message = "Last name must not be empty.")
    @Size(max = 256, message = "Last name must be less than or equal to 256 characters.")
    private String lastName;

    @NotBlank(message = "Street address must not be empty.")
    @Size(max = 256, message = "Street address must be less than or equal to 256 characters.")
    private String streetAddress;

    @NotBlank(message = "Apartment or unit must not be empty.")
    @Size(max = 256, message = "Apartment or unit must be less than or equal to 256 characters.")
    private String aptUnit;

    @NotBlank(message = "City must not be empty.")
    @Size(max = 256, message = "City  name must be less than or equal to 256 characters.")
    private String city;

    @NotBlank(message = "Zip code must not be empty.")
    @Size(min = 5, max = 5, message = "Zip code name must be equal to 5 characters.")
    private String zip;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = true)
    @JsonIgnore 
    private Buyer buyer;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = {
                @JoinColumn(name = "order_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "product_id")})
    private List<Product> products;

    @ManyToMany(mappedBy = "orders")
    @JsonIgnore 
    private List<Seller> sellers;

}
