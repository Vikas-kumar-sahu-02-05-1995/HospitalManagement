package com.jsp.spring_boot_simple_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.spring_boot_simple_project.entity.Medicines;

@Service
public interface MedicinesService {
	
	public Medicines saveMedicineByPatientId(int patientId,Medicines medicine);
	
	public Medicines getById(int medicineId);
	
	public Medicines updateById(int medicineId, Medicines updateMedicineDetail);
	
	public Medicines deleteById(int medicineId);
	
	public List<Medicines> getAllListOfMedicines();
	
}
