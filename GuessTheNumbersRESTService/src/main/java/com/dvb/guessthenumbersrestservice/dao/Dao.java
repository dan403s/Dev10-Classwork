/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.dao;

import com.dvb.guessthenumbersrestservice.dto.Game;
import com.dvb.guessthenumbersrestservice.dto.Round;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface Dao {
    
    // method for "begin" option ---------------------------------------------------
    Game createGame(Game game);
    
    // method for "guess" option ---------------------------------------------------
    Round createRound(Round round, String gameId);
    
    boolean updateGame(Game game);
    
    // method for "game" option ----------------------------------------------------
    List<Game> getAllGames();
    
    // method for "game/{gameId}" --------------------------------------------------
    Game getGame(String gameId);
    
    // method for "rounds/{gameId}" ------------------------------------------------
    List<Round> getAllRounds(String gameId);
    
    // method for deleting games from DB for testing --------------------------------
    boolean deleteGame(String gameId);
    
}
