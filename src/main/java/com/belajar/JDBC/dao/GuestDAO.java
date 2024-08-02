package com.belajar.JDBC.dao;

import com.belajar.JDBC.model.Guest;
import com.belajar.JDBC.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuestDAO {
    public void addGuest (Guest guest){
        String sql = """
                INSERT INTO guest (first_name, last_name, date_of_birth, email, tel_no) VALUES
                (?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConnectionUtil.dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, guest.getFirst_name());
            preparedStatement.setString(2, guest.getLast_name());
            preparedStatement.setDate(3, Date.valueOf(guest.getDateOfBirth()));
            preparedStatement.setString(4, guest.getEmail());
            preparedStatement.setString(5, guest.getTel_no());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
