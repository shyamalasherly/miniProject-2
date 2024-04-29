package com.geekster.DoctorsAppointmentApplication.service;

import com.geekster.DoctorsAppointmentApplication.dto.doctorSignin;
import com.geekster.DoctorsAppointmentApplication.model.Appointment;
import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.model.Patient;
import com.geekster.DoctorsAppointmentApplication.repository.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    IDoctorRepo doctorRepo;

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public Doctor loginDoctor(doctorSignin doctor) {

       Doctor doctors = doctorRepo.findFirstByDoctorEmail(doctor.getDoctorEmail());

        boolean isPasswordValid = (doctor.getPassword()).equals(doctors.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("Invalid Data!!!!");
        }

        return doctors;

    }

    public Doctor addDoctor(Doctor doctor) {
      System.out.println("doctor added");
      return doctorRepo.save(doctor);

    }


//    public List<Appointment> getMyAppointments(Long docId) {
//
//        Doctor myDoc = doctorRepo.findByDoctorId(docId);
//
//        if(myDoc == null)
//        {
//            throw new IllegalStateException("The doctor does not exist");
//        }
//
//        return myDoc.getAppointments();
//    }
}