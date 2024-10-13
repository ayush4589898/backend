package com.example.demo.service;

import com.example.demo.Model.PatientData;
import com.example.demo.Model.PatientDetail;
import com.example.demo.repo.PatientDataRepo;
import com.example.demo.repo.PatientDetailRepository;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientDetailRepository patientDRepository;
    @Autowired
    private PatientDataRepo pdr;

    public void savePatient(PatientDetail patientDetail) {
        patientDRepository.save(patientDetail);
        return;
    }

    public PatientDetail validate(String name, String email) {
        // Check if a patient with the given email exists
        PatientDetail existingPatient = patientDRepository.findByEmail(email);
        
        if (existingPatient != null && existingPatient.getName().equals(name)) {
            return existingPatient;  // Return the existing patient if the name matches
        } else {
            return null;  // Return null if no patient found or name doesn't match
        }
    
        
    }

    public List<PatientData> getPd(String email){

        List<Long> ans=patientDRepository.findUniquePatientIdsByEmail(email);
        return  pdr.findByPatientIds(ans);


        
    } 
  

public ResponseEntity<String> saveData(PatientDetail pad) {
    if (patientDRepository.findByEmail(pad.getEmail()) != null) {
        // Return a 409 Conflict response if the email already exists
        // In your Java Spring Boot controller method
    return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"status\": 409, \"message\": \"Patient with this email already exists.\"}");

    }
    
    // Save the patient details if no conflict
    patientDRepository.save(pad);
    
    // Return a 200 OK response after successfully saving
    // In your Java Spring Boot controller method
return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"status\":200, \"message\": \"Patient saved successfully .\"}");

}

}
