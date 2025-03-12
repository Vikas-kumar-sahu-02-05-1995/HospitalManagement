package com.jsp.spring_boot_simple_project.repository;

import com.jsp.spring_boot_simple_project.entity.MedicalTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalTestRepository extends JpaRepository<MedicalTest, Long> {


    List<MedicalTest> findByPatient_PatientId(Long patientId);
}
