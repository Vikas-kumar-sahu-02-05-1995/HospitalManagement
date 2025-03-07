package com.jsp.spring_boot_simple_project.dto;

import lombok.Data;

@Data
public class PatientsRequest {

	private int patientId;
	private String patientName;
	private String disease;
	private String gender;
	private int age;
	private String address;
	
}
