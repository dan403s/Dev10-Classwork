/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.jdbctemplatecomplexexample.Dao;

import com.dvb.jdbctemplatecomplexexample.DTO.Room;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface RoomDao {
    List<Room> getAllRooms();
    Room getRoomById(int id);
    Room addRoom(Room room);
    void updateRoom(Room room);
    void deleteRoomById(int id);
}
