/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.statecapitals2;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class StateCapitals2 {

    // instantiate new Random object
    Random randomizer = new Random();

    // instantiate new Scanner object
    Scanner sc = new Scanner(System.in);

    Integer userResponseInteger;

    // create HashMap to store Capital objects
    HashMap<String, Capital> stateCapitals = new HashMap<>();

    // multi-dimensional state, capital array to loop through and load into HashMap
    String[][] data = {
        {"Alabama", "Montgomery"}, {"Alaska", "Juneau"}, {"Arizona", "Phoenix"},
        {"Arkansas", "Little Rock"}, {"California", "Sacramento"},
        {"Colorado", "Denver"}, {"Connecticut", "Hartford"},
        {"Delaware", "Dover"}, {"Florida", "Tallahassee"},
        {"Georgia", "Atlanta"}, {"Hawaii", "Honolulu"}, {"Idaho", "Boise"},
        {"Illinois", "Springfield"}, {"Indiana", "Indianapolis"},
        {"Iowa Des", "Moines"}, {"Kansas", "Topeka"}, {"Kentucky", "Frankfort"},
        {"Louisiana", "Baton Rouge"}, {"Maine", "Augusta"},
        {"Maryland", "Annapolis"}, {"Massachusetts", "Boston"},
        {"Michigan", "Lansing"}, {"Minnesota", "Saint Paul"},
        {"Mississippi", "Jackson"}, {"Missouri", "Jefferson City"},
        {"Montana", "Helena"}, {"Nebraska", "Lincoln"},
        {"Nevada", "Carson City"}, {"New Hampshire", "Concord"},
        {"New Jersey", "Trenton"}, {"New Mexico", "Santa Fe"},
        {"New York", "Albany"}, {"North Carolina", "Raleigh"},
        {"North Dakota", "Bismarck"}, {"Ohio", "Columbus"},
        {"Oklahoma", "Oklahoma City"}, {"Oregon", "Salem"},
        {"Pennsylvania", "Harrisburg"}, {"Rhode Island", "Providence"},
        {"South Carolina", "Columbia"}, {"South Dakota", "Pierre"},
        {"Tennessee", "Nashville"}, {"Texas", "Austin"},
        {"Utah", "Salt Lake City"}, {"Vermont", "Montpelier"},
        {"Virginia", "Richmond"}, {"Washington", "Olympia"},
        {"West Virginia", "Charleston"}, {"Wisconsin", "Madison"},
        {"Wyoming", "Cheyenne"}};

    // create Set of keys
    Set<String> keys;

    // load HashMap with key, value pairs
    public void loadHashMap() {
        // load from array with state, capital, other values are default
        for (int i = 0; i < data.length; i++) {
            stateCapitals.put(data[i][0], new Capital(data[i][1], 0, 0));
        }

        // load Set with keys
        keys = stateCapitals.keySet();

        // randomly assign values to population and squareMileage in each object because I'm very lazy to manually enter this stuff and lookup the correct values
        for (String key : keys) {
            stateCapitals.get(key).setPopulation((randomizer.nextInt(1000001 - 100000) + 100000));
            stateCapitals.get(key).setSquareMileage((randomizer.nextInt(1001 - 100) + 100));
        }

    }

    // print to console
    public void printStateAndCapitalObject() {
        System.out.println("STATE/CAPITAL PAIRS:");
        System.out.println("====================");

        // loop through key Set and print to console
        for (String key : keys) {
            System.out.println(key + " - " + stateCapitals.get(key).getName() + " | Pop: " + stateCapitals.get(key).getPopulation() + " | Area: " + stateCapitals.get(key).getSquareMileage() + " sq mi");
        }
        System.out.println();
        System.out.println();
    }

    // prompt user for lower limit for capital city population
    public void promptUser(String prompt) {
        System.out.println(prompt);
        String userResponseString = sc.nextLine();
        userResponseInteger = Integer.parseInt(userResponseString);
    }

    // print to console listing capitals with populations greater than user input
    public void printCapitalsLowerLimit() {
        System.out.println("\n\nLISTING CAPITALS WITH POPULATIONS GREATER THAN " + userResponseInteger);
        System.out.println();

        // loop through key Set and print to console
        for (String key : keys) {
            if (stateCapitals.get(key).getPopulation() > userResponseInteger) {
                System.out.println(key + " - " + stateCapitals.get(key).getName() + " | Pop: " + stateCapitals.get(key).getPopulation() + " | Area: " + stateCapitals.get(key).getSquareMileage() + " sq mi");

            }
        }
    }

}
