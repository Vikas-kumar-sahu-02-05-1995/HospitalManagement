package com.jsp.spring_boot_simple_project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.spring_boot_simple_project.entity.Medicines;
import com.jsp.spring_boot_simple_project.service.MedicinesService;

@RestController
@RequestMapping("/medicines")
public class MedicinesController {

	@Autowired
	private MedicinesService medicinesService;
	
	
	@PostMapping("/saveMedicineByIdPatient/{patientId}")
	public ResponseEntity<Medicines> saveMedicineByPatientIdController(@PathVariable int patientId,@RequestBody Medicines medicine) {
		return new ResponseEntity<Medicines>(medicinesService.saveMedicineByPatientId(patientId, medicine), HttpStatus.OK);
	}
	
	@GetMapping("/getAllMedicines")
	public ResponseEntity<List<Medicines>> getAllListOfMedicinesController() {
		return new ResponseEntity<List<Medicines>>(medicinesService.getAllListOfMedicines(), HttpStatus.OK);
	}
	
	@GetMapping("/getById/{medicineId}")
	public ResponseEntity<Medicines> getByIdController(@PathVariable int medicineId) {
		return new ResponseEntity<Medicines>(medicinesService.getById(medicineId), HttpStatus.OK);
	}
	
	@PutMapping("/updateByMedicinesId/{medicineId}")
	public ResponseEntity<Medicines> updateByIdController(@PathVariable int medicineId,@RequestBody Medicines updateMedicineDetail) {
		return new ResponseEntity<Medicines>(medicinesService.updateById(medicineId, updateMedicineDetail), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{medicineId}")
	public ResponseEntity<Medicines> deleteByIdController(@PathVariable int medicineId) {
		return new ResponseEntity<Medicines>(medicinesService.deleteById(medicineId), HttpStatus.OK);
	}

	
	
}
