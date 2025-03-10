package com.jsp.spring_boot_simple_project.Impl;

import java.util.List;
import java.util.NoSuchElementException;

import com.jsp.spring_boot_simple_project.entity.Doctors;
import com.jsp.spring_boot_simple_project.entity.Patients;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.spring_boot_simple_project.entity.Patient;
import com.jsp.spring_boot_simple_project.repository.DoctorRepository;
import com.jsp.spring_boot_simple_project.repository.PatientRepository;
import com.jsp.spring_boot_simple_project.service.PatientService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private PatientRepository repository;
//	@Autowired
//	private MedicineRepository medicineRepository;
	@Override
	public Patient savethePatient(Patient patient) {
		
		return repository.save(patient);
	}

	@Override
	public List<Patient> saveListPatient(List<Patient> patients) {
		return repository.saveAll(patients);
	}
	
	

	@Override
	public Patient getByIdPatientDetail(int patientId) {
		return repository.findById(patientId)
				.stream()
				.findFirst()
				.orElseThrow(() -> new NoSuchElementException("No Address found with id: "+ patientId));
	}

	@Override
	public Patient updateByIdPatientDetail(int patientId, Patient updatePatientDetail) {
		return repository.findById(patientId)
				.stream()
				.findFirst()
				.map(patient -> {
					patient.setDisease(updatePatientDetail.getDisease());
					patient.setAge(updatePatientDetail.getAge());
					patient.setAddress(updatePatientDetail.getAddress());
					patient.setGender(updatePatientDetail.getGender());
					patient.setPatientName(updatePatientDetail.getPatientName());
					patient.setDoctors(updatePatientDetail.getDoctors());
					repository.save(patient);
					return patient;
				})
				.orElseThrow(() -> new  NoSuchElementException("No Detail found with id: "+ patientId));
	}

	@Override
	public Patient deleteByIdPatientDetail(int patientId) {
		return repository.findById(patientId)
				.map(patient -> {
					repository.deleteById(patientId);
					return patient;
				})
				.orElseThrow(() -> new EntityNotFoundException("Doctor Not found with id: "+patientId));
	}

	@PersistenceContext
	private EntityManager entityManager;

    @Override
	public List<Patients> searchByMultipleField(String value) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Patient.class);
		Root<Patient> root = criteriaQuery.from(Patient.class);



		return null;
	}
	
//	@Override
//	public Patient addPatient(Patient patient) {
//	    // Ensure the medicines list is not null
//	    if (patient.getMedicines() != null) {
//	        List<Medicine> associatedMedicines = new ArrayList<>();
//	        for (Medicine medicine : patient.getMedicines()) {
//	            // Find the existing medicine by its ID
//	            Medicine existingMedicine = medicineRepository.findById(medicine.getMedicineId()).orElse(null);
//	            if (existingMedicine != null) {
//	                // Set the patient for this existing medicine
//	                existingMedicine.setPatient(patient);
//	                associatedMedicines.add(existingMedicine);
//	            } else {
//	                // Handle the case where the medicine does not exist
//	                // You can either skip, log, or return null depending on the business logic
//	                return null;
//	            }
//	        }
//	        // Associate the found medicines with the patient
//	        patient.setMedicines(associatedMedicines);
//	    }
//
//	    // Ensure the doctors list is not null
//	    if (patient.getDoctors() != null) {
//	        List<Doctor> associatedDoctors = new ArrayList<>();
//	        for (Doctor doctor : patient.getDoctors()) {
//	            // Find the existing doctor by its ID
//	            Doctor existingDoctor = doctorRepository.findById(doctor.getId()).orElse(null);
//	            if (existingDoctor != null) {
//	                associatedDoctors.add(existingDoctor);
//	            } else {
//	                // Handle the case where the doctor does not exist
//	                // You can either skip, log, or return null depending on the business logic
//	                return null;
//	            }
//	        }
//	        // Associate the found doctors with the patient
//	        patient.setDoctors(associatedDoctors);
//	    }
//
//	    // Save and return the patient
//	    return repository.save(patient);
//	}


}
