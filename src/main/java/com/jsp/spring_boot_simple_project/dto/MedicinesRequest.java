package com.jsp.spring_boot_simple_project.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MedicinesRequest {

	private int medicineId;
    private String medicineName;
    private LocalDate mfeDate;
    private LocalDate expDate;
    private double rate;
    private long quantity;
    
}
