/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.lesson5collectionsandmapsexamples;

import java.util.HashMap;

/**
 *
 * @author Daniel Bart
 */
public class HashMapExample3 {

    public static void main(String[] args) {
        // create a map that maps a country to its population
        HashMap<String, Integer> populations = new HashMap<>();

        // add the first country
        populations.put("USA", 313000000);

        // add the next country
        populations.put("Canada", 34000000);

        // add another country
        populations.put("United Kingdom", 63000000);

        // add another country
        populations.put("Japan", 127000000);

        // ask the map for its size - it will be 4
        System.out.println("Map size is: " + populations.size());

        // get the poplation of Japan and print it to the screen
        Integer japanPopulation = populations.get("Japan");
        System.out.println("The population of Japan is: "
                + japanPopulation);
    }

}
