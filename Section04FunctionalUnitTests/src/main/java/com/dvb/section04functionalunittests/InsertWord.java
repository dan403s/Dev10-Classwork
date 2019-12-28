/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.section04functionalunittests;

/**
 *
 * @author Daniel Bart
 */
public class InsertWord {

    public String insertWord(String container, String word) {
        String firstPartOfContainer = container.substring(0, container.length() / 2);
        String secondPartOfContainer = container.substring(container.length() / 2, container.length());

        return firstPartOfContainer + word + secondPartOfContainer;
    }

}
