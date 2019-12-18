/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3libraryrefactored;

import com.dvb.mp3libraryrefactored.controller.MP3LibraryController;
import com.dvb.mp3libraryrefactored.dao.MP3LibraryDao;
import com.dvb.mp3libraryrefactored.dao.MP3LibraryDaoFileImpl;
import com.dvb.mp3libraryrefactored.ui.MP3LibraryView;
import com.dvb.mp3libraryrefactored.ui.UserIO;
import com.dvb.mp3libraryrefactored.ui.UserIOConsoleImpl;

/**
 *
 * @author Daniel Bart
 */
public class App {

    public static void main(String[] args) {

        // instantiate new objects to inject dependency
        UserIO myIo = new UserIOConsoleImpl();
        MP3LibraryView myView = new MP3LibraryView(myIo);
        MP3LibraryDao myDao = new MP3LibraryDaoFileImpl();
        MP3LibraryController controller = new MP3LibraryController(myView, myDao);

        // call methods
        controller.run();

    }

}
