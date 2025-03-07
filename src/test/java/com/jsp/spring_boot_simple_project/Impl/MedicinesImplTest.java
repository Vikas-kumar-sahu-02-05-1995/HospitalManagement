package com.jsp.spring_boot_simple_project.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.aopalliance.intercept.Invocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jsp.spring_boot_simple_project.entity.Medicines;
import com.jsp.spring_boot_simple_project.entity.Patients;
import com.jsp.spring_boot_simple_project.repository.MedicinesRepo;
import com.jsp.spring_boot_simple_project.repository.PatientsRepo;
@SpringBootTest
class MedicinesImplTest {

	@MockBean
	private MedicinesRepo medicinesRepo;
	@Autowired
	private MedicinesImpl medicinesService;
	@MockBean
	private PatientsRepo patientsRepo;
	
	@Test
	public void testsaveMedicineByPatientId() {
		int pId=29;
		Patients patients = new Patients();
		patients.setPatientId(pId);
		
		Medicines medicines = new Medicines();
		medicines.setMedicineId(7);
		
		when(patientsRepo.findById(pId)).thenReturn(Optional.of(patients));
		when(medicinesRepo.save(any(Medicines.class))).thenReturn(medicines);
		
		Medicines result = medicinesService.saveMedicineByPatientId(pId, medicines);
		
		assertEquals(patients, result.getPatient1());
		verify(patientsRepo).findById(pId);
		verify(medicinesRepo).save(medicines);
		
	}
	
	@Test
	public void testgetById() {
		int mId = 25;
		Medicines medicines = new Medicines();
		medicines.setMedicineId(mId);
		
		when(medicinesRepo.findById(mId)).thenReturn(Optional.of(medicines));
		
		Medicines result = medicinesService.getById(mId);
		
		assertNotNull(result);
		assertEquals(mId, result.getMedicineId());
		verify(medicinesRepo, times(1)).findById(mId);
	}

	@Test
	public void testdeleteMedicineById() {
		int mId=36;
		Medicines medicines = new Medicines();
		medicines.setMedicineId(mId);
		
		when(medicinesRepo.findById(mId)).thenReturn(Optional.of(medicines));
		
		medicinesService.deleteById(mId);
		
		verify(medicinesRepo, times(1)).findById(mId);
		verify(medicinesRepo, times(1)).deleteById(mId);
		
	}
	
	@Test
	public void testgetAllListOfMedicines() {
		Medicines medicines1 = new Medicines();
		medicines1.setMedicineId(2);
		medicines1.setMedicineName("lata mangeshwar");
		
		Medicines medicines2 = new Medicines();
		medicines2.setMedicineId(3);
		medicines2.setMedicineName("sunidhi passi");
		
		List<Medicines> expectedmedicines =  Arrays.asList(medicines1, medicines2);
		when(medicinesRepo.findAll()).thenReturn(expectedmedicines);
		
		List<Medicines> actualMedicine = medicinesService.getAllListOfMedicines();
		
		assertEquals(expectedmedicines, actualMedicine);
		verify(medicinesRepo).findAll();
	}
	
	@Test
	public void testupdateById() {
		int mId=76;
		Medicines medicines = new Medicines();
		medicines.setMedicineId(mId);
		medicines.setMedicineName("omega3");
		medicines.setQuantity(12);
		medicines.setRate(23);
		
		Medicines updatedmedicines = new Medicines();
		updatedmedicines.setMedicineName("amlosafe");
		updatedmedicines.setQuantity(34);
		updatedmedicines.setRate(45);
		
		when(medicinesRepo.findById(mId)).thenReturn(Optional.of(medicines));
		when(medicinesRepo.save(any(Medicines.class))).thenAnswer(invocation -> invocation.getArgument(0));
		
		Medicines result = medicinesService.updateById(mId, updatedmedicines);
		
		assertNotNull(result);
		assertEquals("amlosafe", result.getMedicineName());
		assertEquals(34, result.getQuantity());
		assertEquals(45, result.getRate());
		verify(medicinesRepo, times(1)).findById(mId);
		verify(medicinesRepo, times(1)).save(medicines);
		
		
	}
	
}
