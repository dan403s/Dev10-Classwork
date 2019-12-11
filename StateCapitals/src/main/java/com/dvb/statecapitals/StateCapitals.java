/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.statecapitals;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class StateCapitals {

    // create hashmap that maps state to its state capital
    HashMap<String, String> stateCapitals = new HashMap<>();

    // take existing multi-dimensional array and loop through to load HashMap
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

    // new Set to store keys
    Set<String> stateNameKeys;

    // new Collection to store values
    Collection<String> stateCapitalNameValues;

    // load the HashMap with state/capital pair
    public void loadHashMap() {
        for (int i = 0; i < data.length; i++) {
            stateCapitals.put(data[i][0], data[i][1]);
        }
    }

    // create new Set to store keys
    public void loadSet() {
        stateNameKeys = stateCapitals.keySet();
    }

    // create new Collection to store values
    public void loadCollection() {
        stateCapitalNameValues = stateCapitals.values();
    }

    // print all state names to screen
    public void printStateNames() {
        System.out.println("STATES:");
        System.out.println("=======");

        for (String state : stateNameKeys) {
            System.out.println(state);
        }

        System.out.println();
    }

    // print all capital names to screen
    public void printCapitalNames() {
        System.out.println("CAPITALS:");
        System.out.println("========");

        for (String capital : stateCapitalNameValues) {
            System.out.println(capital);
        }

        System.out.println();
    }

    // print all state names and capital names to the screen
    public void printStateAndCapitalNames() {
        System.out.println("STATE/CAPITAL PAIRS:");
        System.out.println("======================");

        for (String state : stateNameKeys) {
            System.out.println(state + " - " + stateCapitals.get(state));
        }
    }
}
