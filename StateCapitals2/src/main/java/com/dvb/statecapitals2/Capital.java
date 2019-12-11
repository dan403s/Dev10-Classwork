/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.statecapitals2;

/**
 *
 * @author Daniel Bart
 */
public class Capital {
    
    private String name;
    private Integer population;
    private Integer squareMileage;
    
    public Capital(String name, Integer population, Integer squareMileage) {
        this.name = name;
        this.population = population;
        this.squareMileage = squareMileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getSquareMileage() {
        return squareMileage;
    }

    public void setSquareMileage(Integer squareMileage) {
        this.squareMileage = squareMileage;
    }
    
    
    
}
