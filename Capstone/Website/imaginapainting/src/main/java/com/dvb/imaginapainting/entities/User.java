/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotBlank(message = "First name must not be empty.")
    @Size(max = 256, message = "First name must be less than or equal to 256 characters.")
    private String firstName;

    @NotBlank(message = "Last name must not be empty.")
    @Size(max = 256, message = "Last name must be less than or equal to 256 characters.")
    private String lastName;

    @Size(max = 256, message = "Street address must be less than or equal to 256 characters.")
    private String streetAddress;

    @Size(max = 256, message = "Apartment or unit must be less than or equal to 256 characters.")
    private String aptUnit;

    @Size(max = 256, message = "City  name must be less than or equal to 256 characters.")
    private String city;

    @Size(min = 5, max = 5, message = "Zip code name must be equal to 5 characters.")
    private String zip;

    @NotBlank(message = "Username must not be empty.")
    @Size(max = 256, message = "Username name must be less than or equal to 256 characters.")
    private String username;

    @NotBlank(message = "Password must not be empty.")
    @Size(max = 256, message = "Password must be less than or equal to 30 characters.")
    private String password;
    
    @NotNull(message = "Account status must not be empty.")
    private int acctStatus;
    
    @NotBlank(message = "User role must not be empty.")
    @Size(max = 256, message = "User role name must be less than or equal to 256 characters.")
    private String userRole;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = true)
    private State state;
    
}
