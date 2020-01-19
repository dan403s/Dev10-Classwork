/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.controller;

import com.dvb.guessthenumbersrestservice.dto.Game;
import com.dvb.guessthenumbersrestservice.dto.Guess;
import com.dvb.guessthenumbersrestservice.dto.Round;
import com.dvb.guessthenumbersrestservice.service.GameAlreadyCompletedException;
import com.dvb.guessthenumbersrestservice.service.InputValidationException;
import com.dvb.guessthenumbersrestservice.service.InvalidIdException;
import com.dvb.guessthenumbersrestservice.service.NoGamesExistException;
import com.dvb.guessthenumbersrestservice.service.NoRoundsForGameIdException;
import com.dvb.guessthenumbersrestservice.service.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniel Bart
 */
@RestController
@RequestMapping("/api/guessgame")
public class Controller {

    @Autowired
    private Service service;

    // method for "begin" option ---------------------------------------------------
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game beginGame() {
        return service.beginGame();
    }

    // method for "guess" option ---------------------------------------------------
    @PostMapping("/guess")
    public Round guessAnswer(@RequestBody Guess guess) throws GameAlreadyCompletedException, InputValidationException, InvalidIdException {
        return service.guessAnswer(guess);
    }

    // method for "game" option ----------------------------------------------------
    @GetMapping("/game")
    public List<Game> getAllGames() throws NoGamesExistException {
        return service.getAllGames();
    }

    // method for "game/{gameId}" --------------------------------------------------
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable String gameId) throws InvalidIdException {
        Game result = service.getGame(gameId);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(result);
    }

    // method for "rounds/{gameId}" ------------------------------------------------
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> getAllRounds(@PathVariable String gameId) throws NoRoundsForGameIdException, InvalidIdException {
        List<Round> result = service.getAllRounds(gameId);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

}
