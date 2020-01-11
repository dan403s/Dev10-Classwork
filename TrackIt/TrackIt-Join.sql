USE TrackIt;

SELECT * 
FROM `TaskStatus` 
WHERE `IsResolved` = 1;

SELECT * 
FROM `Task`
WHERE `TaskStatusId` IN (5,6,7,8);

SELECT 
	`Task`.`TaskId`,
    `Task`.`Title`,
    `TaskStatus`.`Name`
FROM `TaskStatus`
INNER JOIN `Task` ON `TaskStatus`.`TaskStatusId` = `Task`.`TaskStatusId`
WHERE `TaskStatus`.`IsResolved` = 1;

SELECT
	`Project`.`Name`,
    `Worker`.`FirstName`,
    `Worker`.`LastName`
FROM `Project`
INNER JOIN `ProjectWorker` ON `Project`.`ProjectId` = `ProjectWorker`.`ProjectId`
INNER JOIN `Worker` ON `ProjectWorker`.`WorkerId` = `Worker`.`WorkerId`
WHERE `Project`.`ProjectId` = 'game-goodboy';

SELECT
	`Project`.`Name`,
    `Worker`.`FirstName`,
    `Worker`.`LastName`,
    `Task`.`Title`
FROM `Project`
INNER JOIN `ProjectWorker` ON `Project`.`ProjectId` = `ProjectWorker`.`ProjectId`
INNER JOIN `Worker` ON `ProjectWorker`.`WorkerId` = `Worker`.`WorkerId`
INNER JOIN `Task` ON `ProjectWorker`.`ProjectId` = `Task`.`ProjectId`
    AND `ProjectWorker`.`WorkerId` = `Task`.`WorkerId`
WHERE `Project`.`ProjectId` = 'game-goodboy';

SELECT * FROM `Task`;

SELECT *
FROM `Task`
INNER JOIN `TaskStatus` ON `Task`.`TaskStatusId` = `TaskStatus`.`TaskStatusId`;

SELECT * 
FROM `Task`
WHERE `TaskStatusId` IS NULL;

SELECT *
FROM `Task`
LEFT OUTER JOIN `TaskStatus` ON `Task`.`TaskStatusId` = `TaskStatus`.`TaskStatusId`;

SELECT
	`Task`.`TaskId`,
    `Task`.`Title`,
    IFNULL(`Task`.`TaskStatusId`, 0) AS `TaskStatusId`,
	IFNULL(`TaskStatus`.`Name`, '[None]') AS `StatusName`
FROM `Task`
LEFT OUTER JOIN `TaskStatus` ON `Task`.`TaskStatusId` = `TaskStatus`.`TaskStatusId`;

SELECT
    IFNULL(`Project`.`Name`, '[No Project]') AS `ProjectName`,
    IFNULL(`Worker`.`FirstName`, '[No Name]') AS `WorkerFirstName`,
    IFNULL(`Worker`.`LastName`, '[No Name]') AS `WorkerLastName`
FROM `Project`
LEFT OUTER JOIN `ProjectWorker` ON `Project`.`ProjectId` = `ProjectWorker`.`ProjectId`
LEFT OUTER JOIN `Worker` ON `ProjectWorker`.`WorkerId` = `Worker`.`WorkerId`
WHERE `ProjectWorker`.`WorkerId` IS NULL;

SELECT
    IFNULL(`Project`.`Name`, '[No Project]') AS `ProjectName`
FROM `Project`
LEFT OUTER JOIN `ProjectWorker` ON `Project`.`ProjectId` = `ProjectWorker`.`ProjectId`
WHERE `ProjectWorker`.`WorkerId` IS NULL;

SELECT
    IFNULL(`Project`.`Name`, '[No Project]') AS `ProjectName`,
    IFNULL(`Worker`.`FirstName`, '[No Name]') AS `WorkerFirstName`,
    IFNULL(`Worker`.`LastName`, '[No Name]') AS `WorkerLastName`
FROM `Project`
RIGHT OUTER JOIN `ProjectWorker` ON `Project`.`ProjectId` = `ProjectWorker`.`ProjectId`
RIGHT OUTER JOIN `Worker` ON `ProjectWorker`.`WorkerId` = `Worker`.`WorkerId`
WHERE `ProjectWorker`.`WorkerId` IS NULL;

SELECT
    `Worker`.`FirstName`,
    `Worker`.`LastName`
FROM `ProjectWorker`
RIGHT OUTER JOIN `Worker` ON `ProjectWorker`.`WorkerId` = `Worker`.`WorkerId`
WHERE `ProjectWorker`.`ProjectId` IS NULL;

SELECT
    `Worker`.`FirstName`,
    `Worker`.`LastName`
FROM `Worker`
LEFT OUTER JOIN `ProjectWorker` ON `Worker`.`WorkerId` = `ProjectWorker`.`WorkerId`
WHERE `ProjectWorker`.`WorkerId` IS NULL;

SELECT *
FROM `Task`
INNER JOIN `Task` ON `Task`.`TaskId` = `Task`.`ParentTaskId`;

SELECT
	`parent`.`TaskId` `ParentTaskId`,
    `child`.`TaskId` `ChildTaskId`,
    CONCAT(`parent`.`Title`, ': ', `child`.`Title`) `Title`
FROM `Task` `parent`
INNER JOIN `Task` `child` ON `parent`.`TaskId` = `child`.`ParentTaskId`;

SELECT
    `p`.`Name` `ProjectName`,
    `w`.`FirstName`,
    `w`.`LastName`,
    `t`.`Title`
FROM `Project` `p`
INNER JOIN `ProjectWorker` `pw` ON `p`.`ProjectId` = `pw`.`ProjectId`
INNER JOIN `Worker` `w` ON `pw`.`WorkerId` = `w`.`WorkerId`
INNER JOIN `Task` `t` ON `pw`.`ProjectId` = `t`.`ProjectId`
    AND `pw`.`WorkerId` = `t`.`WorkerId`
WHERE `p`.`ProjectId` = 'game-goodboy';

SELECT
	CONCAT(`w`.`FirstName`, ' ', `w`.`LastName`) `WorkerName`,
    `p`.`Name` `ProjectName`
FROM `Worker` `w`
CROSS JOIN `Project` `p`
WHERE `w`.`WorkerId` = 1
AND `p`.`ProjectId` NOT LIKE 'game-%';