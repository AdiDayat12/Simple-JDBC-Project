package com.belajar.JDBC.dao;

import com.belajar.JDBC.model.Booking;
import com.belajar.JDBC.util.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    Booking booking;
    Logger log = LoggerFactory.getLogger(BookingDAO.class);

    public List<Booking> getAllData () {
        List<Booking> bookingList = new ArrayList<>();
        String sql = "SELECT * FROM booking";
        try (Connection conn = ConnectionUtil.dataSource.getConnection();
             Statement statement = conn.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                booking = new Booking();
                booking.setBookingID(resultSet.getInt("id_booking"));
                booking.setGuestID(resultSet.getInt("id_guest"));
                booking.setRoomID(resultSet.getInt("id_room"));
                booking.setCheckIN(resultSet.getDate("check_in_date").toLocalDate());
                booking.setCheckOUT(resultSet.getDate("check_out_date").toLocalDate());
                bookingList.add(booking);
            }

        } catch (SQLException e){
            log.error("Error " , e);
        }
        return bookingList;
    }

    public int addBooking (Booking booking){
        String sql = "INSERT INTO booking (id_guest, id_room, check_in_date, check_out_date)" +
                "VALUES ?, ?, ? ?";
        int result = 0;
        try (Connection conn = ConnectionUtil.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, booking.getGuestID());
            ps.setInt(2, booking.getRoomID());
            ps.setDate(3, Date.valueOf(booking.getCheckIN()));
            ps.setDate(4, Date.valueOf(booking.getCheckOUT()));

            result = ps.executeUpdate();
        } catch (SQLException e){
            log.error("Error ", e);
        }
        return result;
    }

    public Booking selectBookingByGuestID (int guestID) {
        String sql = "SELECT * FROM booking WHERE id_guest = 'guestID' ";
        try (Connection conn = ConnectionUtil.dataSource.getConnection();
             Statement statement = conn.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                booking = new Booking();
                booking.setBookingID(resultSet.getInt("id_booking"));
                booking.setGuestID(resultSet.getInt("id_guest"));
                booking.setRoomID(resultSet.getInt("id_room"));
                booking.setCheckIN(resultSet.getDate("check_in_date").toLocalDate());
                booking.setCheckOUT(resultSet.getDate("check_out_date").toLocalDate());
            }

        } catch (SQLException e) {
            log.error("Error ", e);
        }
        return booking;
    }

}
