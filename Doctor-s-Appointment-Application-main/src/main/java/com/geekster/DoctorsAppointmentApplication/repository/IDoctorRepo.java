package com.geekster.DoctorsAppointmentApplication.repository;


import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoctorRepo extends JpaRepository<Doctor, Long> {

    Doctor findFirstByDoctorEmail(String userEmail);

    Doctor findByDoctorId(Long docId);

//    List<Doctor> getDoctorBySpecialisation(String specialisation);
}
