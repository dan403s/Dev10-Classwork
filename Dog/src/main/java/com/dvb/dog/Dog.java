/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.dog;

/**
 *
 * @author Daniel Bart
 */
public class Dog {
    
    public static void main(String[] args) {
        Dog dogInstance1 = new Dog();
        dogInstance1.setName("Sam");
        String dogName = dogInstance1.getName();
        System.out.println(dogName);
        
        dogInstance1.setWeight(500.00);
        Double dogWeight = dogInstance1.getWeight();
        System.out.println(dogWeight);
        
        dogInstance1.bark();
        dogInstance1.sit();
    }
    
    private String name;
    private double weight;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void bark() {
        System.out.println("WOOF!");
    }
    
    public void sit() {
        System.out.println("Sitting...");
    }
    
}
