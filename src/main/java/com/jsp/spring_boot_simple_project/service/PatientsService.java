package com.jsp.spring_boot_simple_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.spring_boot_simple_project.entity.Patients;

@Service
public interface PatientsService {
	
	public Patients savePatientByDoctorId(int id, Patients patient);
	
	public Patients getByIdPatientDetail(int patientId);
	
	public Patients updateByIdPatientDetail(int patientId, Patients updatePatinetDetail);
	
	public Patients deleteByIdPatientDetail(int patientId);
	
	public List<Patients> getAllPatients();

	 public Patients addPatient(Patients patients) ;

	public List<Patients> searchByMultipleField(String value);
}
