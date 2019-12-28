/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3library;

import com.dvb.mp3library.controller.MP3LibraryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Daniel Bart
 */
public class App {

    public static void main(String[] args) {

        // instantiate new objects to inject dependency
//        UserIO myIo = new UserIOConsoleImpl();
//        MP3LibraryView myView = new MP3LibraryView(myIo);
//        MP3LibraryDao myDao = new MP3LibraryDaoFileImpl();
//        MP3LibraryController controller = new MP3LibraryController(myView, myDao);
        // call methods
//        controller.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        MP3LibraryController controller = ctx.getBean("controller", MP3LibraryController.class);
        controller.run();
    }

}
