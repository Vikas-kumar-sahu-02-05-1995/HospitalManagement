package com.jsp.spring_boot_simple_project.repository;

import com.jsp.spring_boot_simple_project.entity.MedicalTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.spring_boot_simple_project.entity.Patients;

import java.util.List;

@Repository
public interface PatientsRepo extends JpaRepository<Patients, Integer> {

    List<MedicalTest> findByPatientId(Long patientId);
}
