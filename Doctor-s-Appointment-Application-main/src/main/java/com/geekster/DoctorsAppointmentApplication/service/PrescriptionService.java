package com.geekster.DoctorsAppointmentApplication.service;

import com.geekster.DoctorsAppointmentApplication.model.Prescription;
import com.geekster.DoctorsAppointmentApplication.repository.IPrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    IPrescriptionRepo prescriptionRepo;

    @Autowired
    DoctorService doctorService;

    public void addPrescription(Prescription prescription) {
        prescriptionRepo.save(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepo.findAll();
    }

    public Prescription getPrescriptionById(Long id) {
        return prescriptionRepo.findByPrescriptionId(id);
    }

}
