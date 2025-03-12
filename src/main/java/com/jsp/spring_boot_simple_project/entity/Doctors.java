package com.jsp.spring_boot_simple_project.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity

public class Doctors {

	@Id
	private int id;
    private String doctorsName;
	private String gender;
    private int age;
    private double fees;
    private String qualification;
    private String specialization;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Patients> patients;  // Changed patient1 → patients

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MedicalTest> medicalTests;  // Mapped correctly with "doctor"

	public Doctors(int id, String doctorsName, String gender, int age, double fees, String qualification,
				   String specialization, List<Patients> patient1) {
		super();
		this.id = id;
		this.doctorsName = doctorsName;
		this.gender = gender;
		this.age = age;
		this.fees = fees;
		this.qualification = qualification;
		this.specialization = specialization;
		this.patients = patient1;
	}

    public Doctors() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDoctorsName() {
		return doctorsName;
	}

	public void setDoctorsName(String doctorsName) {
		this.doctorsName = doctorsName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<Patients> getPatient1() {
		return patients;
	}

	public void setPatient1(List<Patients> patient1) {
		this.patients = patient1;
	}




   
}
