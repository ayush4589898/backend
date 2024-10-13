package com.example.demo.repo;


import com.example.demo.Model.PatientDetail; // Adjust package as necessary

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDetailRepository extends JpaRepository<PatientDetail, Long> {
 
    PatientDetail findByEmail(String email);
    PatientDetail findByName(String name);
    PatientDetail findByPatientId(Long patientId);

    @Query("SELECT DISTINCT p.id FROM PatientDetail p WHERE p.email = ?1")
    List<Long> findUniquePatientIdsByEmail(String email);

    @Query("SELECT DISTINCT p.id FROM PatientDetail p WHERE p.name = ?1")
    List<Long> findUniquePatientIdsByName(String name);

    @Query("SELECT p FROM PatientDetail p WHERE p.patientId IN :patientIds")
    List<PatientDetail> findByPatientIds(@Param("patientIds") List<Long> patientIds);
}
