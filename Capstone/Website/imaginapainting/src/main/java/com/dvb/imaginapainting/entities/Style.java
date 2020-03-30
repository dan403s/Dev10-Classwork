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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Daniel Bart
 */
@Entity
@Data
public class Style {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int styleId;
    
    @NotBlank(message = "Style name must not be empty.")
    @Size(max = 256, message = "Style name must be less than or equal to 256 characters.")
    private String name;
    
}
