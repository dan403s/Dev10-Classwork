-- Drops the existing database, if it exists, so that the script can rebuild a database with the same name.
DROP DATABASE IF EXISTS GuessTheNumbersRESTService;

CREATE DATABASE IF NOT EXISTS GuessTheNumbersRESTService;

USE GuessTheNumbersRESTService;

CREATE TABLE Game (
	GameId INT PRIMARY KEY AUTO_INCREMENT,
    GameAnswer VARCHAR(4) NOT NULL,
    GameIsFinished TINYINT(1) NOT NULL
);

CREATE TABLE Round (
	RoundId INT PRIMARY KEY AUTO_INCREMENT,
    RoundGuess VARCHAR(4) NOT NULL,
    RoundDateTime DATETIME NOT NULL,
    RoundResult VARCHAR(7) NOT NULL,
    RoundResultSummary VARCHAR(30) NOT NULL,
    GameId INT NOT NULL,
    FOREIGN KEY fk_Round_Game (GameId)
		REFERENCES Game (GameId)
);