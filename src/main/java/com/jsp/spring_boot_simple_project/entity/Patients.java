package com.jsp.spring_boot_simple_project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "patient1", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Medicines> medicine1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Doctors doctor1;
}
