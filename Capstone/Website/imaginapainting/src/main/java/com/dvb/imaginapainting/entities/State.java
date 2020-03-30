/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.entities;

import javax.persistence.Entity;
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
public class State {

    @Id
    @NotBlank(message = "State ID must not be empty.")
    @Size(max = 2, message = "State ID must be less than or equal to 2 characters.")
    private String stateId;

    @NotBlank(message = "State name must not be empty.")
    @Size(max = 256, message = "State name must be less than or equal to 256 characters.")
    private String name;

}
