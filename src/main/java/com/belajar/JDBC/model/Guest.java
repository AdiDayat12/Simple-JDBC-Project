package com.belajar.JDBC.model;
import lombok.*;

import java.time.LocalDate;

@Data
public class Guest {
    private String first_name;
    private String last_name;
    private LocalDate dateOfBirth;
    private String email;
    private String tel_no;
}
