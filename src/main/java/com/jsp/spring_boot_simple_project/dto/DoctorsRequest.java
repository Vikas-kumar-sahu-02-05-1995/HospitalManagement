package com.jsp.spring_boot_simple_project.dto;

import lombok.Data;

@Data
public class DoctorsRequest {

	private int id;
    private String doctorsName;
    private String gender;
    private int age;
    private double fees;
    private String qualification;
    private String specialization;
}
