package com.geekster.DoctorsAppointmentApplication.repository;


import com.geekster.DoctorsAppointmentApplication.model.Appointment;
import com.geekster.DoctorsAppointmentApplication.model.AppointmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAppointmentRepo extends JpaRepository<Appointment, AppointmentKey> {

    Optional<Appointment> findById(Long appointmentId);


//    public String findByIdAppId(Long id);
}

