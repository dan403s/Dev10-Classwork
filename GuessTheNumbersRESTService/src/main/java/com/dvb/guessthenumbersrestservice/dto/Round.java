/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Daniel Bart
 */
public class Round {

    private String roundId;
    private String roundGuess;
    private LocalDateTime roundDateTime;
    private String roundResult;
    private String roundResultSummary;

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    public String getRoundGuess() {
        return roundGuess;
    }

    public void setRoundGuess(String roundGuess) {
        this.roundGuess = roundGuess;
    }

    public LocalDateTime getRoundDateTime() {
        return roundDateTime;
    }

    public void setRoundDateTime(LocalDateTime roundDateTime) {
        this.roundDateTime = roundDateTime;
    }

    public String getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(String roundResult) {
        this.roundResult = roundResult;
    }

    public String getRoundResultSummary() {
        return roundResultSummary;
    }

    public void setRoundResultSummary(String roundResultSummary) {
        this.roundResultSummary = roundResultSummary;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.roundId);
        hash = 37 * hash + Objects.hashCode(this.roundGuess);
        hash = 37 * hash + Objects.hashCode(this.roundDateTime);
        hash = 37 * hash + Objects.hashCode(this.roundResult);
        hash = 37 * hash + Objects.hashCode(this.roundResultSummary);
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
        final Round other = (Round) obj;
        if (!Objects.equals(this.roundId, other.roundId)) {
            return false;
        }
        if (!Objects.equals(this.roundGuess, other.roundGuess)) {
            return false;
        }
        if (!Objects.equals(this.roundResult, other.roundResult)) {
            return false;
        }
        if (!Objects.equals(this.roundResultSummary, other.roundResultSummary)) {
            return false;
        }
        if (!Objects.equals(this.roundDateTime, other.roundDateTime)) {
            return false;
        }
        return true;
    }

}
