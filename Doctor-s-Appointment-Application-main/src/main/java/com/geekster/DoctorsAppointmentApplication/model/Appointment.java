package com.geekster.DoctorsAppointmentApplication.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Appointment.class,property = "id")
public class Appointment {

    @Id
    @EmbeddedId
    private AppointmentKey id;

    private String appointmentDate;

    private String appointmentTime;

    private String patientName;

    private String doctorName;

    @OneToOne
    private Doctor doctor;

    @OneToOne
    private Patient patient;

    private String prescription;

}