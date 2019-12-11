/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.shapesandperimeters;

/**
 *
 * @author Daniel Bart
 */
public abstract class Shape {
    
    protected String color;
    
    public abstract double getArea();
    
    public abstract double getPerimeter();

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
