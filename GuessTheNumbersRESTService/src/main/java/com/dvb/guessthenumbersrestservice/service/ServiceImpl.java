/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.service;

import com.dvb.guessthenumbersrestservice.dao.Dao;
import com.dvb.guessthenumbersrestservice.dto.Game;
import com.dvb.guessthenumbersrestservice.dto.Guess;
import com.dvb.guessthenumbersrestservice.dto.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Daniel Bart
 */
@Component
public class ServiceImpl implements Service {

    @Autowired
    private Dao dao;

    // method for "begin" option ---------------------------------------------------
    @Override
    public Game beginGame() {
        Game game = new Game();
        game.setGameAnswer(generateRandomNumber());
        game.setGameIsFinished(false);
        // call dao createGame method passing in Game object, the dao will set the gameId
        dao.createGame(game);
        // call hideOrShowAnswer which will keep or remove answer
        hideOrShowAnswer(game);
        // return Game object with or without answer
        return game;
    }

    // method for "guess" option ---------------------------------------------------
    @Override
    public Round guessAnswer(Guess guess) throws GameAlreadyCompletedException, InputValidationException, InvalidIdException {
        try {
            Integer.parseInt(guess.getGameId());
        } catch (NumberFormatException e) {
            throw new InvalidIdException("Invalid id.");
        }
        // call service method getGame with gameId passed into it and store in Game object variable
        Game game = dao.getGame(guess.getGameId());
        // if statement to check if game is already finished, if it is, throw GameAlreadyCompletedException
        if (game.isGameIsFinished()) {
            throw new GameAlreadyCompletedException("This game is already finished.");
        }
        // validate that guess is 4 digit number, parse to int and try catch exception and throw InputValidationException, but keep guess as String
        try {
            Integer.parseInt(guess.getRoundGuess());
        } catch (NumberFormatException e) {
            throw new InputValidationException("You must enter a four-digit number.");
        }

        if (guess.getRoundGuess().length() != 4) {
            throw new InputValidationException("You must enter a four-digit number.");
        }
        // instantiate new Round object, set roundGuess, LocalDateTime
        Round round = new Round();
        round.setRoundGuess(guess.getRoundGuess());
        round.setRoundDateTime(LocalDateTime.now().withNano(0));
        // call checkGuess and pass in Game object and Round object
        checkGuess(game, round);
        // send Round to DB with gameId
        dao.createRound(round, guess.getGameId());
        // return round with roundId
        return round;
    }

    // method for "game" option ----------------------------------------------------
    @Override
    public List<Game> getAllGames() throws NoGamesExistException {
        List<Game> gameList = dao.getAllGames();
        for (Game game : gameList) {
            hideOrShowAnswer(game);
        }
        if (gameList.isEmpty()) {
            throw new NoGamesExistException("There are no games. Please begin game.");
        }
        return gameList;
    }

    // method for "game/{gameId}" --------------------------------------------------
    @Override
    public Game getGame(String gameId) throws InvalidIdException {
        try {
            Integer.parseInt(gameId);
        } catch (NumberFormatException e) {
            throw new InvalidIdException("Invalid id.");
        }

        Game game = dao.getGame(gameId);
        hideOrShowAnswer(game);
        List<Round> gameRounds = dao.getAllRounds(gameId);
        game.setGameRounds(gameRounds);
        return game;
    }

    // method for "rounds/{gameId}" ------------------------------------------------
    @Override
    public List<Round> getAllRounds(String gameId) throws NoRoundsForGameIdException, InvalidIdException {
        try {
            Integer.parseInt(gameId);
        } catch (NumberFormatException e) {
            throw new InvalidIdException("Invalid id.");
        }

        List<Round> gameRounds = dao.getAllRounds(gameId);
        if (gameRounds.isEmpty()) {
            throw new NoRoundsForGameIdException("There are no rounds for that Game Id.");
        }
        return gameRounds;
    }

    // private methods =============================================================
    // random number generator -----------------------------------------------------
    private String generateRandomNumber() {

        String randomNumberAsString;
        List<Integer> fullNumberList = new ArrayList<>();
        List<Integer> numberList4Digit = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            fullNumberList.add(i);
        }

        Collections.shuffle(fullNumberList);

        for (int i = 0; i < 4; i++) {
            numberList4Digit.add(fullNumberList.get(i));
        }

        randomNumberAsString = numberList4Digit.get(0) + "" + numberList4Digit.get(1) + "" + numberList4Digit.get(2) + "" + numberList4Digit.get(3);

        return randomNumberAsString;
    }

    // determine whether or not to hide answer -------------------------------------
    private void hideOrShowAnswer(Game game) {
        if (!game.isGameIsFinished()) {
            game.setGameAnswer("HIDDEN");
        }
    }

    // check guess -----------------------------------------------------------------
    private void checkGuess(Game game, Round round) {
        // call checkGuess and pass in Game object and Round object, update DB if guess == answer, set roundResult and roundResultSummary, return Round object
        int exactMatches = 0, partialMatches = 0;
        String roundResultSummary = "You did not win yet.";
        char[] answerStringArray = game.getGameAnswer().toCharArray();
        char[] guessStringArray = round.getRoundGuess().toCharArray();

        // exact match
        for (int i = 0; i < 4; i++) {
            if (answerStringArray[i] == guessStringArray[i]) {
                exactMatches++;
            }
        }

        // partial match
        if (guessStringArray[0] == answerStringArray[1] || guessStringArray[0] == answerStringArray[2] || guessStringArray[0] == answerStringArray[3]) {
            partialMatches++;
        }
        if (guessStringArray[1] == answerStringArray[0] || guessStringArray[1] == answerStringArray[2] || guessStringArray[1] == answerStringArray[3]) {
            partialMatches++;
        }
        if (guessStringArray[2] == answerStringArray[0] || guessStringArray[2] == answerStringArray[1] || guessStringArray[2] == answerStringArray[3]) {
            partialMatches++;
        }
        if (guessStringArray[3] == answerStringArray[0] || guessStringArray[3] == answerStringArray[1] || guessStringArray[3] == answerStringArray[2]) {
            partialMatches++;
        }

        if (exactMatches == 4) {
            game.setGameIsFinished(true);
            roundResultSummary = "You won.";
            dao.updateGame(game);
        }

        round.setRoundResult("e:" + exactMatches + ":" + "p:" + partialMatches);
        round.setRoundResultSummary(roundResultSummary);

    }

}
