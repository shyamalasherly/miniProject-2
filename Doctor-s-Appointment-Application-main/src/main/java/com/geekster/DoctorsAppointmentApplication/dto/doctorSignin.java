package com.geekster.DoctorsAppointmentApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class doctorSignin {

    private String doctorEmail;
    private String password;

}