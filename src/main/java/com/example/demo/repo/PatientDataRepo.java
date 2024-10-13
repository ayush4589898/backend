package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.PatientData;

@Repository
public interface PatientDataRepo extends JpaRepository<PatientData, String> {

    // Method to find all PatientData records by a list of patient IDs
    @Query("SELECT p FROM PatientData p WHERE p.patientId IN :patientIds")
    List<PatientData> findByPatientIds(@Param("patientIds") List<Long> patientIds);
}
