package com.jsp.spring_boot_simple_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medical_tests")
@Data
public class MedicalTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Ensure this is set properly
    @Column(name = "test_id")  // Explicitly define the column
    private Long testId;

    private String testName;
    private String testDate;
    private String result;

    @ManyToOne
    @JoinColumn(name = "patient_test_id", nullable = false)
    private Patients patient;  // Use singular "patient"

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctors doctor;  // Optional: The doctor who prescribed the test
}
