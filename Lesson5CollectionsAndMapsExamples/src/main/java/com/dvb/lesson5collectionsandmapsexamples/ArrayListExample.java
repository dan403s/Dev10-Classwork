/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.lesson5collectionsandmapsexamples;

import java.util.ArrayList;

/**
 *
 * @author Daniel Bart
 */
public class ArrayListExample {

    public static void main(String[] args) {
        // create an ArrayList of String objects
        ArrayList<String> stringList = new ArrayList<>();

        // ask the list how big it is
        System.out.println("List size before adding any Strings: "
                + stringList.size());

        // add a String object to our list
        stringList.add("My First String");

        // ask the list how big it is
        System.out.println("List size after adding one String: "
                + stringList.size());

        // add another String object to our list
        stringList.add("My Second String");

        // ask the list how big it is
        System.out.println("List size after adding two Strings: "
                + stringList.size());
    }

}
