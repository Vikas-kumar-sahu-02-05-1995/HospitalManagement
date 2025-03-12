package com.jsp.spring_boot_simple_project.service;

import com.jsp.spring_boot_simple_project.entity.MedicalTest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalTestService {

    public List<MedicalTest> getMedicalTestsByPatient(Long patientId);

    public MedicalTest addMedicalTest(MedicalTest medicalTest);

}
