package com.belajar.JDBC.dao;

import com.belajar.JDBC.model.Guest;
import com.belajar.JDBC.util.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuestDAO {
    Logger log = LoggerFactory.getLogger(GuestDAO.class);

    public void addGuest (Guest guest){
        String sql = """
                INSERT INTO guest (first_name, last_name, date_of_birth, email, tel_no) VALUES (?, ?, ?, ?, ?);
                """;

        try (Connection conn = ConnectionUtil.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, guest.getFirst_name());
            ps.setString(2, guest.getLast_name());
            ps.setDate(3, Date.valueOf(guest.getDateOfBirth()));
            ps.setString(4, guest.getEmail());
            ps.setString(5, guest.getTel_no());

            ps.executeUpdate();
        }catch (SQLException e){
            log.error("Error executing addGuest", e);
            throw new RuntimeException(e);
        }
    }
    public List<Guest> searchGuest (String keyword){
        List<Guest> guestList = new ArrayList<>();
        String sql = """
                SELECT * FROM guest WHERE first_name ILIKE ? OR last_name ILIKE ? OR email ILIKE ?\s
                \s""";

        try (Connection conn = ConnectionUtil.dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            keyword = "%" + keyword + "%";

            ps.setString(1, keyword);
            ps.setString(2, keyword);
            ps.setString(3, keyword);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                Guest guest = new Guest();
                guest.setFirst_name(resultSet.getString("first_name"));
                guest.setLast_name(resultSet.getString("last_name"));
                guest.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                guest.setEmail(resultSet.getString("email"));
                guest.setTel_no(resultSet.getString("tel_no"));

                guestList.add(guest);
            }

        } catch (SQLException e){
            log.error("Error executing searchGuest", e);
            throw new RuntimeException(e);
        }
        return guestList;
    }

    public List<Guest> getAllGuests (){
        List<Guest> guestList = new ArrayList<>();
        String sql = """
                SELECT * FROM guest""";


        try (Connection conn = ConnectionUtil.dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Guest guest = new Guest();
                guest.setFirst_name(resultSet.getString("first_name"));
                guest.setLast_name(resultSet.getString("last_name"));
                guest.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                guest.setEmail(resultSet.getString("email"));
                guest.setTel_no(resultSet.getString("tel_no"));

                guestList.add(guest);
            }
        } catch (SQLException e){
            log.error("Error ", e);
            throw new RuntimeException(e);
        }
        return guestList;
    }

    public int updateGuest(String column, String prevData, String newData){
        List<String> validColumns = Arrays.asList("first_name", "date_of_birth", "last_name", "email", "tel_no");
        if (!validColumns.contains(column)){
            log.error("Error ", new Throwable());
        }
        if (column.equals("date_of_birth")){
            return updateDate(prevData, newData);
        }
        String sql = "UPDATE guest SET " + column + " = ? WHERE " + column + " = ?";
        int result = 0;
        try (Connection conn = ConnectionUtil.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, newData);
            ps.setString(2, prevData);
            
            result = ps.executeUpdate();
        } catch (SQLException e){
            log.error("Error", new Throwable());
        }
        return result;
    }

    private int updateDate(String prevDate, String newDate) {
        Date date1 = parseToDate(prevDate);
        Date date2 = parseToDate(newDate);

        String sql = "UPDATE guest SET date_of_birth = ? WHERE date_of_birth = ?";
        int result = 0;
        try (Connection conn = ConnectionUtil.dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, date2);
            ps.setDate(2, date1);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Error updating date", e);
        }
        return result;
    }

    private Date parseToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new java.sql.Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }
    }

    public int deleteGuest (String keyword){
        String sql = """
                DELETE FROM guest WHERE first_name = ? OR last_name = ?""";
        int result = 0;
        try (Connection conn = ConnectionUtil.dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            result = ps.executeUpdate();
        }catch (SQLException e){
            log.error("Error ", new Throwable());
        }
        return result;
    }
}