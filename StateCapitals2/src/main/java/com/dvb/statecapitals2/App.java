/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.statecapitals2;

/**
 *
 * @author Daniel Bart
 */
public class App {

    public static void main(String[] args) {

        // instantiate new StateCapitals2 object
        StateCapitals2 stateCapital2Instance = new StateCapitals2();

        // call methods
        stateCapital2Instance.loadHashMap();
        stateCapital2Instance.printStateAndCapitalObject();
        stateCapital2Instance.promptUser("Please enter the lower limit for capital city population: ");
        stateCapital2Instance.printCapitalsLowerLimit();
    }

}
