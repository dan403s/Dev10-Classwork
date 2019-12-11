/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.lesson5collectionsandmapsexamples;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Daniel Bart
 */
public class ArrayListExample3 {

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

        // print every String in our list with an iterator
        // ask for the iterator - we must ask for an iterator of Strings
        // What happens if we don't?
        Iterator<String> iter = stringList.iterator();

        // get String objects from the list while there are still Strings
        // remaining
        while (iter.hasNext()) {
            String current = iter.next();
            System.out.println(current);
        }
    }

}
