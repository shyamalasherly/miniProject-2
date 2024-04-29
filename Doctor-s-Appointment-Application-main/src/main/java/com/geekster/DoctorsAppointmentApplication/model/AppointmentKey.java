package com.geekster.DoctorsAppointmentApplication.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;
import java.time.LocalDateTime;

@Embeddable
public class AppointmentKey {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long appointmentId;

    public String time;


}