CREATE DATABASE IF NOT EXISTS MovieCatalog;

USE MovieCatalog;

CREATE TABLE IF NOT EXISTS `Actor` (
	`ActorId` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `FirstName` VARCHAR(30) NOT NULL,
    `LastName` VARCHAR(30) NOT NULL, 
    `BirthDate` DATE NULL
);

CREATE TABLE IF NOT EXISTS `Genre` (
	`GenreId` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `GenreName` VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Rating` (
	`RatingId` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `RatingName` VARCHAR(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Director` (
	`DirectorId` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `FirstName` VARCHAR(30) NOT NULL,
    `LastName` VARCHAR(30) NOT NULL,
    `BirthDate` DATE NULL
);

CREATE TABLE IF NOT EXISTS `Movie` (
	`MovieId` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `Title` VARCHAR(128) NOT NULL,
    `ReleaseDate` DATE NULL,
    `RatingId` INT(11) NULL,
	FOREIGN KEY `fk_Movie_Rating` (`RatingId`)
		REFERENCES `Rating` (`RatingId`)
);

CREATE TABLE IF NOT EXISTS `CastMember` (
	`CastMemberId` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `Role` VARCHAR(50) NOT NULL,
    `ActorId` INT(11) NOT NULL,
    FOREIGN KEY `fk_CastMember_Actor` (`ActorId`)
		REFERENCES `Actor` (`ActorId`)
);

CREATE TABLE IF NOT EXISTS `CastMemberMovie` (
	`CastMemberId` INT(11) NOT NULL,
    `MovieId` INT(11) NOT NULL,
    PRIMARY KEY `pk_CastMemberMovie` (`CastMemberId`, `MovieId`),
    FOREIGN KEY `fk_CastMemberMovie_CastMember` (`CastMemberId`)
		REFERENCES `CastMember` (`CastMemberId`),
	FOREIGN KEY `fk_CastMemberMovie_Movie` (`MovieId`)
		REFERENCES `Movie` (`MovieId`)
);

CREATE TABLE IF NOT EXISTS `MovieDirector` (
	`MovieId` INT(11) NOT NULL,
    `DirectorId` INT(11) NOT NULL,
    PRIMARY KEY `pk_MovieDirector` (`MovieId`, `DirectorId`),
    FOREIGN KEY `fk_MovieDirector_Movie` (`MovieId`)
		REFERENCES `Movie` (`MovieId`),
	FOREIGN KEY `fk_MovieDirector_Director` (`DirectorId`)
		REFERENCES `Director` (`DirectorId`)
);

CREATE TABLE IF NOT EXISTS `MovieGenre` (
	`MovieId` INT(11) NOT NULL,
    `GenreId` INT(11) NOT NULL,
    PRIMARY KEY `pk_MovieGenre` (`MovieId`, `GenreId`),
    FOREIGN KEY `fk_MovieGenre_Movie` (`MovieId`)
		REFERENCES `Movie` (`MovieId`),
	FOREIGN KEY `fk_MovieGenre_Genre` (`GenreId`)
		REFERENCES `Genre` (`GenreId`)
);