USE PersonalTrainer;

-- Select all columns from ExerciseCategory and Exercise.
-- The tables should be joined on ExerciseCategoryId.
-- This query returns all Exercises and their associated ExerciseCategory.
-- 64 rows
-------------------- GOOD
SELECT *
FROM `ExerciseCategory` `ec`
INNER JOIN `Exercise` `e` 
	ON `ec`.`ExerciseCategoryId` = `e`.`ExerciseCategoryId`;
    
-- Select ExerciseCategory.Name and Exercise.Name
-- where the ExerciseCategory does not have a ParentCategoryId (it is null).
-- Again, join the tables on their shared key (ExerciseCategoryId).
-- 9 rows
-------------------- GOOD
SELECT 
	`ec`.`Name`,
    `e`.`Name`
FROM `ExerciseCategory` `ec`
INNER JOIN `Exercise` `e` 
	ON `ec`.`ExerciseCategoryId` = `e`.`ExerciseCategoryId`
WHERE `ec`.`ParentCategoryId` IS NULL;

-- The query above is a little confusing. At first glance, it's hard to tell
-- which Name belongs to ExerciseCategory and which belongs to Exercise.
-- Rewrite the query using an aliases. 
-- Alias ExerciseCategory.Name as 'CategoryName'.
-- Alias Exercise.Name as 'ExerciseName'.
-- 9 rows
-------------------- GOOD
SELECT 
	`ec`.`Name` `CategoryName`,
    `e`.`Name` `ExerciseName`
FROM `ExerciseCategory` `ec`
INNER JOIN `Exercise` `e` 
	ON `ec`.`ExerciseCategoryId` = `e`.`ExerciseCategoryId`
WHERE `ec`.`ParentCategoryId` IS NULL;

-- Select FirstName, LastName, and BirthDate from Client
-- and EmailAddress from Login 
-- where Client.BirthDate is in the 1990s.
-- Join the tables by their key relationship. 
-- What is the primary-foreign key relationship?
-- 35 rows
-------------------- GOOD
SELECT
	`c`.`FirstName`,
    `c`.`LastName`,
    `c`.`BirthDate`,
    `l`.`EmailAddress`
FROM `Client` `c`
INNER JOIN `Login` `l`
	ON `c`.`ClientId` = `l`.`ClientId`
WHERE `c`.`BirthDate` BETWEEN '1990-01-01' AND '1999-12-31';

-- Select Workout.Name, Client.FirstName, and Client.LastName
-- for Clients with LastNames starting with 'C'?
-- How are Clients and Workouts related?
-- 25 rows
-------------------- GOOD
SELECT
    `c`.`FirstName`,
    `c`.`LastName`,
    `w`.`Name`
FROM `Client` `c`
INNER JOIN `ClientWorkout` `cw`
	ON `c`.`ClientId` = `cw`.`ClientId`
INNER JOIN `Workout` `w`
	ON `cw`.`WorkoutId` = `w`.`WorkoutId`
WHERE `c`.`LastName` LIKE 'C%';

-- Select Names from Workouts and their Goals.
-- This is a many-to-many relationship with a bridge table.
-- Use aliases appropriately to avoid ambiguous columns in the result.
-------------------- GOOD
SELECT
	`w`.`Name` `WorkoutName`,
    `g`.`Name` `GoalName`
FROM `Workout` `w`
INNER JOIN `WorkoutGoal` `wg`
	ON `w`.`WorkoutId` = `wg`.`WorkoutId`
INNER JOIN `Goal` `g`
	ON `wg`.`GoalId` = `g`.`GoalId`;

-- Select FirstName and LastName from Client.
-- Select ClientId and EmailAddress from Login.
-- Join the tables, but make Login optional.
-- 500 rows
-------------------- GOOD
SELECT
	`c`.`FirstName`,
    `c`.`LastName`,
    `l`.`ClientId`,
    `l`.`EmailAddress`
FROM `Client` `c`
LEFT OUTER JOIN `Login` `l`
	ON `c`.`ClientId` = `l`.`ClientId`;

-- Using the query above as a foundation, select Clients
-- who do _not_ have a Login.
-- 200 rows
-------------------- GOOD
SELECT
	`c`.`FirstName`,
    `c`.`LastName`,
    `l`.`ClientId`,
    `l`.`EmailAddress`
FROM `Client` `c`
LEFT OUTER JOIN `Login` `l`
	ON `c`.`ClientId` = `l`.`ClientId`
WHERE `l`.`ClientId` IS NULL;

-- Does the Client, Romeo Seaward, have a Login?
-- Decide using a single query.
-- nope :(
--------------------
SELECT
	CONCAT(`c`.`FirstName`, " ", `c`.`LastName`) `clientFirstLast`,
    `l`.`ClientId`,
    `l`.`EmailAddress`
FROM `Client` `c`
LEFT OUTER JOIN `Login` `l`
	ON `c`.`ClientId` = `l`.`ClientId`
WHERE `c`.`FirstName` = 'Romeo'
AND `c`.`LastName` = 'Seaward';

-- Select ExerciseCategory.Name and its parent ExerciseCategory's Name.
-- This requires a self-join.
-- 12 rows
-------------------- GOOD
SELECT
	`parent`.`Name` `MainCategory`,
    `child`.`Name` `SubCategory`
FROM `ExerciseCategory` `parent`
INNER JOIN `ExerciseCategory` `child`
	ON `parent`.`ExerciseCategoryId` = `child`.`ParentCategoryId`;
    
-- Rewrite the query above so that every ExerciseCategory.Name is
-- included, even if it doesn't have a parent.
-- 16 rows
-------------------- GOOD
SELECT
	`parent`.`Name` `MainCategory`,
    `child`.`Name` `SubCategory`
FROM `ExerciseCategory` `parent`
RIGHT OUTER JOIN `ExerciseCategory` `child`
	ON `parent`.`ExerciseCategoryId` = `child`.`ParentCategoryId`;
    
-- Are there Clients who are not signed up for a Workout?
-- 50 rows
-------------------- GOOD
SELECT
	CONCAT(`c`.`FirstName`, ' ', `c`.`LastName`) `ClientFullName`,
    IFNULL(`w`.`Name`, '[No Workout]') `WorkoutName`
FROM `Client` `c`
LEFT OUTER JOIN `ClientWorkout` `cw`
	ON `c`.`ClientId` = `cw`.`ClientId`
LEFT OUTER JOIN `Workout` `w`
	ON `cw`.`WorkoutId` = `w`.`WorkoutId`
WHERE `cw`.`WorkoutId` IS NULL;

SELECT
	CONCAT(`c`.`FirstName`, ' ', `c`.`LastName`) `ClientFullName`
FROM `Client` `c`
LEFT OUTER JOIN `ClientWorkout` `cw`
	ON `c`.`ClientId` = `cw`.`ClientId`
WHERE `cw`.`ClientId` IS NULL;

-- Which Beginner-Level Workouts satisfy at least one of Shell Creane's Goals?
-- Goals are associated to Clients through ClientGoal.
-- Goals are associated to Workouts through WorkoutGoal.
-- 6 rows, 4 unique rows
-------------------- GOOD
SELECT
	CONCAT(`c`.`FirstName`, ' ', `c`.`LastName`) `ClientFullName`,
    `g`.`Name` `GoalName`,
    `w`.`Name` `WorkoutName`
FROM `Client` `c`
INNER JOIN `ClientGoal` `cg`
	ON `c`.`ClientId` = `cg`.`ClientId`
INNER JOIN `Goal` `g`
	ON `cg`.`GoalId` = `g`.`GoalId`
INNER JOIN `WorkoutGoal` `wg`
	ON `g`.`GoalId` = `wg`.`GoalId`
INNER JOIN `Workout` `w`
	ON `wg`.`WorkoutId` = `w`.`WorkoutId`
WHERE `c`.`FirstName` = 'Shell'
	AND `c`.`LastName` = 'Creane'
    AND `w`.`LevelId` = 1;

-- Select all Workouts. 
-- Join to the Goal, 'Core Strength', but make it optional.
-- You may have to look up the GoalId before writing the main query.
-- If you filter on Goal.Name in a WHERE clause, Workouts will be excluded.
-- Why?
-- 26 Workouts, 4 Goals
-------------------- GOOD
SELECT
	`w`.`Name` `WorkoutName`,
    `g`.`Name` `GoalName`
FROM `Workout` `w`
LEFT OUTER JOIN `WorkoutGoal` `wg`
	ON `w`.`WorkoutId` = `wg`.`WorkoutId` AND `wg`.`GoalId` = 10
LEFT OUTER JOIN `Goal` `g`
	ON `wg`.`GoalId` = `g`.`GoalId`;
    
-- The relationship between Workouts and Exercises is... complicated.
-- Workout links to WorkoutDay (one day in a Workout routine)
-- which links to WorkoutDayExerciseInstance 
-- (Exercises can be repeated in a day so a bridge table is required) 
-- which links to ExerciseInstance 
-- (Exercises can be done with different weights, repetions,
-- laps, etc...) 
-- which finally links to Exercise.
-- Select Workout.Name and Exercise.Name for related Workouts and Exercises.
-------------------- GOOD
SELECT
	`w`.`Name` `WorkoutName`,
    `e`.`Name` `ExerciseName`
FROM `Workout` `w`
INNER JOIN `WorkoutDay` `wd`
	ON `w`.`WorkoutId` = `wd`.`WorkoutId`
INNER JOIN `WorkoutDayExerciseInstance` `wdei`
	ON `wd`.`WorkoutDayId` = `wdei`.`WorkoutDayId`
INNER JOIN `ExerciseInstance` `ei`
	ON `wdei`.`ExerciseInstanceId` = `ei`.`ExerciseInstanceId`
INNER JOIN `Exercise` `e`
	ON `ei`.`ExerciseId` = `e`.`ExerciseId`;
   
-- An ExerciseInstance is configured with ExerciseInstanceUnitValue.
-- It contains a Value and UnitId that links to Unit.
-- Example Unit/Value combos include 10 laps, 15 minutes, 200 pounds.
-- Select Exercise.Name, ExerciseInstanceUnitValue.Value, and Unit.Name
-- for the 'Plank' exercise. 
-- How many Planks are configured, which Units apply, and what 
-- are the configured Values?
-- 4 rows, 1 Unit, and 4 distinct Values
-------------------- GOOD
SELECT
	`e`.`Name` `ExerciseName`,
    `eiuv`.`Value`,
    `u`.`Name` `UnitName`
FROM `Exercise` `e`
INNER JOIN `ExerciseInstance` `ei`
	ON `e`.`ExerciseId` = `ei`.`ExerciseId`
INNER JOIN `ExerciseInstanceUnitValue` `eiuv`
	ON `ei`.`ExerciseInstanceId` = `eiuv`.`ExerciseInstanceId`
INNER JOIN `Unit` `u`
	ON `eiuv`.`UnitId` = `u`.`UnitId`
WHERE `e`.`Name` = 'Plank';