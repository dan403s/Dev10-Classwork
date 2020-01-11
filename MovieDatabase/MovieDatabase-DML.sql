USE `MovieCatalogue`;

INSERT INTO `Actor` (
	`FirstName`,
    `LastName`,
    `BirthDate`
    ) VALUES (
    'Bill',
    'Murray',
    '1950-09-21'
    ), (
    'Dan',
    'Aykroyd',
    '1952-07-01'
    ), (
    'John',
    'Candy',
    '1950-10-31'
    ), (
    'Steve',
    'Martin',
    null
    ), (
    'Sylvester',
    'Stallone',
    null
    );
    
INSERT INTO `Director` (
	`FirstName`,
    `LastName`,
    `BirthDate`
    ) VALUES (
    'Ivan',
    'Reitman',
    '1946-10-27'
    ), (
    'Ted',
    'Kotcheff',
    null
    );
    
INSERT INTO `Rating` (
	`RatingName`
    ) VALUES (
    'G'
    ), (
    'PG'
    ), (
    'PG-13'
    ), (
    'R'
    );

INSERT INTO `Genre` (
	`GenreName`
    ) VALUES (
    'Action'
    ), (
    'Comedy'
    ), (
    'Drama'
    ), (
    'Horror'
    );
    
INSERT INTO `Movie` (
	`GenreId`,
    `DirectorId`,
    `RatingId`,
    `Title`,
    `ReleaseDate`
    ) VALUES (
    1,
    2,
    3,
    'Rambo: First Blood',
    '1982-10-22'
    ), (
    2,
    null,
    4,
    'Planes, Trains & Automobiles',
    '1987-11-25'
    ), (
    2,
    1,
    2,
    'Ghostbusters',
    null
    ), (
    2,
    null,
    2,
    'The Great Outdoors',
    '1988-06-17'
    );
    
UPDATE `Movie` SET
	`RatingId` = 4
WHERE `MovieId` = 1;
    
INSERT INTO `CastMember` (
	`ActorId`,
    `MovieId`,
    `Role`
    ) VALUES (
	5,
    1,
    'John Rambo'
    ), (
	4,
    2,
    'Neil Page'
    ), (
    3,
    2,
    'Del Griffith'
    ), (
    1,
    3,
    'Dr. Peter Venkman'
    ), (
    2,
    3,
    'Dr. Raymond Stanz'
    ), (
    2,
    4,
    'Roman Craig'
    ), (
    3,
    4,
    'Chet Ripley'
    );
    
UPDATE `Movie` SET
	`Title` = 'Ghostbusters (1984)',
    `ReleaseDate` = '1984-06-08'
WHERE `MovieId` = 3;

UPDATE `Genre` SET
	`GenreName` = 'Action/Adventure'
WHERE `GenreId` = 1;

DELETE FROM `CastMember`
WHERE `CastMemberId` = 1;

DELETE FROM `Movie`
WHERE `MovieId` = 1;

ALTER TABLE `Actor`
	ADD COLUMN (
		`DateOfDeath` DATE NOT NULL
    );
    
ALTER TABLE `Actor`
	DROP COLUMN `DateOfDeath`;
    
ALTER TABLE `Actor`
	ADD COLUMN (
		`DateOfDeath` DATE NULL
	);

UPDATE `Actor` SET
	`DateOfDeath` = '1994-03-04'
WHERE `ActorId` = 3;