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
public class ArrayListExample2 {

    public static void main(String[] args) {
        // create an ArrayList of String objects
        ArrayList<String> stringList = new ArrayList<>();

        // add a String object to our list
        stringList.add("My First String");

        // add another String object to our list
        stringList.add("My Second String");

        // add another String object to our list
        stringList.add("My Third String");

        // add another String object to our list
        stringList.add("My Fourth String");

        // ask the list how big it is
        System.out.println("List size: " + stringList.size());

        // print every String in our list with an enhanced for loop
        for (String s : stringList) {
            System.out.println(s);
        }
        
        for(String s : stringList){
            System.out.println(s);
        }
        
        // use get(int index) to get each element
        for(int i = 0; i < stringList.size(); i++) {
            System.out.println(stringList.get(i));
        }
    }

}
