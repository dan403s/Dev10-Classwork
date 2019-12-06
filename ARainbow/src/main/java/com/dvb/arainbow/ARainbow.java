/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.arainbow;

/**
 *
 * @author Daniel Bart
 */
public class ARainbow {

    public static void main(String[] args) {
        ARainbow rain = new ARainbow();
        
        String[] colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};

        System.out.println(colors[0]);
        System.out.println(colors[1]);
        System.out.println(colors[2]);
        System.out.println(colors[3]);
        System.out.println(colors[4]);
        System.out.println(colors[5]);
        System.out.println(colors[6]);
        
        System.out.println(rain.returnColors(colors));
    }
    
    public String returnColors(String[] array) {
        String temp = "";
        for(String item : array) {
            temp += ("Color: " + item + "\n");
        }
        return temp;
    }

}
