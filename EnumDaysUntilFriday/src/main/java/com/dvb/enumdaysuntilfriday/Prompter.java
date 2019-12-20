/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.enumdaysuntilfriday;

import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class Prompter {
    
    Scanner sc = new Scanner(System.in);
    
    public String readString(String prompt) {
        System.out.println(prompt);
        String response = sc.nextLine();
        return response;
    }
    
}
