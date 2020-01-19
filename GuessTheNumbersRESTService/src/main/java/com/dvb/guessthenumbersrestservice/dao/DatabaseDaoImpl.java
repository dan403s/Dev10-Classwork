/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.guessthenumbersrestservice.dao;

import com.dvb.guessthenumbersrestservice.dto.Game;
import com.dvb.guessthenumbersrestservice.dto.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel Bart
 */
@Repository
@Profile("database")
public class DatabaseDaoImpl implements Dao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // method for "begin" option ---------------------------------------------------
    @Override
    @Transactional
    public Game createGame(Game game) {
        // insert known details into DB
        final String CREATE_GAME = "INSERT INTO Game(GameAnswer, GameIsFinished) VALUES(?,?)";
        jdbcTemplate.update(CREATE_GAME,
                game.getGameAnswer(),
                game.isGameIsFinished());

        // get gameId from DB and set to Game object and return
        String newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", String.class);
        game.setGameId(newId);
        return game;
    }

    // method for "guess" option ---------------------------------------------------
    @Override
    @Transactional
    public Round createRound(Round round, String gameId) {
        // insert known details into DB
        final String CREATE_ROUND = "INSERT INTO Round(RoundGuess, RoundDateTime, RoundResult, GameId, RoundResultSummary) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(CREATE_ROUND,
                round.getRoundGuess(),
                round.getRoundDateTime(),
                round.getRoundResult(),
                gameId,
                round.getRoundResultSummary());

        // get roundId from DB and set to Round object and return
        String newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", String.class);
        round.setRoundId(newId);
        return round;
    }
    
    @Override
    public boolean updateGame(Game game) {
        final String sql = "UPDATE Game SET GameAnswer = ?, GameIsFinished = ? WHERE GameId = ?";

        return jdbcTemplate.update(sql,
                game.getGameAnswer(),
                game.isGameIsFinished(),
                game.getGameId()) > 0;
    }
    
    // method for "game" option ----------------------------------------------------
    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT GameId, GameAnswer, GameIsFinished FROM Game";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    // method for "game/{gameId}" --------------------------------------------------
    @Override
    public Game getGame(String gameId) {
        final String sql = "SELECT GameId, GameAnswer, GameIsFinished FROM Game WHERE GameId = ?";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
    }    
    
    // method for "rounds/{gameId}" ------------------------------------------------
    @Override
    public List<Round> getAllRounds(String gameId) {
        final String sql = "SELECT * FROM Round WHERE GameId = ? ORDER BY RoundDateTime ASC";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }
    
    // method for deleting from DB for testing --------------------------------------
    @Override
    @Transactional
    public boolean deleteGame(String gameId) {
        final String sqlRound = "DELETE FROM Round WHERE GameId = ?";
        jdbcTemplate.update(sqlRound, gameId);
        
        final String sqlGame = "DELETE FROM Game WHERE GameId = ?;";
        return jdbcTemplate.update(sqlGame, gameId) > 0;
    }
    
    // rowMapper for Game ----------------------------------------------------------
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(String.valueOf(rs.getInt("GameId")));
            game.setGameAnswer(rs.getString("GameAnswer"));
            game.setGameIsFinished(rs.getBoolean("GameIsFinished"));
            return game;
        }
    }
    
    // rowMapper for Round ----------------------------------------------------------
    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(String.valueOf(rs.getInt("RoundId")));
            round.setRoundGuess(rs.getString("RoundGuess"));
            round.setRoundDateTime(rs.getTimestamp("RoundDateTime").toLocalDateTime());
            round.setRoundResult(rs.getString("RoundResult"));
            round.setRoundResultSummary(rs.getString("RoundResultSummary"));
            
            return round;
        }
    }
}
