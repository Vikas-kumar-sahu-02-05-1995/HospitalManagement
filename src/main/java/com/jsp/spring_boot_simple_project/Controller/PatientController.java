package com.jsp.spring_boot_simple_project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.spring_boot_simple_project.entity.Patient;
import com.jsp.spring_boot_simple_project.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService service;
	
	@PostMapping(value = "/insert")
	public ResponseEntity<Patient> savethePatientController(@RequestBody Patient patient) {
		return new ResponseEntity<Patient>(service.savethePatient(patient), HttpStatus.OK);
	}
	
//	@PostMapping("/insertThem")
//	public ResponseEntity<Patient> addPatientController(@RequestBody Patient patient) {
//		return new ResponseEntity<Patient>(service.addPatient(patient), HttpStatus.OK);
//	}
	
	@PostMapping(value = "/insertAll")
	public ResponseEntity<List<Patient>> saveListPatientController(@RequestBody List<Patient> patients) {
		return new ResponseEntity<List<Patient>>(service.saveListPatient(patients), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById")
	public ResponseEntity<Patient> getByIdPatientDetailController(@RequestParam int patientId) {
		return new ResponseEntity<Patient>(service.getByIdPatientDetail(patientId), HttpStatus.OK);
	}
	

}
