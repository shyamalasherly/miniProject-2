package com.geekster.DoctorsAppointmentApplication.repository;

import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPrescriptionRepo extends JpaRepository<Prescription, Long> {



    Prescription findByPrescriptionId(Long prescriptionId);

}
