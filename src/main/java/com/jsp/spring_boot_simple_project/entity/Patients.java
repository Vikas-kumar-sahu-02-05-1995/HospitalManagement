package com.jsp.spring_boot_simple_project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Patients {

	@Id
	private int patientId;
	private String patientName;
	private String disease;
	private String gender;
	private int age;
	private String address;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Medicines> medicines;  // Changed from medicine1 to medicines

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Doctors doctor;  // Changed doctor1 → doctor

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private List<MedicalTest> medicalTests;  // "mappedBy" should match "patient" in MedicalTest

}
