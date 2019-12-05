/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.fieldday;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class FieldDay {
    
    public static void main(String[] args) {
        String lastName, teamName = "";
        
        Scanner sc = new Scanner(System.in);
        System.out.println("What's your last name? ");
        lastName = sc.nextLine();
        
        if(lastName.compareTo("Baggins") <= 0) {
            teamName = "Red Dragons";
        } else if(lastName.compareTo("Baggins") > 0 && lastName.compareTo("Dresden") <= 0) {
            teamName = "Dark Wizards";
        } else if(lastName.compareTo("Dresden") > 0 && lastName.compareTo("Howl") <= 0) {
            teamName = "Moving Castles";
        } else if(lastName.compareTo("Howl") > 0 && lastName.compareTo("Potter") <= 0) {
            teamName = "Golden Snitches";
        } else if(lastName.compareTo("Potter") > 0 && lastName.compareTo("Vimes") <= 0) {
            teamName = "Night Guards";
        } else if(lastName.compareTo("Vimes") > 0) {
            teamName = "Black Holes";
        }
        
        System.out.println("Aha! You're on the team " + "\"" + teamName + "\"!\n Good luck in the games!\n");
    }
    
}
