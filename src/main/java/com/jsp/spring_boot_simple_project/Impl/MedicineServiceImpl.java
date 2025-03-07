//package com.jsp.spring_boot_simple_project.Impl;
//
//import java.util.NoSuchElementException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.jsp.spring_boot_simple_project.dto.Doctor;
//import com.jsp.spring_boot_simple_project.dto.Medicine;
//import com.jsp.spring_boot_simple_project.dto.Patient;
//import com.jsp.spring_boot_simple_project.repository.DoctorRepository;
//import com.jsp.spring_boot_simple_project.repository.MedicineRepository;
//import com.jsp.spring_boot_simple_project.repository.PatientRepository;
//import com.jsp.spring_boot_simple_project.service.MedicineService;
//
//import jakarta.persistence.EntityNotFoundException;
//
//@Service
//public class MedicineServiceImpl implements MedicineService {
//
//	@Autowired
//	private MedicineRepository medicineRepository;
//	@Autowired
//	private DoctorRepository doctorRepository;
//	@Autowired
//	private PatientRepository patientRepository;
//
//	@Override
//	public Medicine saveMedicine(Medicine medicine) {
//		return medicineRepository.save(medicine);
//	}
//
//	@Override
//	public Medicine addMedicine( Medicine medicine) {
//	  
//	    if (medicine.getPatient() != null) {
//	      Patient patient = patientRepository.findById(medicine.getPatient().getPatientId()).orElse(null);
//	         medicine.setPatient(patient);
//	     }
//	   return medicineRepository.save(medicine);
//	}
//
//	@Override
//	public Medicine getById(int medicineId) {
//		return medicineRepository.findById(medicineId)
//				.stream()
//				.findFirst()
//				.orElseThrow(() -> new NoSuchElementException("No id is found: "+medicineId));
//	}
//
//	@Override
//	public Medicine updateById(int medicineId, Medicine updateMedicineDetail) {
//		return medicineRepository.findById(medicineId)
//				.stream()
//				.findFirst()
//				.map(medicine -> {
//					medicine.setMedicineName(updateMedicineDetail.getMedicineName());
//					medicine.setExpDate(updateMedicineDetail.getExpDate());
//					medicine.setMfeDate(updateMedicineDetail.getMfeDate());
//					medicineRepository.save(medicine);
//					return medicine;
//				})
//				.orElseThrow(() ->  new EntityNotFoundException("Doctor Not found with id: "+medicineId));
//	}
//
//	@Override
//	public Medicine deleteById(int medicineId) {
//		return null;
//	}
//
//	
//
//}
