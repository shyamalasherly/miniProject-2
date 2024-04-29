package com.geekster.DoctorsAppointmentApplication.controller;

import com.geekster.DoctorsAppointmentApplication.dto.doctorSignin;
import com.geekster.DoctorsAppointmentApplication.model.Appointment;
import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    DoctorService docService;

    @PostMapping("/add")
    public Doctor addDoctor(@RequestBody Doctor doctor){
        System.out.println(doctor);
       return docService.addDoctor(doctor);
    }


    @PostMapping("/login")
    public Doctor loginDoctor(@RequestBody doctorSignin doctor){
        System.out.println(doctor);
        return docService.loginDoctor(doctor);
    }


    @GetMapping("/getdoc")
    public List<Doctor> getAllDoctors(){

        return docService.getAllDoctors();
    }

//
//    @GetMapping("{docId}/appointments")
//    ResponseEntity<List<Appointment>> getDocMyAppointments(@PathVariable Long docId)
//    {
//
//        List<Appointment> myAppointments = null;
//        HttpStatus status;
//        try
//        {
//            myAppointments = docService.getMyAppointments(docId);
//            //System.out.println(myAppointments);
//            if(myAppointments.isEmpty())
//            {
//                status = HttpStatus.NO_CONTENT;
//            }
//            else
//            {
//                status = HttpStatus.OK;
//            }
//        }
//        catch(Exception e)
//        {
//            System.out.println("The doc Id is not valid");
//            status = HttpStatus.BAD_REQUEST;
//
//        }

//        return new ResponseEntity<List<Appointment>>(myAppointments,status);
//
//    }


}
