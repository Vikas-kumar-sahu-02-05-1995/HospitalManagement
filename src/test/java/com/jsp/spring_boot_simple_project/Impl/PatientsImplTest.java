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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jsp.spring_boot_simple_project.entity.Doctors;
import com.jsp.spring_boot_simple_project.entity.Patients;
import com.jsp.spring_boot_simple_project.repository.DoctorsRepo;
import com.jsp.spring_boot_simple_project.repository.PatientsRepo;
@SpringBootTest
class PatientsImplTest {

	@MockBean
	private PatientsRepo patientsRepo;
	@Autowired
	private PatientsImpl servicePatient;
	@MockBean
	private DoctorsRepo doctorsRepo;
	
	@Test
	public void testsavePatientByDoctorId() {
		int doctorId = 10;
		Doctors doctors = new Doctors();
		doctors.setId(doctorId);
		
		Patients patients = new Patients();
		patients.setPatientId(1);
		
		when(doctorsRepo.findById(doctorId)).thenReturn(Optional.of(doctors));
		when(patientsRepo.save(any(Patients.class))).thenReturn(patients);
		
		Patients resullt= servicePatient.savePatientByDoctorId(doctorId, patients);
		
		assertEquals(doctors, resullt.getDoctor1());
		verify(doctorsRepo).findById(doctorId);
		verify(patientsRepo).save(patients);
	}

	@Test
	public void testgetByIdPatientDetail() {
	     int patientId =9;
	     Patients patients = new Patients();
	     patients.setPatientId(patientId);
	     when(patientsRepo.findById(patientId)).thenReturn(Optional.of(patients));
	     
	     Patients result = servicePatient.getByIdPatientDetail(patientId);
	     
	     assertNotNull(result);
	     assertEquals(patientId, result.getPatientId());
	     verify(patientsRepo, times(1)).findById(patientId);  
	}
	
	@Test
	public void testdeleteByIdPatientDetail() {
		int pId =19;
		Patients patients = new Patients();
		patients.setPatientId(pId);
		when(patientsRepo.findById(pId)).thenReturn(Optional.of(patients));
		
		servicePatient.deleteByIdPatientDetail(pId);
		
		verify(patientsRepo, times(1)).findById(pId);
		verify(patientsRepo, times(1)).deleteById(pId);
	}
	
	@Test
	public void testgetAllPatients() {
		Patients patient1 = new Patients();
		patient1.setPatientId(1);
		patient1.setPatientName("ninja");
		
		Patients patient2 = new Patients();
		patient2.setPatientId(2);
		patient2.setPatientName("techniq");
		
		List<Patients> expectedPatients = Arrays.asList(patient1, patient2);
		when(patientsRepo.findAll()).thenReturn(expectedPatients);
		
		List<Patients> actualPatient = servicePatient.getAllPatients();
		
		assertEquals(expectedPatients, actualPatient);
		verify(patientsRepo).findAll();
	}
	
	@Test
	public void testupdateByIdPatientDetail() {
		int patientId=4;
		Patients patinets = new Patients();
		patinets.setPatientId(patientId);
		patinets.setPatientName("sohel");
		patinets.setAddress("noida");
		patinets.setAge(23);
		patinets.setDisease("fever");
		patinets.setGender("male");
		
		Patients updatePatinetDetail = new Patients();
		updatePatinetDetail.setPatientName("prakash");
		updatePatinetDetail.setAddress("mg road");
		updatePatinetDetail.setAge(34);
		updatePatinetDetail.setDisease("maleria");
		updatePatinetDetail.setGender("Male");
		
		when(patientsRepo.findById(patientId)).thenReturn(Optional.of(patinets));
		when(patientsRepo.save(any(Patients.class))).thenAnswer(invocation -> invocation.getArgument(0));

		
		Patients result = servicePatient.updateByIdPatientDetail(patientId, updatePatinetDetail);
		
		assertNotNull(result);
		assertEquals("prakash", result.getPatientName());
		assertEquals("mg road", result.getAddress());
		assertEquals(34, result.getAge());
		assertEquals("maleria", result.getDisease());
		assertEquals("Male", result.getGender());
		verify(patientsRepo, times(1)).findById(patientId);
		verify(patientsRepo, times(1)).save(patinets);
	}
}
