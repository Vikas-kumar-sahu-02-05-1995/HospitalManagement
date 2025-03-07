package com.jsp.spring_boot_simple_project.Impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.spring_boot_simple_project.dto.DoctorsRequest;
import com.jsp.spring_boot_simple_project.dto.MedicinesRequest;
import com.jsp.spring_boot_simple_project.entity.Doctors;
import com.jsp.spring_boot_simple_project.entity.Medicines;
import com.jsp.spring_boot_simple_project.entity.Patients;
import com.jsp.spring_boot_simple_project.repository.MedicinesRepo;
import com.jsp.spring_boot_simple_project.repository.PatientsRepo;
import com.jsp.spring_boot_simple_project.service.MedicinesService;

@Service
public class MedicinesImpl implements MedicinesService {

	@Autowired
	private MedicinesRepo medicinesRepo;
	@Autowired
	private PatientsRepo patientRepository;
	
	@Override
	public Medicines saveMedicineByPatientId(int patientId,Medicines medicine) {
		Patients patients = patientRepository.findById(patientId)
				.orElseThrow(() -> new NoSuchElementException("No Patient id found "+patientId));
		medicine.setPatient1(patients);
		return medicinesRepo.save(medicine);
	}

	@Override
	public Medicines getById(int medicineId) {
		return medicinesRepo.findById(medicineId)
				.orElseThrow(() -> new NoSuchElementException("No Address found with id: "+ medicineId));
	}

	@Override
	public Medicines updateById(int medicineId, Medicines updateMedicineDetail) {
		return medicinesRepo.findById(medicineId)
				.map(medicine -> {
					medicine.setMedicineName(updateMedicineDetail.getMedicineName());
					medicine.setRate(updateMedicineDetail.getRate());
					medicine.setQuantity(updateMedicineDetail.getQuantity());
					medicine.setMfeDate(updateMedicineDetail.getMfeDate());
					medicine.setExpDate(updateMedicineDetail.getExpDate());
					
					return medicinesRepo.save(medicine);
				})
				.orElseThrow(() -> new NoSuchElementException("No medicineId found "+medicineId));
	}

	@Override
	public Medicines deleteById(int medicineId) {
		return medicinesRepo.findById(medicineId) 
				.map(medicines1 -> {
					medicinesRepo.deleteById(medicineId);
					return medicines1;
				})
				.orElseThrow(() -> new NoSuchElementException("No medicinesId found "+ medicineId));
	}

	@Override
	public List<Medicines> getAllListOfMedicines() {
		List<Medicines> list = medicinesRepo.findAll();
		return list;
	}
	
	public Medicines convertToEntity(MedicinesRequest medicinesRequest) {
		Medicines medicines = new Medicines();
		medicines.setMedicineId(medicinesRequest.getMedicineId());
		medicines.setExpDate(medicinesRequest.getExpDate());
		medicines.setMedicineName(medicinesRequest.getMedicineName());
		medicines.setMfeDate(medicinesRequest.getMfeDate());
		medicines.setQuantity(medicinesRequest.getQuantity());
		medicines.setRate(medicinesRequest.getRate());
		return medicines;
	}
	
	public MedicinesRequest convertToResponse(Medicines medicines) {
		MedicinesRequest medicinesRequest = new MedicinesRequest();
		medicinesRequest.setMedicineId(medicines.getMedicineId());
		medicinesRequest.setExpDate(medicines.getExpDate());
		medicinesRequest.setMedicineName(medicines.getMedicineName());
		medicinesRequest.setMfeDate(medicines.getMfeDate());
		medicinesRequest.setQuantity(medicines.getQuantity());
		medicinesRequest.setRate(medicines.getRate());
		return medicinesRequest;
	}
}
