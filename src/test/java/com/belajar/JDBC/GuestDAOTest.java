package com.belajar.JDBC;

import com.belajar.JDBC.dao.GuestDAO;
import com.belajar.JDBC.model.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuestDAOTest {

    Logger log  = LoggerFactory.getLogger(GuestDAOTest.class);
    private Guest guest;
    private GuestDAO guestDAO;

    @BeforeEach
    void setUp (){
        guestDAO = new GuestDAO();
    }
    @Test
    void insertData (){
        assertNotNull(guest);
        guestDAO.addGuest(guest);
    }

    @Test
    void searchData (){
        List<Guest> result = guestDAO.searchGuest("lin");
        printResults(result);
    }

    @Test
    void getAllData (){
        List<Guest> result = guestDAO.getAllGuests();
        printResults(result);
    }

    @Test
    void updateFirstName (){
        int hasil = guestDAO.updateGuest("first_name",  "Muhtar", "ADI");
        assertEquals(2, hasil);
    }

    private void printResults(List<Guest> guests) {
        System.out.println("====== Result ======");
        guests.forEach(v -> System.out.println(v.getFirst_name() + " " + v.getLast_name() + " " + v.getDateOfBirth() + " - "  +  v.getEmail() + " - " + v.getTel_no()));
    }

    @Test
    void updateDateTest (){
        int hasil = guestDAO.updateGuest("date_of_birth", "1990-05-23", "2000-12-12");
        assertEquals(2, hasil);
    }

    @Test
    void deleteTest (){
        int hasil = guestDAO.deleteGuest("ADI");
        assertEquals(2, hasil);
    }
}
