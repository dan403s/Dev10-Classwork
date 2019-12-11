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
public class ArrayListExample1 {

    public static void main(String[] args) {
        // create an ArrayList of String objects
        ArrayList<String> stringList = new ArrayList<>();

        // add a String object to our list
        stringList.add("My First String");

        // add another String object to our list
        stringList.add("My Second String");

        // ask the list how big it is
        System.out.println("List size after adding two Strings: "
                + stringList.size());

        // remove the second String object from our list - remember that
        // our indexes start counting at 0 instead of 1
        stringList.remove(1);

        // ask the list how big it is
        System.out.println("List size after removing one String: "
                + stringList.size());

        // remove the remaining String object from our list - remember
        // that the list resizes automatically so if there is only one
        // element in a list it is always at index 0
        stringList.remove(0);

        // ask the list how big it is
        System.out.println("List size after removing last String: "
                + stringList.size());

        // what happens if you try to remove another element? Give it a
        // try...
    }

}
