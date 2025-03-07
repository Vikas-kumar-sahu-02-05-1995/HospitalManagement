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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.spring_boot_simple_project.Impl.HealthCareService;
import com.jsp.spring_boot_simple_project.entity.Doctors;

@RestController
@RequestMapping("/doctor")
public class HealthCareController {

	@Autowired
	private HealthCareService healthCareService;
	
	@GetMapping("/home")
	public ResponseEntity<String> normalUser(){
		return ResponseEntity.ok("yes, successfull login");
	}
	
	
	@PostMapping(value = "/saveAllData")
	public ResponseEntity<String> saveAllController(@RequestBody Doctors doctors) {
		healthCareService.saveAll(doctors);
		return ResponseEntity.ok("data saved successfully ");
	}
	
	@GetMapping(value = "/getById")
	public ResponseEntity<Doctors> getByIdDoctorController(@RequestParam(value = "id", required = false) Integer id, String doctorsName) {
		if(id == null) {
			return new ResponseEntity<Doctors>(healthCareService.getByNameDoctors(doctorsName) ,HttpStatus.OK);
			
		}else {
		Doctors doctors = healthCareService.getByIdDoctor(id);
		return  ResponseEntity.ok().body(doctors) ;
		}
		
	 }
	
	@PutMapping("/updateBydoctorsId")
	public ResponseEntity<Doctors> updateDoctorsDetailsByIdController(@RequestParam int id,@RequestBody  Doctors updateDoctorDetails ) {
		return new ResponseEntity<Doctors>(healthCareService.updateDoctorsDetailsById(id, updateDoctorDetails), HttpStatus.OK);
	}

	
	@DeleteMapping("/deleteById")
	public ResponseEntity<Doctors> deleteByDoctorsIdController(@RequestParam int id) {
		return new ResponseEntity<Doctors>(healthCareService.deleteByDoctorsId(id), HttpStatus.OK);
	}
	
	@GetMapping("/getByDoctorsName")
	 public ResponseEntity<Doctors> getByNameDoctorsController(@RequestParam String doctorsName) {
		 return new ResponseEntity<Doctors>(healthCareService.getByNameDoctors(doctorsName), HttpStatus.OK);
	 }
	@GetMapping("/getAllDocotrs")
	public ResponseEntity<List<Doctors>> getAllDoctorsController() {
		return new ResponseEntity<List<Doctors>>(healthCareService.getAllDoctors(), HttpStatus.OK);
	}
	
	@GetMapping("/getListOfDoctorBySpecilization/{specialization}")
	public ResponseEntity<List<Doctors>> getDoctorsBySpecialization(@PathVariable String specialization) {
		return new ResponseEntity<List<Doctors>>(healthCareService.getDoctorsBySpecialization(specialization), HttpStatus.OK);
	}
	
//	@GetMapping("/getListOfDoctorsBySpecilization")
//	public ResponseEntity<List<Doctors>> getListOfDoctors(@RequestBody Doctors doctors) {
//		return new ResponseEntity<List<Doctors>>(healthCareService.getListOfDoctors(doctors), HttpStatus.OK);
//	}
	
//	@GetMapping("/search")
//	public List<Doctors> searchDoctors(@RequestParam String value){
//		return healthCareService.searchByMultipleFields(value);
//	}
	
	
	
}