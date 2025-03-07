//package com.jsp.spring_boot_simple_project.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.jsp.spring_boot_simple_project.dto.Medicine;
//import com.jsp.spring_boot_simple_project.service.MedicineService;
//
//@RestController
//@RequestMapping("/medicine")
//public class MedicineController {
//
//	@Autowired
//	private MedicineService medicineService;
//	
//	@PostMapping("/inserts")
//	public ResponseEntity<Medicine> saveMedicineController(@RequestBody Medicine medicine) {
//		return new ResponseEntity<Medicine>(medicineService.saveMedicine(medicine), HttpStatus.OK);
//	}
//	
//	@PostMapping("/insertThem")
//	public ResponseEntity<Medicine> addMedicinecontroller(@RequestBody Medicine medicine) {
//		return new ResponseEntity<Medicine>(medicineService.addMedicine(medicine), HttpStatus.OK);
//	}
//	
//	@GetMapping(value = "/getById")
//	public ResponseEntity<Medicine> getByIdController(@RequestParam int medicineId) {
//		return new ResponseEntity<Medicine>(medicineService.getById(medicineId), HttpStatus.OK);
//	}
//}
