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
public class Square extends Shape {
    
    private double area;
    private double perimeter;
    private double sideLength;
    
    @Override
    public double getArea() {
        area = sideLength * sideLength;
        return area;
    }
    
    @Override
    public double getPerimeter() {
        perimeter = sideLength * 4;
        return perimeter;
    }

    public Square(double sideLength, String color) {
        super(color);
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }
    
}
