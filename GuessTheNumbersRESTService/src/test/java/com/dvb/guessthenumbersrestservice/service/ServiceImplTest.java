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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Daniel Bart
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceImplTest {

    public ServiceImplTest() {
    }

    @Autowired
    private Service service;

    @Autowired
    private Dao dao;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    // use dao test database and create game and rounds for that game before each service layer test runs
    @Before
    public void setUp() {
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setGameIsFinished(false);
        game = dao.createGame(game);

        Round round = new Round();
        round.setRoundGuess("1243");
        round.setRoundDateTime(LocalDateTime.now().withNano(0));
        round.setRoundResult("e:2:p:2");
        round.setRoundResultSummary("You did not win yet.");
        dao.createRound(round, game.getGameId());

        Round round2 = new Round();
        round2.setRoundGuess("5678");
        round2.setRoundDateTime(LocalDateTime.now().withNano(0));
        round2.setRoundResult("e:0:p:0");
        round2.setRoundResultSummary("You did not win yet.");
        dao.createRound(round2, game.getGameId());

        Round round3 = new Round();
        round3.setRoundGuess("9012");
        round3.setRoundDateTime(LocalDateTime.now().withNano(0));
        round3.setRoundResult("e:0:p:2");
        round3.setRoundResultSummary("You did not win yet.");
        dao.createRound(round3, game.getGameId());
    }

    // use dao test database and delete game and rounds for that game after each service layer test runs
    @After
    public void tearDown() {
        List<Game> gameList = dao.getAllGames();
        for (Game game : gameList) {
            dao.deleteGame(game.getGameId());
        }
    }

    /**
     * Test of beginGame method, of class ServiceImpl.
     */
    @Test
    public void testBeginGame() throws Exception {
        List<Game> gameList = dao.getAllGames();
        for (Game game : gameList) {
            dao.deleteGame(game.getGameId());
        }

        Game newGame = service.beginGame();

        // test that Game created has 4 non-repeating digits answer
        Game newGameGetAnswer = dao.getGame(newGame.getGameId());
        String newGameAnswer = newGameGetAnswer.getGameAnswer();

        try {
            Integer.parseInt(newGameAnswer);
        } catch (NumberFormatException e) {
            throw new InputValidationException("You must enter a four-digit number.");
        }

        if (newGameAnswer.length() != 4) {
            throw new InputValidationException("You must enter a four-digit number.");
        }

        char[] newGameAnswerCharArray = newGameAnswer.toCharArray();

        if (newGameAnswerCharArray[0] == newGameAnswerCharArray[1] || newGameAnswerCharArray[0] == newGameAnswerCharArray[2] || newGameAnswerCharArray[0] == newGameAnswerCharArray[3]) {
            throw new InputValidationException("Digits are the same");
        } else if (newGameAnswerCharArray[1] == newGameAnswerCharArray[0] || newGameAnswerCharArray[1] == newGameAnswerCharArray[2] || newGameAnswerCharArray[1] == newGameAnswerCharArray[3]) {
            throw new InputValidationException("Digits are the same");
        } else if (newGameAnswerCharArray[2] == newGameAnswerCharArray[0] || newGameAnswerCharArray[2] == newGameAnswerCharArray[1] || newGameAnswerCharArray[2] == newGameAnswerCharArray[3]) {
            throw new InputValidationException("Digits are the same");
        } else if (newGameAnswerCharArray[3] == newGameAnswerCharArray[0] || newGameAnswerCharArray[3] == newGameAnswerCharArray[1] || newGameAnswerCharArray[3] == newGameAnswerCharArray[2]) {
            throw new InputValidationException("Digits are the same");
        }

        // test that Game created has correct status
        assertFalse(newGame.isGameIsFinished());

        // test that Game created hides the answer
        assertEquals("HIDDEN", newGame.getGameAnswer());

    }

    /**
     * Test of guessAnswer method, of class ServiceImpl.
     */
    @Test
    public void testGuessAnswerCorrectly() throws Exception {
        List<Game> gameList = dao.getAllGames();
        Game game = gameList.get(0);
        Guess guess = new Guess();
        guess.setGameId(game.getGameId());
        guess.setRoundGuess("1234");
        
        Round round = service.guessAnswer(guess);
        
        assertEquals("e:4:p:0",round.getRoundResult());
        assertEquals("You won.", round.getRoundResultSummary());
        
        Game fromDaoGame = dao.getGame(game.getGameId());
        
        assertTrue(fromDaoGame.isGameIsFinished());
    }

    @Test
    public void testGuessAnswerIncorrectly() throws Exception {
        List<Game> gameList = dao.getAllGames();
        Game game = gameList.get(0);
        Guess guess = new Guess();
        guess.setGameId(game.getGameId());
        guess.setRoundGuess("1243");
        
        Round round = service.guessAnswer(guess);
        
        assertEquals("e:2:p:2",round.getRoundResult());
        assertEquals("You did not win yet.", round.getRoundResultSummary());
    }

    @Test
    public void testGuessAnswerNotNumber() throws Exception {
        List<Game> gameList = dao.getAllGames();
        Game game = gameList.get(0);
        Guess guess = new Guess();
        guess.setGameId(game.getGameId());
        guess.setRoundGuess("test");

        try {
            Round round = service.guessAnswer(guess);
            fail("Expecting InputValidationException.");
        } catch (InputValidationException e) {

        }
    }

    @Test
    public void testGuessAnswerNot4Digits() throws Exception {
        List<Game> gameList = dao.getAllGames();
        Game game = gameList.get(0);
        Guess guess = new Guess();
        guess.setGameId(game.getGameId());
        guess.setRoundGuess("98765");

        try {
            Round round = service.guessAnswer(guess);
            fail("Expecting InputValidationException.");
        } catch (InputValidationException e) {

        }
    }

    @Test
    public void testGuessAnswerGameIdNotValid() throws Exception {
        Guess guess = new Guess();
        guess.setGameId("100");
        guess.setRoundGuess("9876");

        try {
            Round round = service.guessAnswer(guess);
            fail("Expecting EmptyResultDataAccessException.");
        } catch (EmptyResultDataAccessException e) {

        }
    }

    @Test
    public void testGuessAnswerGameAlreadyFinished() throws Exception {
        List<Game> gameList = dao.getAllGames();
        Game game = gameList.get(0);
        game.setGameIsFinished(true);
        dao.updateGame(game);

        Guess guess = new Guess();
        guess.setGameId(game.getGameId());
        guess.setRoundGuess("9876");

        try {
            Round round = service.guessAnswer(guess);
            fail("Expecting GameAlreadyCompletedException.");
        } catch (GameAlreadyCompletedException e) {

        }
    }

    /**
     * Test of getAllGames method, of class ServiceImpl.
     */
    @Test
    public void testGetAllGames() throws Exception {
        // do not display answer for in-progress games
        List<Game> gameList = service.getAllGames();
        Game hiddenAnswerGame = gameList.get(0);
        assertEquals("HIDDEN", hiddenAnswerGame.getGameAnswer());

        // test for 1 game that is not null
        assertNotNull(hiddenAnswerGame);

        // delete all
        List<Game> gameListToDelete = dao.getAllGames();
        for (Game gameToDelete : gameListToDelete) {
            dao.deleteGame(gameToDelete.getGameId());
        }

        // test for no games
        try {
            service.getAllGames();
            fail("Expecting NoGamesExistException.");
        } catch (NoGamesExistException e) {

        }

        // create 2 games with status true and answer
        Game game = new Game();
        game.setGameAnswer("9876");
        game.setGameIsFinished(true);
        game = dao.createGame(game);

        Game game2 = new Game();
        game2.setGameAnswer("9876");
        game2.setGameIsFinished(true);
        game2 = dao.createGame(game2);

        // display answer because game is not in progress
        List<Game> gameList2 = service.getAllGames();
        Game returnedGame = gameList2.get(0);
        Game returnedGame2 = gameList2.get(1);

        assertEquals(2, gameList2.size());
        assertEquals("9876", returnedGame.getGameAnswer());
        assertEquals("9876", returnedGame2.getGameAnswer());
    }

    /**
     * Test of getGame method, of class ServiceImpl.
     */
    @Test
    public void testGetGame() throws Exception {
        List<Game> gameList = dao.getAllGames();
        Game game = gameList.get(0);

        // do not display answer for in-progress games
        Game hiddenAnswerGame = service.getGame(game.getGameId());
        assertEquals("HIDDEN", hiddenAnswerGame.getGameAnswer());

        // test that game not null
        assertNotNull(hiddenAnswerGame);

        // test for 3 rounds
        assertEquals(3, hiddenAnswerGame.getGameRounds().size());

        // delete all
        List<Game> gameListToDelete = dao.getAllGames();
        for (Game gameToDelete : gameListToDelete) {
            dao.deleteGame(gameToDelete.getGameId());
        }

        // create game with status true and answer
        Game game2 = new Game();
        game2.setGameAnswer("9876");
        game2.setGameIsFinished(true);
        game2 = dao.createGame(game2);

        // display answer because game is not in progress
        Game shownAnswerGame = service.getGame(game2.getGameId());
        assertEquals("9876", shownAnswerGame.getGameAnswer());
    }

    /**
     * Test of getAllRounds method, of class ServiceImpl.
     */
    @Test
    public void testGetAllRounds() throws Exception {
        List<Game> gameList = dao.getAllGames();
        Game game = gameList.get(0);

        List<Round> roundList = service.getAllRounds(game.getGameId());

        assertEquals(3, roundList.size());

        List<Game> gameListToDelete = dao.getAllGames();
        for (Game gameToDelete : gameListToDelete) {
            dao.deleteGame(gameToDelete.getGameId());
        }

        Game game2 = new Game();
        game2.setGameAnswer("1234");
        game2.setGameIsFinished(false);
        game2 = dao.createGame(game2);

        try {
            service.getAllRounds(game2.getGameId());
            fail("Expecting NoRoundsForGameIdException.");
        } catch (NoRoundsForGameIdException e) {

        }
    }

}
