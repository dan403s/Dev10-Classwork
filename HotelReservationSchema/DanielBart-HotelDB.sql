-- Drops the existing database, if it exists, so that the script can rebuild a database with the same name.
DROP DATABASE IF EXISTS HotelReservationSchema;

CREATE DATABASE IF NOT EXISTS HotelReservationSchema;

USE HotelReservationSchema;

CREATE TABLE Guest (
    GuestId INT PRIMARY KEY AUTO_INCREMENT,
    FName VARCHAR(30) NOT NULL,
    LName VARCHAR(30) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    City VARCHAR(30) NOT NULL,
    State VARCHAR(2) NOT NULL,
    Zip VARCHAR(9) NOT NULL,
    Phone VARCHAR(11) NOT NULL
);

CREATE TABLE Amenity (
	AmenityId INT PRIMARY KEY AUTO_INCREMENT,
    AmenityName VARCHAR(30) NOT NULL
);

CREATE TABLE RoomType (
	RoomTypeId INT PRIMARY KEY AUTO_INCREMENT,
    RoomTypeName VARCHAR(10) NOT NULL,
    StdOccupancy INT NOT NULL,
    MaxOccupancy INT NOT NULL,
    ExtraPersonCost DECIMAL(5,2) NULL
);

CREATE TABLE Reservation (
	ReservationId INT PRIMARY KEY AUTO_INCREMENT,
    GuestId INT NOT NULL,
    AdultNumber INT NOT NULL,
    ChildNumber INT NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    TotalRoomCost Decimal(7,2) NOT NULL,
    FOREIGN KEY fk_Reservation_Guest (GuestId)
		REFERENCES Guest (GuestId)
);

CREATE TABLE Room (
	RoomId VARCHAR(4) PRIMARY KEY,
    RoomTypeId INT NOT NULL,
    ADAAccessibility TINYINT(1) NOT NULL,
    BasePrice DECIMAL(6,2) NOT NULL,
    FOREIGN KEY fk_Room_RoomType (RoomTypeId)
		REFERENCES RoomType (RoomTypeId)
);

CREATE TABLE RoomAmenity (
	RoomId VARCHAR(4) NOT NULL,
    AmenityId INT NOT NULL,
    PRIMARY KEY pk_RoomAmenity (RoomId, AmenityId),
    FOREIGN KEY fk_RoomAmenity_Room (RoomId)
		REFERENCES Room (RoomId),
	FOREIGN KEY fk_RoomAmenity_Amenity (AmenityId)
		REFERENCES Amenity (AmenityId)
);

CREATE TABLE ReservationRoom (
	ReservationId INT NOT NULL,
    RoomId VARCHAR(4) NOT NULL,
    PRIMARY KEY pk_ReservationRoom (ReservationId, RoomId),
    FOREIGN KEY fk_ReservationRoom_Reservation (ReservationId)
		REFERENCES Reservation (ReservationId),
	FOREIGN KEY fk_ReservationRoom_Room (RoomId)
		REFERENCES Room (RoomId)
);