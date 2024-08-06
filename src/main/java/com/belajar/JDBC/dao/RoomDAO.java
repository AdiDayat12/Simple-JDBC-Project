package com.belajar.JDBC.dao;


import com.belajar.JDBC.model.Room;
import com.belajar.JDBC.model.RoomStatus;
import com.belajar.JDBC.model.RoomType;
import com.belajar.JDBC.util.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private static final Logger log = LoggerFactory.getLogger(RoomDAO.class);

    Room room;
    public List<Room> getAllDatas (){
        List<Room> roomList = new ArrayList<>();

        String sql = "SELECT * FROM room";

        try (Connection conn = ConnectionUtil.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Room room = new Room();
                RoomType type = RoomType.valueOf(resultSet.getString("type").toUpperCase());
                RoomStatus status = RoomStatus.valueOf(resultSet.getString("status").toUpperCase().replace(" ", "_"));
                room.setId(resultSet.getInt("id_room"));
                room.setType(type);
                room.setStatus(status);
                roomList.add(room);
            }
        } catch (SQLException e){
            log.error("Error", e);
        }
        return roomList;
    }

    public int addRoom (Room room){
        String sql = "INSERT INTO room (type, status) VALUES (?, ?)";

        int result = 0;
        try (Connection conn = ConnectionUtil.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, room.getType().toString().toLowerCase());
            ps.setString(2, room.getStatus().toString().toLowerCase());

            result = ps.executeUpdate();
        } catch (SQLException e){
            log.error("Error ", e);
        }
        return result;
    }


}
