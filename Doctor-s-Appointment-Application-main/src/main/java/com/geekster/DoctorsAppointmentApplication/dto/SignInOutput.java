package com.geekster.DoctorsAppointmentApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInOutput {

    private String status;
    private String patientEmail;
    private String Name;
    private String patientId;

}