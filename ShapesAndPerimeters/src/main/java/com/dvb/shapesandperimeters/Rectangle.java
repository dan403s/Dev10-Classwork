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
public class Rectangle extends Shape {
    
    private double area;
    private double perimeter;
    private double sideLength;
    private double sideWidth;
    
    @Override
    public double getArea() {
        area = sideLength * sideWidth;
        return area;
    }
    
    @Override
    public double getPerimeter() {
        perimeter = sideLength * 2 + sideWidth * 2;
        return perimeter;
    }
    
    public Rectangle(double sideLength, double sideWidth, String color) {
        super(color);
        this.sideLength = sideLength;
        this.sideWidth = sideWidth;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideWidth() {
        return sideWidth;
    }

    public void setSideWidth(double sideWidth) {
        this.sideWidth = sideWidth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
