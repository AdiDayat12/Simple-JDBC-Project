package com.belajar.JDBC;

import com.belajar.JDBC.model.Guest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class GuestTest {
    @Test
    void testGuest (){
        Guest guest = new Guest();
        guest.setFirst_name("Adi");
        guest.setLast_name("linan");
        guest.setDateOfBirth(LocalDate.of(1990, 5, 23));
        guest.setEmail("adi@gmail.com");
        guest.setTel_no("1234567890123");

        assertEquals("Adi", guest.getFirst_name());
        assertEquals("linan", guest.getLast_name());
        assertEquals(LocalDate.of(1990, 5, 23), guest.getDateOfBirth());
        assertEquals("adi@gmail.com", guest.getEmail());
        assertEquals("1234567890123", guest.getTel_no());
    }
}
