package com.geekster.DoctorsAppointmentApplication.service;

import com.geekster.DoctorsAppointmentApplication.dto.SignInInput;
import com.geekster.DoctorsAppointmentApplication.dto.SignInOutput;
import com.geekster.DoctorsAppointmentApplication.dto.SignUpInput;
import com.geekster.DoctorsAppointmentApplication.dto.SignUpOutput;
import com.geekster.DoctorsAppointmentApplication.model.AppointmentKey;
import com.geekster.DoctorsAppointmentApplication.model.AuthenticationToken;
import com.geekster.DoctorsAppointmentApplication.model.Doctor;
import com.geekster.DoctorsAppointmentApplication.model.Patient;
import com.geekster.DoctorsAppointmentApplication.repository.IPatientRepo;
import com.geekster.DoctorsAppointmentApplication.repository.ITokenRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.xml.bind.DatatypeConverter;
import javax.security.auth.login.LoginException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    ITokenRepo tokenRepo;

    @Autowired
    IPatientRepo patientRepo;

    @Autowired
    AuthenticationService tokenService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    AppointmentService appointmentService;

    public Patient signUp(SignUpInput signUpDto) {

        System.out.println("get method");
        //check if user exists or not based on email
        Patient patient = patientRepo.findFirstByPatientEmail(signUpDto.getUserEmail());

        if(patient != null)
        {
            throw new IllegalStateException("Patient already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpDto.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        patient = new Patient(signUpDto.getUserFirstName(), signUpDto.getUserLastName(),
                signUpDto.getUserEmail(), encryptedPassword , signUpDto.getUserContact());

//        patientRepo.save(patient);

        return patientRepo.save(patient);
//                new SignUpOutput("Patient registered","Patient created successfully");

    }

    public List<Patient> getAllPatients() {

        return patientRepo.findAll();
    }

   public String logOutFromAccount(String key){

        AuthenticationToken  validUserSession=tokenRepo.findFirstByToken(key);

        System.out.println(validUserSession);

        if(validUserSession==null) {
           return "User not logged in with this username.";
        }
        tokenRepo.delete(validUserSession);
        return "Logged out successfully.";
    }


    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

//        String hash = DatatypeConverter.printHexBinary(digested);
        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }

    public SignInOutput signIn(SignInInput signInDto) {
        //check if user exists or not based on email
        Patient patient = patientRepo.findFirstByPatientEmail(signInDto.getPatientEmail());

        if(patient == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPatientPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(patient.getPatientPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

//        AuthenticationToken token = new AuthenticationToken(patient);
//
//        tokenService.saveToken(token);

        return new SignInOutput("Authentication Successfull !!!", patient.getPatientEmail(),patient.getPatientFirstName(),patient.getPatientId().toString());

    }

    public List<Doctor> getAllDoctors() {

        return doctorService.getAllDoctors();

    }

    public void cancelAppointment(AppointmentKey key) {

        appointmentService.cancelAppointment(key);

    }
}