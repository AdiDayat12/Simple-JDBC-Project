package com.belajar.JDBC.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Booking {
    private int bookingID;
    private int guestID;
    private int roomID;
    private LocalDate checkIN;
    private LocalDate checkOUT;


    @Override
    public String toString (){
        return "Booking ID : " + this.bookingID + " -- Guest ID : " + this.guestID + " -- Room ID : " +
                this.roomID + " -- Check In Date : " + this.checkIN + " -- Check Out Date : " + this.checkOUT;
    }
}


