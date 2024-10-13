package com.example.demo.Model;

import jakarta.persistence.*;  // Use javax.persistence.* if you're using older JPA versions

@Entity
@Table(name = "patient_detail")
public class PatientDetail {
  
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "email", unique = true, length = 255)
    private String email;

   
    
    public PatientDetail(){
        
    }
    // Getters and setters
    public PatientDetail( String name,String email ){
        this.name=name;
        this.email=email;
    }
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
