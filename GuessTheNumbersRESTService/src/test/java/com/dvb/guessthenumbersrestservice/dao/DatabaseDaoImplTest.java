/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.dao;

import com.dvb.guessthenumbersrestservice.dto.Game;
import com.dvb.guessthenumbersrestservice.dto.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
public class DatabaseDaoImplTest {

    public DatabaseDaoImplTest() {
    }

    @Autowired
    private Dao dao;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Game> gameList = dao.getAllGames();
        for (Game game : gameList) {
            dao.deleteGame(game.getGameId());
        }
    }

    @After
    public void tearDown() {
        List<Game> gameList = dao.getAllGames();
        for (Game game : gameList) {
            dao.deleteGame(game.getGameId());
        }
    }

    /**
     * Test of createGame and getGame methods, of class DatabaseDaoImpl.
     */
    @Test
    public void testCreateGetGame() {
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setGameIsFinished(false);
        game = dao.createGame(game);

        Game fromDao = dao.getGame(game.getGameId());

        assertEquals(game, fromDao);
    }

    /**
     * Test of createGame method, of class DatabaseDaoImpl.
     */
    @Test
    public void testCreateGame() {
        // tested in testCreateGetGame method test
    }

    /**
     * Test of createRound and getAllRounds methods, of class DatabaseDaoImpl.
     */
    @Test
    public void testCreateGetAllRound() {
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setGameIsFinished(false);
        game = dao.createGame(game);

        List<Round> rounds = new ArrayList<>();

        Round round = new Round();
        round.setRoundGuess("1243");
        round.setRoundDateTime(LocalDateTime.now().withNano(0));
        round.setRoundResult("e:2:p:2");
        round.setRoundResultSummary("You did not win yet.");
        round = dao.createRound(round, game.getGameId());
        rounds.add(round);

        Round round2 = new Round();
        round2.setRoundGuess("5678");
        round2.setRoundDateTime(LocalDateTime.now().withNano(0));
        round2.setRoundResult("e:0:p:0");
        round2.setRoundResultSummary("You did not win yet.");
        round2 = dao.createRound(round2, game.getGameId());
        rounds.add(round2);

        Round round3 = new Round();
        round3.setRoundGuess("9012");
        round3.setRoundDateTime(LocalDateTime.now().withNano(0));
        round3.setRoundResult("e:0:p:2");
        round3.setRoundResultSummary("You did not win yet.");
        round3 = dao.createRound(round3, game.getGameId());
        rounds.add(round3);

        List<Round> fromDaoRounds = dao.getAllRounds(game.getGameId());

        assertEquals(rounds, fromDaoRounds);
        assertEquals(3, fromDaoRounds.size());
        assertTrue(fromDaoRounds.contains(round));
        assertTrue(fromDaoRounds.contains(round2));
        assertTrue(fromDaoRounds.contains(round3));
    }

    /**
     * Test of createRound method, of class DatabaseDaoImpl.
     */
    @Test
    public void testCreateRound() {
        // tested in testCreateGetAllRound method test
    }

    /**
     * Test of updateGame method, of class DatabaseDaoImpl.
     */
    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setGameIsFinished(false);
        game = dao.createGame(game);

        Game fromDao = dao.getGame(game.getGameId());

        assertEquals(game, fromDao);
        
        game.setGameAnswer("4321");
        game.setGameIsFinished(true);
        
        dao.updateGame(game);
        
        assertNotEquals(game, fromDao);
        
        fromDao = dao.getGame(game.getGameId());
        
        assertEquals(game, fromDao);

    }

    /**
     * Test of getAllGames method, of class DatabaseDaoImpl.
     */
    @Test
    public void testGetAllGames() {
        List<Game> games = new ArrayList<>();
        
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setGameIsFinished(false);
        game = dao.createGame(game);
        games.add(game);
        
        Game game2 = new Game();
        game2.setGameAnswer("1234");
        game2.setGameIsFinished(false);
        game2 = dao.createGame(game2);
        games.add(game2);
        
        List<Game> fromDaoGames = dao.getAllGames();
        
        assertEquals(2, fromDaoGames.size());
        assertTrue(fromDaoGames.contains(game));
        assertTrue(fromDaoGames.contains(game2));
        assertEquals(games, fromDaoGames);
    }

    /**
     * Test of getGame method, of class DatabaseDaoImpl.
     */
    @Test
    public void testGetGame() {
        // tested in testCreateGetGame method test
    }

    /**
     * Test of getAllRounds method, of class DatabaseDaoImpl.
     */
    @Test
    public void testGetAllRounds() {
        // tested in testCreateGetAllRound method test
    }

    /**
     * Test of deleteGame method, of class DatabaseDaoImpl.
     */
    @Test
    public void testDeleteGame() {
        Game game = new Game();
        game.setGameAnswer("1234");
        game.setGameIsFinished(false);
        game = dao.createGame(game);

        Round round = new Round();
        round.setRoundGuess("1243");
        round.setRoundDateTime(LocalDateTime.now().withNano(0));
        round.setRoundResult("e:2:p:2");
        round.setRoundResultSummary("You did not win yet.");
        round = dao.createRound(round, game.getGameId());

        Round round2 = new Round();
        round2.setRoundGuess("5678");
        round2.setRoundDateTime(LocalDateTime.now().withNano(0));
        round2.setRoundResult("e:0:p:0");
        round2.setRoundResultSummary("You did not win yet.");
        round2 = dao.createRound(round2, game.getGameId());

        Round round3 = new Round();
        round3.setRoundGuess("9012");
        round3.setRoundDateTime(LocalDateTime.now().withNano(0));
        round3.setRoundResult("e:0:p:2");
        round3.setRoundResultSummary("You did not win yet.");
        round3 = dao.createRound(round3, game.getGameId());
        
        assertEquals(3, dao.getAllRounds(game.getGameId()).size());
        
        Game game2 = new Game();
        game2.setGameAnswer("1234");
        game2.setGameIsFinished(false);
        game2 = dao.createGame(game2);

        Round round4 = new Round();
        round4.setRoundGuess("1243");
        round4.setRoundDateTime(LocalDateTime.now().withNano(0));
        round4.setRoundResult("e:2:p:2");
        round4.setRoundResultSummary("You did not win yet.");
        round4 = dao.createRound(round4, game2.getGameId());

        Round round5 = new Round();
        round5.setRoundGuess("5678");
        round5.setRoundDateTime(LocalDateTime.now().withNano(0));
        round5.setRoundResult("e:0:p:0");
        round5.setRoundResultSummary("You did not win yet.");
        round5 = dao.createRound(round5, game2.getGameId());

        Round round6 = new Round();
        round6.setRoundGuess("9012");
        round6.setRoundDateTime(LocalDateTime.now().withNano(0));
        round6.setRoundResult("e:0:p:2");
        round6.setRoundResultSummary("You did not win yet.");
        round6 = dao.createRound(round6, game2.getGameId());
        
        assertEquals(3, dao.getAllRounds(game2.getGameId()).size());
        
        assertEquals(2, dao.getAllGames().size());
        
        dao.deleteGame(game.getGameId());
        
        assertEquals(1, dao.getAllGames().size());
        
        dao.deleteGame(game2.getGameId());
        
        assertEquals(0, dao.getAllGames().size());
        
        try {
            dao.getGame(game.getGameId());
            fail("THIS SHOULD HAVE THROWN EmptyResultDataAccessException.");
        } catch(EmptyResultDataAccessException e) {

        }
        
        try {
            dao.getGame(game2.getGameId());
            fail("THIS SHOULD HAVE THROWN EmptyResultDataAccessException.");
        } catch(EmptyResultDataAccessException e) {

        }
                
        assertEquals(0, dao.getAllRounds(game.getGameId()).size());
        assertEquals(0, dao.getAllRounds(game2.getGameId()).size());
    }

}
