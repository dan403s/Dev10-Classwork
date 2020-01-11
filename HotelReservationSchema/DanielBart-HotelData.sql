USE HotelReservationSchema;

INSERT INTO Guest (GuestId, FName, LName, Address, City, State, Zip, Phone) VALUES
    (1, 'Daniel', 'Bart', '123 Fake Lane', 'Charlotte' , 'NC', '28205', '1234567890'),
	(2, 'Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs' , 'IA', '51501', '2915530508'),
	(3, 'Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla' , 'AK', '99654', '4782779632'),
	(4, 'Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen' , 'TX', '78552', '3084940198'),
	(5, 'Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford' , 'NJ', '08096', '2147300298'),
	(6, 'Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw' , 'MI', '48601', '3775070974'),
	(7, 'Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada' , 'CO', '80003', '8144852615'),
	(8, 'Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion' , 'IL', '60099', '2794910960'),
	(9, 'Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland' , 'RI', '02864', '4463966785'),
	(10, 'Wilfred', 'Vise', '77 West Surrey Street', 'Oswego' , 'NY', '13126', '8347271001'),
	(11, 'Maritza', 'Tilton', '939 Linda Rd.', 'Burke' , 'VA', '22015', '4463516860'),
	(12, 'Joleen', 'Tison', '87 Queen St.', 'Drexel Hill' , 'PA', '19026', '2318932755');

    
INSERT INTO Amenity (AmenityId, AmenityName) VALUES
	(1, 'Microwave'),
    (2, 'Jacuzzi'),
    (3, 'Refrigerator'),
    (4, 'Oven');
    
INSERT INTO RoomType (RoomTypeId, RoomTypeName, StdOccupancy, MaxOccupancy, ExtraPersonCost) VALUES
	(1, 'Single', 2, 2, null),
    (2, 'Double', 2, 4, 10.00),
    (3, 'Suite', 3, 8, 20.00);
    
INSERT INTO Reservation (ReservationId, GuestId, AdultNumber, ChildNumber, StartDate, EndDate, TotalRoomCost) VALUES
	(1, 2, 1, 0, '2023-02-02', '2023-02-04', 299.98),
	(2, 3, 2, 1, '2023-02-05', '2023-02-10', 999.95),
	(3, 4, 2, 0, '2023-02-22', '2023-02-24', 349.98),
	(4, 5, 2, 2, '2023-03-06', '2023-03-07', 199.99),
	(5, 1, 1, 1, '2023-03-17', '2023-03-20', 524.97),
	(6, 6, 3, 0, '2023-03-18', '2023-03-23', 924.95),
	(7, 7, 2, 2, '2023-03-29', '2023-03-31', 349.98),
	(8, 8, 2, 0, '2023-03-31', '2023-04-05', 874.95),
	(9, 9, 1, 0, '2023-04-09', '2023-04-13', 799.96),
	(10, 10, 1, 1, '2023-04-23', '2023-04-24', 174.99),
	(11, 11, 2, 4, '2023-05-30', '2023-06-02', 1199.97),
	(12, 12, 2, 0, '2023-06-10', '2023-06-14', 599.96),
	(13, 12, 1, 0, '2023-06-10', '2023-06-14', 599.96),
	(14, 6, 3, 0, '2023-06-17', '2023-06-18', 184.99),
	(15, 1, 2, 0, '2023-06-28', '2023-07-02', 699.96),
	(16, 9, 3, 1, '2023-07-13', '2023-07-14', 184.99),
	(17, 10, 4, 2, '2023-07-18', '2023-07-21', 1259.97),
	(18, 3, 2, 1, '2023-07-28', '2023-07-29', 199.99),
	(19, 3, 1, 0, '2023-08-30', '2023-09-01', 349.98),
	(20, 2, 2, 0, '2023-09-16', '2023-09-17', 149.99),
	(21, 5, 2, 2, '2023-09-13', '2023-09-15', 399.98),
	(22, 4, 2, 2, '2023-11-22', '2023-11-25', 1199.97),
	(23, 2, 2, 0, '2023-11-22', '2023-11-25', 449.97),
	(24, 2, 2, 2, '2023-11-22', '2023-11-25', 599.97),
	(25, 11, 2, 0, '2023-12-24', '2023-12-28', 699.96);
    
INSERT INTO Room (RoomId, RoomTypeId, ADAAccessibility, BasePrice) VALUES
	('201', 2, 0, 199.99),
	('202', 2, 1, 174.99),
	('203', 2, 0, 199.99),
	('204', 2, 1, 174.99),
	('205', 1, 0, 174.99),
	('206', 1, 1, 149.99),
	('207', 1, 0, 174.99),
	('208', 1, 1, 149.99),
	('301', 2, 0, 199.99),
	('302', 2, 1, 174.99),
	('303', 2, 0, 199.99),
	('304', 2, 1, 174.99),
	('305', 1, 0, 174.99),
	('306', 1, 1, 149.99),
	('307', 1, 0, 174.99),
	('308', 1, 1, 149.99),
	('401', 3, 1, 399.99),
	('402', 3, 1, 399.99);

INSERT INTO RoomAmenity (RoomId, AmenityId) VALUES
	('201', 1),
    ('201', 2),
    ('202', 3),
    ('203', 1),
    ('203', 2),
    ('204', 3),
    ('205', 1),
    ('205', 2),
    ('205', 3),
    ('206', 1),
    ('206', 3),
    ('207', 1),
    ('207', 2),
    ('207', 3),
    ('208', 1),
    ('208', 3),
    ('301', 1),
    ('301', 2),
    ('302', 3),
    ('303', 1),
    ('303', 2),
    ('304', 3),
    ('305', 1),
    ('305', 2),
    ('305', 3),
    ('306', 1),
    ('306', 3),
    ('307', 1),
    ('307', 2),
    ('307', 3),
    ('308', 1),
    ('308', 3),
    ('401', 1),
    ('401', 3),
    ('401', 4),
    ('402', 1),
    ('402', 3),
    ('402', 4);
    
INSERT INTO ReservationRoom (ReservationId, RoomId) VALUES
	(1, '308'),
	(2, '203'),
	(3, '305'),
	(4, '201'),
	(5, '307'),
	(6, '302'),
	(7, '202'),
	(8, '304'),
	(9, '301'),
	(10, '207'),
	(11, '401'),
	(12, '206'),
	(13, '208'),
	(14, '304'),
	(15, '205'),
	(16, '204'),
	(17, '401'),
	(18, '303'),
	(19, '305'),
	(20, '208'),
	(21, '203'),
	(22, '401'),
	(23, '206'),
	(24, '301'),
	(25, '302');
    
-- Just me checking my work before and after deleting Jeremiah Pendergrass
-- Room Data
SELECT
	r.RoomId,
    rt.RoomTypeName,
    a.AmenityName,
    r.ADAAccessibility,
    rt.StdOccupancy,
    rt.MaxOccupancy,
    r.BasePrice,
    rt.ExtraPersonCost
From Room r
INNER JOIN RoomAmenity ra
	ON r.RoomId = ra.RoomId
INNER JOIN Amenity a
	ON ra.AmenityId = a.AmenityId
INNER JOIN RoomType rt
	ON r.RoomTypeId = rt.RoomTypeId
ORDER BY r.RoomId;

-- Guests
SELECT *
FROM Guest;

-- Reservations
SELECT
	g.GuestId,
	re.ReservationId,
	ro.RoomId,
    CONCAT(g.FName, ' ', g.LName) GuestFullName,
    re.AdultNumber,
    re.ChildNumber,
    re.StartDate,
    re.EndDate,
    re.TotalRoomCost
FROM Room ro
INNER JOIN ReservationRoom rr
	ON ro.RoomId = rr.RoomId
INNER JOIN Reservation re
	ON rr.ReservationId = re.ReservationId
INNER JOIN Guest g
	ON re.GuestId = g.GuestId
ORDER BY re.StartDate;

-- Delete Jeremiah Pendergrass and his reservations from the database
DELETE FROM ReservationRoom
	WHERE ReservationId = 8
		AND RoomId = '304';
        
DELETE FROM Reservation
	WHERE ReservationId = 8;
    
DELETE FROM Guest
	WHERE GuestId = 8;