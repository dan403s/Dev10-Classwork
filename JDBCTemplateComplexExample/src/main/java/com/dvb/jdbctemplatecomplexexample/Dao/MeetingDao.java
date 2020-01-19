/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.jdbctemplatecomplexexample.Dao;

import com.dvb.jdbctemplatecomplexexample.DTO.Employee;
import com.dvb.jdbctemplatecomplexexample.DTO.Meeting;
import com.dvb.jdbctemplatecomplexexample.DTO.Room;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface MeetingDao {
    List<Meeting> getAllMeetings();
    Meeting getMeetingByid(int id);
    Meeting addMeeting(Meeting meeting);
    void updateMeeting(Meeting meeting);
    void deleteMeetingById(int id);
    
    List<Meeting> getMeetingsForRoom(Room room);
    List<Meeting> getMeetingsForEmployee(Employee employee);
}
