/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.service;

import com.dvb.guessthenumbersrestservice.dto.Game;
import com.dvb.guessthenumbersrestservice.dto.Guess;
import com.dvb.guessthenumbersrestservice.dto.Round;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface Service {
    
    // method for "begin" option ---------------------------------------------------
    Game beginGame();
    
    // method for "guess" option ---------------------------------------------------
    Round guessAnswer(Guess guess) throws GameAlreadyCompletedException, InputValidationException, InvalidIdException;
    
    // method for "game" option ----------------------------------------------------
    List<Game> getAllGames() throws NoGamesExistException;
    
    // method for "game/{gameId}" --------------------------------------------------
    Game getGame(String gameId) throws InvalidIdException;
    
    // method for "rounds/{gameId}" ------------------------------------------------
    List<Round> getAllRounds(String gameId) throws NoRoundsForGameIdException, InvalidIdException;
    
}
