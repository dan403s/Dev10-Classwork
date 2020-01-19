/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Daniel Bart
 */
public class Game {

    private String gameId;
    private String gameAnswer;
    private boolean gameIsFinished;
    private List<Round> gameRounds = new ArrayList<>();

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameAnswer() {
        return gameAnswer;
    }

    public void setGameAnswer(String gameAnswer) {
        this.gameAnswer = gameAnswer;
    }

    public boolean isGameIsFinished() {
        return gameIsFinished;
    }

    public void setGameIsFinished(boolean gameIsFinished) {
        this.gameIsFinished = gameIsFinished;
    }

    // @JsonIgnore
    public List<Round> getGameRounds() {
        return gameRounds;
    }

    public void setGameRounds(List<Round> gameRounds) {
        this.gameRounds = gameRounds;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.gameId);
        hash = 37 * hash + Objects.hashCode(this.gameAnswer);
        hash = 37 * hash + (this.gameIsFinished ? 1 : 0);
        hash = 37 * hash + Objects.hashCode(this.gameRounds);
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
        final Game other = (Game) obj;
        if (this.gameIsFinished != other.gameIsFinished) {
            return false;
        }
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        if (!Objects.equals(this.gameAnswer, other.gameAnswer)) {
            return false;
        }
        if (!Objects.equals(this.gameRounds, other.gameRounds)) {
            return false;
        }
        return true;
    }

}
