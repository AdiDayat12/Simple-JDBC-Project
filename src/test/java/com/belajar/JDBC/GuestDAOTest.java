package com.belajar.JDBC;

import com.belajar.JDBC.dao.GuestDAO;
import com.belajar.JDBC.model.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuestDAOTest {
    private GuestDAO newGuest;
    private Guest guest;

    @BeforeEach
    void setUp (){
        guest = new Guest();
        guest.setFirst_name("Adi");
        guest.setLast_name("linan");
        guest.setDateOfBirth(LocalDate.of(1990, 5, 23));
        guest.setEmail("adi@gmail.com");
        guest.setTel_no("1234567890123");
    }

    @Test
    void testGuest (){
        assertEquals("Adi", guest.getFirst_name());
        assertEquals("linan", guest.getLast_name());
        assertEquals(LocalDate.of(1990, 5, 23), guest.getDateOfBirth());
        assertEquals("adi@gmail.com", guest.getEmail());
        assertEquals("1234567890123", guest.getTel_no());
    }

    @Test
    void testObj (){
        assertNotNull(guest);
    }

    @Test
    void insertData (){
        newGuest = new GuestDAO();
        newGuest.addGuest(guest);
    }

}
