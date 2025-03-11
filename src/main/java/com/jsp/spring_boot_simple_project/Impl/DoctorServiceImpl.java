//package com.jsp.spring_boot_simple_project.Impl;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.jsp.spring_boot_simple_project.entity.Doctor;
//import com.jsp.spring_boot_simple_project.entity.Patient;
//import com.jsp.spring_boot_simple_project.repository.DoctorRepository;
//import com.jsp.spring_boot_simple_project.repository.PatientRepository;
//import com.jsp.spring_boot_simple_project.service.DoctorService;
//
//import jakarta.persistence.EntityNotFoundException;
//
//@Service
//public class DoctorServiceImpl implements DoctorService {
//
//	@Autowired
//	private DoctorRepository repository;
//
////	@Autowired
////	private MedicineRepository medicineRepository;
//
//	@Autowired
//	private PatientRepository repository1;
//
//	@Override
//	public Doctor saveDoctor(Doctor doctor) {
//		return repository.save(doctor);
//	}
//
//	@Override
//	public List<Doctor> saveListOfDoctor(List<Doctor> doctors) {
//		return repository.saveAll(doctors);
//	}
//
//	@Override
//	public Doctor getById(int id) {
//		return repository.findById(id)
//				.orElseThrow( () -> new RuntimeException("No Detail found with id: "+ id));
//	}
//
//	public List<Patient> getPatientsByDoctor(int id){
//		return null;
//	}
//
//
//	@Override
//	public Doctor updateById(int id, Doctor updateDoctorDetail) {
//		return repository.findById(id)
//				.stream()
//				.findFirst()
//				.map(doctor -> {
//					doctor.setDoctorName(updateDoctorDetail.getDoctorName());
//					doctor.setFees(updateDoctorDetail.getFees());
//					repository.save(doctor);
//					return doctor;
//				})
//				.orElseThrow(() -> new NoSuchElementException("No Detail found with id: "+ id));
//	}
//
//	@Override
//	public Doctor deleteByIdDoctorDetail(int id) {
//		return repository.findById(id)
//				.map(doctor -> {
//					repository.deleteById(id);
//					return doctor;
//				})
//				.orElseThrow(() ->  new EntityNotFoundException("Doctor Not found with id: "+id));
//	}
//
//	@Override
//	public void addPatientToDoctor(Doctor doctor, Patient patient) {
//		// TODO Auto-generated method stub
//
//	}
//
////	@Override
////	public void saveAll(Doctor doctor) {
////		//save the doctor first
////		repository.save(doctor);
////		//for each patients associate with doctor
////		if(doctor.getPatients() != null) {
////			for(Patient patientss : doctor.getPatients()) {
////				// Set the doctor for each patient
////				patientss.setDoctors(doctor);
////
////				//save the patient first
////				repository1.save(patientss);
////
////				//save all medicines associated with the patient
////				if(patientss.getMedicines() != null) {
////					for(Medicine mediciness : patientss.getMedicines()) {
////						//set the patient for each medicine
////						mediciness.setPatient(patientss);
////						//save the medicine
////						medicineRepository.save(mediciness);
////					}
////				}
////			}
////		}
////
////	}
//
//
//}
