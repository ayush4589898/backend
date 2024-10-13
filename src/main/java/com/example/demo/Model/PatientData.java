package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;

@Entity
@Table(name = "work")
public class PatientData {

    // Primary Key for patient_id
    
    @Column(name = "patient_id")
    private Long patientId;

    // Column for date_wrt
    @Column(name = "date_wrt")
    private Date dateWrt;

    // Column for image_path
    @Id
    @Column(name = "image_path", length = 255)
    private String imagePath;

    // Column for result
    @Column(name = "result", length = 255)
    private String result;

    // Default Constructor
    public PatientData() {}

    // Parameterized Constructor
    public PatientData(Long patientId, Date dateWrt, String imagePath, String result) {
        this.patientId = patientId;
        this.dateWrt = dateWrt;
        this.imagePath = imagePath;
        this.result = result;
    }

    // Getters and Setters
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Date getDateWrt() {
        return dateWrt;
    }

    public void setDateWrt(Date dateWrt) {
        this.dateWrt = dateWrt;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
