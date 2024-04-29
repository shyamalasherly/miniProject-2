package com.geekster.DoctorsAppointmentApplication.controller;


import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.model.Prescription;
import com.geekster.DoctorsAppointmentApplication.service.AuthenticationService;
import com.geekster.DoctorsAppointmentApplication.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("prescription")
@CrossOrigin("*")
public class PrescriptionController {


    PrescriptionService prescriptionService;

    @PostMapping("/add")
    void addDoctor(@RequestBody Prescription prescription){
        prescriptionService.addPrescription(prescription);
    }

}
