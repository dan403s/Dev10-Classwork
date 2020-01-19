/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.jdbctemplatecomplexexample;

import com.dvb.jdbctemplatecomplexexample.Controller.MeetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Daniel Bart
 */
@SpringBootApplication
public class App implements CommandLineRunner {
    
    @Autowired
    MeetingController controller;

    public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        controller.run();
    }
    
}

