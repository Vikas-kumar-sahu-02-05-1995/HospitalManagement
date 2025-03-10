package com.jsp.spring_boot_simple_project.Controller;

import java.util.List;

import com.jsp.spring_boot_simple_project.entity.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jsp.spring_boot_simple_project.entity.Patients;
import com.jsp.spring_boot_simple_project.service.PatientsService;

@RestController
@RequestMapping("/patients")
public class PatientsController {

	@Autowired
	private PatientsService patientsService;
	
	
	
	@PostMapping("/savelist/{id}")
	public ResponseEntity<Patients> savePatientByDoctorId(@PathVariable int id,@RequestBody Patients patient) {
		return new ResponseEntity<Patients>(patientsService.savePatientByDoctorId(id, patient), HttpStatus.OK);
	}
	
	@GetMapping("/getAllPatients")
	public ResponseEntity<List<Patients>> getAllPatientsController() {
		return new ResponseEntity<List<Patients>>(patientsService.getAllPatients(), HttpStatus.OK);
	}
	
	@PutMapping("/updatePatients/{patientId}")
	public ResponseEntity<Patients> updateByIdPatientDetailController(@PathVariable int patientId,@RequestBody Patients updatePatinetDetail) {
		return new ResponseEntity<Patients>(patientsService.updateByIdPatientDetail(patientId, updatePatinetDetail), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{patinetId}")
	public ResponseEntity<Patients> getByIdPatientDetailController(@PathVariable int patientId) {
		return new ResponseEntity<Patients>(patientsService.getByIdPatientDetail(patientId), HttpStatus.OK);
	}
	
	@DeleteMapping("/getBypatientId/{patientId}")
	public ResponseEntity<Patients> deleteByIdPatientDetailController(@PathVariable int patientId) {
		return new ResponseEntity<Patients>(patientsService.deleteByIdPatientDetail(patientId), HttpStatus.OK);
	}
	
	@PostMapping("/savePatientMedicineByDoctorId")
	public ResponseEntity<Patients> addPatientController(@RequestBody Patients patients) {
		return new ResponseEntity<Patients>(patientsService.addPatient(patients), HttpStatus.OK);
	}

	@GetMapping("/search")
	public List<Patients> searchDoctorsbyField(@RequestParam String value){
		return  patientsService.searchByMultipleField(value);
	}

}
