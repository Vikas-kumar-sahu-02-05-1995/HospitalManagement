package com.jsp.spring_boot_simple_project.Impl;

import com.jsp.spring_boot_simple_project.entity.MedicalTest;
import com.jsp.spring_boot_simple_project.repository.MedicalTestRepository;
import com.jsp.spring_boot_simple_project.repository.PatientsRepo;
import com.jsp.spring_boot_simple_project.service.MedicalTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalTestServiceimpl implements MedicalTestService {

    @Autowired
    private MedicalTestRepository medicalTestRepo;
    @Autowired
    private PatientsRepo patientsRepo;

    @Override
    public List<MedicalTest> getMedicalTestsByPatient(Long patientId) {
        return medicalTestRepo.findByPatient_PatientId(patientId);
    }

    @Override
    public MedicalTest addMedicalTest(MedicalTest medicalTest) {
        return medicalTestRepo.save(medicalTest);
    }
}

