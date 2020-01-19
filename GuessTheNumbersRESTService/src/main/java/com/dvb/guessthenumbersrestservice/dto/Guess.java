/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.dto;

import java.util.Objects;

/**
 *
 * @author Daniel Bart
 */
public class Guess {
    
    private String gameId;
    private String roundGuess;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getRoundGuess() {
        return roundGuess;
    }

    public void setRoundGuess(String roundGuess) {
        this.roundGuess = roundGuess;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.gameId);
        hash = 67 * hash + Objects.hashCode(this.roundGuess);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Guess other = (Guess) obj;
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        if (!Objects.equals(this.roundGuess, other.roundGuess)) {
            return false;
        }
        return true;
    }
    
    
    
}
