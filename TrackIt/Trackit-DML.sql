USE TrackIt;

INSERT INTO `Worker` (
	`WorkerId`, 
	`FirstName`, 
	`LastName`
	) VALUES (
    1,
    'Rosemonde',
    'Featherbie'
	);

INSERT INTO `Worker` (
	`FirstName`,
    `LastName`
    ) VALUES (
    'Kingsly',
    'Besantie'
	);
    
INSERT INTO `Worker` (
	`FirstName`, 
	`LastName`
    ) VALUES (
    'Goldi',
    'Pilipets'
    ),
    (
    'Dorey',
    'Rulf'
    ),
    (
    'Panchito',
    'Ashtonhurst'
    );
    
INSERT INTO `Worker` (
	`WorkerId`,
    `FirstName`,
    `LastName`
    ) VALUES (
    50,
    'Valentino',
    'Newvill'
    );

INSERT INTO `Project` (
	`ProjectId`,
    `Name`,
    `DueDate`
    ) VALUES (
    'db-milestone',
    'Database Material',
    '2018-12-31'
    );
    
INSERT INTO `ProjectWorker` (
	`ProjectId`,
    `WorkerId`
    ) VALUES (
    'db-milestone',
    2
    );

INSERT INTO `Project` (
	`ProjectId`,
    `Name`,
    `DueDate`
    ) VALUES (
    'kitchen',
    'Kitchen Remodel',
    '2025-07-15'
    );

INSERT INTO `ProjectWorker` (
	`ProjectId`, 
    `WorkerId`
    ) VALUES (
    'db-milestone',
    1
    ),
    (
    'kitchen',
    2
    ),
    (
    'db-milestone',
    3
    ),
    (
    'db-milestone',
    4
    );
    
UPDATE `Project` SET
	`Summary` = 'All lessons and exercises for the relational database milestone.',
    `DueDate` = '2018-10-15'
WHERE `ProjectId` = 'db-milestone';

UPDATE `Worker` SET
	`LastName` = 'Oaks'
WHERE `WorkerId` = 2;

SET SQL_SAFE_UPDATES = 0;

UPDATE `Project` SET
    `IsActive` = 0
WHERE `DueDate` BETWEEN '2017-01-01' AND '2017-12-31'
AND `IsActive` = 1;

SET SQL_SAFE_UPDATES = 1;

UPDATE Task SET
    EstimatedHours = EstimatedHours * 1.25
WHERE WorkerId = 2;

SELECT * FROM `Worker`
	WHERE `WorkerId` = 50;
    
DELETE FROM `Worker`
	WHERE `WorkerId` = 50;
    
SET SQL_SAFE_UPDATES = 0;

DELETE FROM Worker
WHERE FirstName = 'Kingsly';

SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM Task
	WHERE WorkerId = 2;

DELETE FROM ProjectWorker
	WHERE WorkerId = 2;

DELETE FROM Worker
	WHERE WorkerId = 2;

SET SQL_SAFE_UPDATES = 1;