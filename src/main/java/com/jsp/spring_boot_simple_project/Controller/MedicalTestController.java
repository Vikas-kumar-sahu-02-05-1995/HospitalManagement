package com.jsp.spring_boot_simple_project.Controller;


import com.jsp.spring_boot_simple_project.Impl.MedicalTestServiceimpl;
import com.jsp.spring_boot_simple_project.entity.MedicalTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-tests")
public class MedicalTestController {

    @Autowired
    private MedicalTestServiceimpl medicalTestService;

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalTest>> getTestsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalTestService.getMedicalTestsByPatient(patientId));
    }

    @PostMapping
    public ResponseEntity<MedicalTest> addMedicalTest(@RequestBody MedicalTest medicalTest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalTestService.addMedicalTest(medicalTest));
    }
}

