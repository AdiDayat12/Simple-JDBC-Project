package com.belajar.JDBC;

import com.belajar.JDBC.dao.RoomDAO;
import com.belajar.JDBC.model.Room;
import com.belajar.JDBC.model.RoomStatus;
import com.belajar.JDBC.model.RoomType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

public class RoomDAOTest extends AbstractConnection {
    Room room;
    @BeforeEach
    void setUp () throws SQLException {
        getConnection();
    }
    @AfterEach
    void tearDown () throws SQLException {
        super.tearDown();
    }

    @Test
    void getAllData (){
        RoomDAO roomDAO = new RoomDAO();
        List<Room> result = roomDAO.getAllDatas();
        printAll(result);
    }

    void printAll (List<Room> rooms){
        rooms.forEach(System.out::println);
    }

    @Test
    void addRoomTest (){
        RoomDAO roomDAO = new RoomDAO();
        room = new Room();
        room.setType(RoomType.NORMAL);
        room.setStatus(RoomStatus.BOOKED);

        int result = roomDAO.addRoom(room);
        assertEquals(1, result);
    }
}
