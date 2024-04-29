package com.geekster.DoctorsAppointmentApplication.controller;
import com.geekster.DoctorsAppointmentApplication.dto.prescriptionUpdate;
import com.geekster.DoctorsAppointmentApplication.model.Appointment;
import com.geekster.DoctorsAppointmentApplication.model.AppointmentKey;
import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.service.AppointmentService;
import com.geekster.DoctorsAppointmentApplication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("appointment")
public class AppointmentController  {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AuthenticationService authService;


    @PutMapping("/prescribe")
    public ResponseEntity<String> updatePrescription(@RequestBody prescriptionUpdate prescription) {
        try {
            appointmentService.updatePrescription(prescription.key, prescription.prescription);
            return ResponseEntity.ok("Prescription updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found for key: " + prescription.key);
        }
    }


    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment){
       System.out.println(appointment);
        String msg=null;
        HttpStatus status;
//        try{
//            appointmentService.bookAppointment(appointment);
//            msg = " Appointment booked successfully";
//            status = HttpStatus.OK;
//        }catch(Exception e){
//            msg = "Book Another Appointment as this appointment is already booked";
//            status = HttpStatus.BAD_REQUEST;
//        }
      return appointmentService.bookAppointment(appointment);
//        msg = " Appointment booked successfully";
//        status = HttpStatus.OK;
//        return new ResponseEntity<String>(msg, status);
    }


    @GetMapping("/getappointment")
    public List<Appointment> getAllappointment(){
        return appointmentService.getallAppointments();
    }

    @DeleteMapping("appointment")
    ResponseEntity<Void> cancelAppointment(@RequestParam String userEmail, @RequestParam String token, @RequestBody AppointmentKey key)
    {

        HttpStatus status;
        if(authService.authenticate(userEmail,token))
        {
            appointmentService.cancelAppointment(key);
            status = HttpStatus.OK;
        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<Void>(status);

    }



}
