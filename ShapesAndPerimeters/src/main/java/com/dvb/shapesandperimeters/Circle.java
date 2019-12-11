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
public class Circle extends Shape {
    
    private double area;
    private double perimeter;
    private double radius;
    private final double PI = 3.14;
    
    @Override
    public double getArea() {
        area = PI * Math.pow(radius, 2);
        return area;
    }
    
    @Override
    public double getPerimeter() {
        perimeter = 2 * PI * radius;
        return perimeter;
    }

    public Circle(double radius, String color) {
        super(color);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
    
}
