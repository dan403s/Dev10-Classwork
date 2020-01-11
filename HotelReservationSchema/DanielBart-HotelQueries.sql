USE HotelReservationSchema;

-- 1. Write a query that returns a list of reservations that end in July 2023, including the name of the guest, the room number(s), and the reservation dates.
-- QUERY GOES HERE --
SELECT
	CONCAT(g.FName, ' ', g.LName) GuestFullName,
    rr.RoomId,
    r.StartDate,
    r.EndDate
FROM Guest g
INNER JOIN Reservation r
	ON g.GuestId = r.GuestId
INNER JOIN ReservationRoom rr
	ON r.ReservationId = rr.ReservationId
WHERE r.EndDate BETWEEN '2023-07-01' AND '2023-07-31';

-- Query Results --
/*
GuestFullName, RoomId, StartDate, EndDate
'Daniel Bart', '205', '2023-06-28', '2023-07-02'
'Walter Holaway', '204', '2023-07-13', '2023-07-14'
'Wilfred Vise', '401', '2023-07-18', '2023-07-21'
'Bettyann Seery', '303', '2023-07-28', '2023-07-29'
*/

-- 2. Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name, the room number, and the dates of the reservation.
-- QUERY GOES HERE --
SELECT
	CONCAT(g.FName, ' ', g.LName) GuestFullName,
    ro.RoomId,
    re.StartDate,
    re.EndDate
From Guest g
INNER JOIN Reservation re
	ON g.GuestId = re.GuestId
INNER JOIN ReservationRoom rr
	ON re.ReservationId = rr.ReservationId
INNER JOIN Room ro
	ON rr.RoomId = ro.RoomId
INNER JOIN RoomAmenity ra
	ON ro.RoomId = ra.RoomId
INNER JOIN Amenity a
	ON ra.AmenityId = a.AmenityId
WHERE a.AmenityName = 'Jacuzzi';

-- Query Results --
/*
GuestFullName, RoomId, StartDate, EndDate
'Karie Yang', '201', '2023-03-06', '2023-03-07'
'Bettyann Seery', '203', '2023-02-05', '2023-02-10'
'Karie Yang', '203', '2023-09-13', '2023-09-15'
'Daniel Bart', '205', '2023-06-28', '2023-07-02'
'Wilfred Vise', '207', '2023-04-23', '2023-04-24'
'Walter Holaway', '301', '2023-04-09', '2023-04-13'
'Mack Simmer', '301', '2023-11-22', '2023-11-25'
'Bettyann Seery', '303', '2023-07-28', '2023-07-29'
'Duane Cullison', '305', '2023-02-22', '2023-02-24'
'Bettyann Seery', '305', '2023-08-30', '2023-09-01'
'Daniel Bart', '307', '2023-03-17', '2023-03-20'
*/


-- 3. Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the room(s) reserved, the starting date of the reservation, and how many people were included in the reservation. (Choose a guest's name from the existing data.)
-- QUERY GOES HERE --
SELECT
	CONCAT(g.FName, ' ', g.LName) GuestFullName,
    rr.RoomId,
    re.StartDate,
    (re.AdultNumber + re.ChildNumber) AS TotalPeople
From Guest g
INNER JOIN Reservation re
	ON g.GuestId = re.GuestId
INNER JOIN ReservationRoom rr
	ON re.ReservationId = rr.ReservationId
WHERE g.FName = 'Duane'
	AND g.LName = 'Cullison';

-- Query Results --
/*
GuestFullName, RoomId, StartDate, TotalPeople
'Duane Cullison', '305', '2023-02-22', '2'
'Duane Cullison', '401', '2023-11-22', '4'

*/

-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. The results should include all rooms, whether or not there is a reservation associated with the room.
-- QUERY GOES HERE --
SELECT
	ro.RoomId,
    rr.ReservationId,
    ro.BasePrice
FROM Room ro
LEFT OUTER JOIN ReservationRoom rr
	ON ro.RoomId = rr.RoomId;

-- Query Results --
/*
RoomId, ReservationId, BasePrice
'201', '4', '199.99'
'202', '7', '174.99'
'203', '2', '199.99'
'203', '21', '199.99'
'204', '16', '174.99'
'205', '15', '174.99'
'206', '12', '149.99'
'206', '23', '149.99'
'207', '10', '174.99'
'208', '13', '149.99'
'208', '20', '149.99'
'301', '9', '199.99'
'301', '24', '199.99'
'302', '6', '174.99'
'302', '25', '174.99'
'303', '18', '199.99'
'304', '14', '174.99'
'305', '3', '174.99'
'305', '19', '174.99'
'306', NULL, '149.99'
'307', '5', '174.99'
'308', '1', '149.99'
'401', '11', '399.99'
'401', '17', '399.99'
'401', '22', '399.99'
'402', NULL, '399.99'

*/

-- 5. Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023.
-- QUERY GOES HERE -- I AM A LITTLE CONFUSED SO I WROTE TWO, THE FIRST GETS ROOMS WITH 3+ GUESTS THAT WILL BE STAYING AT THAT TIME, THE SECOND GETS ROOMS THAT CAN ACCOMODATE 3+ GUESTS
SELECT
	rr.RoomId,
    SUM(re.AdultNumber + re.ChildNumber) GuestTotalForApril
FROM ReservationRoom rr
INNER JOIN Reservation re
	ON rr.ReservationId = re.ReservationId
WHERE (re.StartDate BETWEEN '2023-04-01' AND '2023-04-30') 
	OR (re.EndDate BETWEEN '2023-04-01' AND '2023-04-30')
GROUP BY rr.RoomId
HAVING SUM(re.AdultNumber + re.ChildNumber) >= 3;

-- Query Results --
/*
0 row(s) returned
*/


-- SECOND QUERY AS THE INSTRUCTIONS ARE UNCLEAR TO ME
SELECT
	rr.RoomId,
    COUNT(rr.ReservationId) TotalReservationsForApril
FROM ReservationRoom rr
INNER JOIN Reservation re
	ON rr.ReservationId = re.ReservationId
INNER JOIN Room ro
	ON rr.RoomId = ro.RoomId
INNER JOIN RoomType rt
	ON ro.RoomTypeId = rt.RoomTypeId
WHERE ((re.StartDate BETWEEN '2023-04-01' AND '2023-04-30') 
	OR (re.EndDate BETWEEN '2023-04-01' AND '2023-04-30'))
    AND rt.MaxOccupancy > 2
GROUP BY rr.RoomId;

/*
RoomId, TotalReservationsForApril
'301', '1'
*/

-- 6. Write a query that returns a list of all guest names and the number of reservations per guest, sorted starting with the guest with the most reservations and then by the guest's last name.
-- QUERY GOES HERE --
SELECT
	CONCAT(g.FName, ' ', g.LName) GuestFullName,
    COUNT(re.ReservationId) ReservationCount
FROM Guest g
LEFT OUTER JOIN Reservation re
	ON g.GuestId = re.GuestId
GROUP BY re.GuestId
ORDER BY COUNT(re.ReservationId) DESC, g.LName ASC;

-- Query Results --
/*
GuestFullName, ReservationCount
'Mack Simmer', '4'
'Bettyann Seery', '3'
'Daniel Bart', '2'
'Duane Cullison', '2'
'Walter Holaway', '2'
'Aurore Lipton', '2'
'Maritza Tilton', '2'
'Joleen Tison', '2'
'Wilfred Vise', '2'
'Karie Yang', '2'
'Zachery Luechtefeld', '1'

*/

-- 7. Write a query that displays the name, address, and phone number of a guest based on their phone number. (Choose a phone number from the existing data.)
-- QUERY GOES HERE --
SELECT
	CONCAT(g.FName, ' ', g.LName) GuestFullName,
    g.Address,
    g.City,
    g.State,
    g.Zip,
    g.Phone
FROM Guest g
WHERE g.Phone = '8144852615';

-- Query Results --
/*
GuestFullName, Address, City, State, Zip, Phone
'Zachery Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003', '8144852615'

*/