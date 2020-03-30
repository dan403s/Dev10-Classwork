    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    
    @NotBlank(message = "Title must not be empty.")
    @Size(max = 256, message = "Title must be less than or equal to 256 characters.")
    private String title;
    
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 5000, message = "Description must be less than or equal to 5000 characters.")
    private String description;
    
    @NotNull(message = "Price must not be empty.")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "99999999.99", inclusive = true)
    private BigDecimal price;
    
    @NotNull(message = "Quantity must not be empty.")
    @Min(value = 0, message = "Quantity must be greater than 0.")
    @Max(value = 99999, message = "Quantity must be less than 99,999.")
    private int quantity;
    
    @NotBlank(message = "Photo URL must not be empty.")
    @Size(max = 5000, message = "Photo URL must be less than or equal to 5000 characters.")
    private String photoUrl;
    
    @NotNull(message = "Product status must not be empty.")
    private int productStatus;
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "style_id", nullable = true)
    private Style style;
    
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = true)
    private Subject subject;
    
    @ManyToOne
    @JoinColumn(name = "medium_id", nullable = true)
    private Medium medium;
    
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = true)
    private Material material;
    
}
