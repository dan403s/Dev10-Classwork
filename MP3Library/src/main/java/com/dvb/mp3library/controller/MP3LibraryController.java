/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.mp3library.controller;

import com.dvb.mp3library.dao.MP3LibraryDao;
import com.dvb.mp3library.dao.MP3LibraryDaoException;
import com.dvb.mp3library.dto.MP3;
import com.dvb.mp3library.ui.MP3LibraryView;
import com.dvb.mp3library.ui.UnsupportedReleaseDateFormatException;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public class MP3LibraryController {

    // declare MP3LibraryView object
    MP3LibraryView view;

    // declare MP3LibraryDaoFileImpl object
    MP3LibraryDao dao;

    // constructor for dependency injection
    public MP3LibraryController(MP3LibraryView view, MP3LibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    // method that starts everything
    public void run() {

        // declare variables and initialize if necessary
        boolean keepGoing = true;
        int menuSelection;

        // loop through menu choices as long as keepGoing is true
        while (keepGoing) {

            // try-catch for error handling (print out error message if caught)
            try {

                // store user selection in menuSelection as int
                menuSelection = getMenuSelection();

                // switch statement to determine what to do based upon menuSelection variable
                switch (menuSelection) {
                    case 1:
                        createMp3();
                        break;
                    case 2:
                        removeMp3();
                        break;
                    case 3:
                        editMp3();
                        break;
                    case 4:
                        showAllMp3s();
                        break;
                    case 5:
                        showMp3Details();
                        break;
                    case 6:
                        searchLibrary();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

                exitMessage();

            } catch (MP3LibraryDaoException | UnsupportedReleaseDateFormatException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }

    }

    // INITIAL MENU ----------------------------------------------------------------
    // coordinate menu selection
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    // ADD MP3 ---------------------------------------------------------------------
    // coordinate add MP3
    private void createMp3() throws MP3LibraryDaoException, UnsupportedReleaseDateFormatException {
        view.displayAddMp3Banner();
        MP3 createdMp3 = view.getNewMp3Info();
        dao.addMp3(createdMp3.getTitle(), createdMp3);
        view.displayAddMp3SuccessBanner();
    }

    // REMOVE MP3 ------------------------------------------------------------------
    // coordinate remove MP3
    private void removeMp3() throws MP3LibraryDaoException {
        view.displayRemoveMp3Banner();
        String removeTitle = view.getTitleForDeleteMp3();
        MP3 removeMp3 = dao.listMp3Details(removeTitle);
        view.displayMp3Details(removeMp3);
        if (removeMp3 != null) {
            String deleteConfirm = view.getRemoveMp3Verification();
            if (deleteConfirm.equalsIgnoreCase("y")) {
                dao.deleteMp3(removeTitle);
                view.displayRemoveMp3SuccessBanner();
            } else {
                view.displayCancelRemoveMp3Banner();
            }
        } else {
            view.displayContinueToMenuBanner();
        }
    }

    // EDIT MP3 --------------------------------------------------------------------
    // coordinate edit MP3
    private void editMp3() throws MP3LibraryDaoException, UnsupportedReleaseDateFormatException {
        view.displayEditMp3Banner();
        String editTitle = view.getTitleForEditMp3();
        MP3 editMp3 = dao.listMp3Details(editTitle);
        view.displayMp3Details(editMp3);
        if (editMp3 != null) {
            editMp3 = view.getEditedMp3Info(editTitle);
            dao.changeMp3(editTitle, editMp3);
            view.displayEditSuccessBanner();
        } else {
            view.displayContinueToMenuBanner();
        }
    }

    // LIST ALL MP3s AND DETAILS ---------------------------------------------------
    // coordinate list all MP3s and details
    private void showAllMp3s() throws MP3LibraryDaoException {
        view.displayAllMp3sBanner();
        List<MP3> mp3List = dao.listAllMp3s();
        view.displayMp3List(mp3List);
        view.displayContinueToMenuBanner();
    }

    // LIST MP3 DETAILS FOR SINGLE MP3 ---------------------------------------------
    // coordinate list MP3 details
    private void showMp3Details() throws MP3LibraryDaoException {
        view.displayMp3DetailsBanner();
        String titleDetails = view.getTitleForMp3Details();
        MP3 mp3Details = dao.listMp3Details(titleDetails);
        view.displayMp3Details(mp3Details);
        view.displayContinueToMenuBanner();
    }

    // SEARCH FOR MP3 TITLE (PARTIAL TITLE OK) -------------------------------------
    // coordinate search for MP3
    private void searchLibrary() throws MP3LibraryDaoException {
        view.displayMp3SearchBanner();
        String searchTerm = view.getSearchTermForMp3Details();
        List<MP3> foundMp3s = dao.searchLibrary(searchTerm);
        view.displayMp3List(foundMp3s);
        view.displayContinueToMenuBanner();
    }

    // EXIT ------------------------------------------------------------------------
    // coordinate exit
    private void exitMessage() {
        view.displayExitBanner();
    }

    // UNKNOWN COMMAND -------------------------------------------------------------
    // coordinate unknown command
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

}
