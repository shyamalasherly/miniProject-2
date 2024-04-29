package com.geekster.DoctorsAppointmentApplication.service;

import com.geekster.DoctorsAppointmentApplication.model.Appointment;
import com.geekster.DoctorsAppointmentApplication.model.AppointmentKey;
import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.repository.IAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    IAppointmentRepo appointmentRepo;

    public List<Appointment> getallAppointments() {
        return appointmentRepo.findAll();
    }



    public Appointment updatePrescription(AppointmentKey key, String newPrescription) {
        Optional<Appointment> appointmentOptional = appointmentRepo.findById(key);

        System.out.println("appointmentOptional"+appointmentOptional);
        System.out.println(appointmentOptional);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            appointment.setPrescription(newPrescription);
            return appointmentRepo.save(appointment);
        } else {
            throw new IllegalArgumentException("Appointment not found for key: " + key);
        }
    }


    public Appointment bookAppointment(Appointment appointment) {

        Optional<Appointment> myAppointment = appointmentRepo.findById(appointment.getId());
        if(myAppointment.isEmpty()){
            System.out.println("Appointment Doctor Id:");
            System.out.println(appointment.getDoctor());
            return appointmentRepo.save(appointment);

        }else{
            throw new IllegalStateException("Appointment with id already present");
        }

    }

    public void cancelAppointment(AppointmentKey key) {
        appointmentRepo.deleteById(key);
    }
}
