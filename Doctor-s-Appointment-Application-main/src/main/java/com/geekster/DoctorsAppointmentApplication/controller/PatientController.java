package com.geekster.DoctorsAppointmentApplication.controller;

import com.geekster.DoctorsAppointmentApplication.dto.SignInInput;
import com.geekster.DoctorsAppointmentApplication.dto.SignInOutput;
import com.geekster.DoctorsAppointmentApplication.dto.SignUpInput;
import com.geekster.DoctorsAppointmentApplication.dto.SignUpOutput;
import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.model.Patient;
import com.geekster.DoctorsAppointmentApplication.service.AuthenticationService;
import com.geekster.DoctorsAppointmentApplication.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AuthenticationService authService;

    @PostMapping("/signup")
    public Patient signUp(@RequestBody SignUpInput signUpDto){
        return patientService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@RequestBody SignInInput signInDto){
        return patientService.signIn(signInDto);
    }


    @GetMapping("/getPatients")
    public List<Patient> getPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String key) throws LoginException {
        String msg=patientService.logOutFromAccount(key);
        return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestParam String key){
//        return patientService.logOutFromAccount(key);
//    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam String userEmail , @RequestParam String token){
        HttpStatus status;
        List<Doctor> allDoctors = null;

        //authenticate

        if(authService.authenticate(userEmail,token))
        {

            allDoctors =  patientService.getAllDoctors();
            status = HttpStatus.OK;
        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }



        return new ResponseEntity<List<Doctor>>(allDoctors, status);


    }


}