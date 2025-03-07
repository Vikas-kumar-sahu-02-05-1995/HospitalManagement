package com.jsp.spring_boot_simple_project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.spring_boot_simple_project.entity.Doctor;
import com.jsp.spring_boot_simple_project.service.DoctorService;

@RestController
@RequestMapping("/doctor1")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@PostMapping(value = "/insert")
	public ResponseEntity<Doctor> saveDoctorController(@RequestBody Doctor doctor) {
		return new ResponseEntity<Doctor>(doctorService.saveDoctor(doctor), HttpStatus.OK);
	}
	
	@PostMapping(value = "/insertAll")
	public ResponseEntity<List<Doctor>> saveListOfDoctorController(@RequestBody List<Doctor> doctors) {
		return new ResponseEntity<List<Doctor>>(doctorService.saveListOfDoctor(doctors), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById")
	public ResponseEntity<Doctor> getByIdController(@RequestParam int id) {
		return new ResponseEntity<Doctor>(doctorService.getById(id), HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/updateDoctor")
	public ResponseEntity<Doctor> updateByIdController(@RequestParam int id,@RequestBody Doctor updateDoctorDetail) {
		return new ResponseEntity<Doctor>(doctorService.updateById(id, updateDoctorDetail), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteDoctorById")
	public ResponseEntity<Doctor> deleteByIdDocotrController(@RequestParam int id) { 
		return new ResponseEntity<Doctor>(doctorService.deleteByIdDoctorDetail(id), HttpStatus.OK);
	}
	
//	@PostMapping("/saveAll")
//	public ResponseEntity<String> saveAllController(@RequestBody Doctor doctor) {
//		doctorService.saveAll(doctor);
//		return ResponseEntity.ok("data saved succefully");
//	}
}
