USE TrackIt;

SELECT * FROM Worker;

SELECT *
FROM `Worker`
ORDER BY `LastName`;

SELECT *
FROM `Worker`
ORDER BY `LastName` DESC;

SELECT * 
FROM `Worker`
ORDER BY `LastName` ASC;

SELECT
    `w`.`FirstName`,
    `w`.`LastName`,
    `p`.`Name` `ProjectName`
FROM `Worker` `w`
INNER JOIN `ProjectWorker` `pw` ON `w`.`WorkerId` = `pw`.`WorkerId`
INNER JOIN `Project` `p` ON `pw`.`ProjectId` = `p`.`ProjectId`
ORDER BY `w`.`LastName` ASC;

SELECT
    `w`.`FirstName`,
    `w`.`LastName`,
    `p`.`Name` `ProjectName`
FROM `Worker` `w`
INNER JOIN `ProjectWorker` `pw` ON `w`.`WorkerId` = `pw`.`WorkerId`
INNER JOIN `Project` `p` ON `pw`.`ProjectId` = `p`.`ProjectId`
ORDER BY `w`.`LastName` ASC, `p`.`name` ASC;

SELECT
    `w`.`FirstName`,
    `w`.`LastName`,
    `p`.`Name` `ProjectName`
FROM `Worker` `w`
INNER JOIN `ProjectWorker` `pw` ON `w`.`WorkerId` = `pw`.`WorkerId`
INNER JOIN `Project` `p` ON `pw`.`ProjectId` = `p`.`ProjectId`
ORDER BY `w`.`LastName` DESC, `p`.`name` ASC;

SELECT
    `t`.`Title`,
    `s`.`Name` `StatusName`
FROM `Task` `t`
LEFT OUTER JOIN `TaskStatus` `s` ON `t`.`TaskStatusId` = `s`.`TaskStatusId`
ORDER BY `s`.`Name` ASC;

SELECT
    `t`.`Title`,
    `s`.`Name` `StatusName`
FROM `Task` `t`
LEFT OUTER JOIN `TaskStatus` `s` ON `t`.`TaskStatusId` = `s`.`TaskStatusId`
ORDER BY ISNULL(`s`.`Name`), `s`.`Name` ASC;

SELECT *
FROM Worker
ORDER BY LastName ASC
LIMIT 0, 10;

SELECT *
FROM Worker
ORDER BY LastName ASC
LIMIT 10, 10;

SELECT *
FROM Worker
ORDER BY LastName DESC
LIMIT 200, 10;

SELECT
    `w`.`FirstName`,
    `w`.`LastName`,
    `p`.`Name` `ProjectName`
FROM `Worker` `w`
INNER JOIN `ProjectWorker` `pw` ON `w`.`WorkerId` = `pw`.`WorkerId`
INNER JOIN `Project` `p` ON `pw`.`ProjectId` = `p`.`ProjectId`
ORDER BY `w`.`LastName` ASC, `p`.`name` ASC
LIMIT 100, 25;