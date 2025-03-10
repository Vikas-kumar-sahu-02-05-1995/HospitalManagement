package com.jsp.spring_boot_simple_project.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.spring_boot_simple_project.EcceptionHandling.ResourceNotFoundException;
import com.jsp.spring_boot_simple_project.dto.PatientsRequest;
import com.jsp.spring_boot_simple_project.entity.Doctors;
import com.jsp.spring_boot_simple_project.entity.Medicines;
import com.jsp.spring_boot_simple_project.entity.Patients;
import com.jsp.spring_boot_simple_project.repository.DoctorsRepo;
import com.jsp.spring_boot_simple_project.repository.MedicinesRepo;
import com.jsp.spring_boot_simple_project.repository.PatientsRepo;
import com.jsp.spring_boot_simple_project.service.PatientsService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientsImpl implements PatientsService {

	@Autowired
	private PatientsRepo patientsRepo;
	@Autowired
	private MedicinesRepo medicinesRepo;
	@Autowired
	private DoctorsRepo doctorsRepo;
	
	

	@Override
	public Patients savePatientByDoctorId(int id, Patients patient) {
       Doctors doctor = doctorsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id " + id));
        patient.setDoctor1(doctor);
     return patientsRepo.save(patient);
    }

	@Override
	public Patients getByIdPatientDetail(int patientId) {
		return patientsRepo.findById(patientId)
				.orElseThrow(() -> new NoSuchElementException("no patient id found "+patientId));
	}

	@Override
	public Patients updateByIdPatientDetail(int patientId, Patients updatePatinetDetail) {
		return patientsRepo.findById(patientId)
				.map(patinets -> {
					patinets.setAddress(updatePatinetDetail.getAddress());
					patinets.setAge(updatePatinetDetail.getAge());
					patinets.setDisease(updatePatinetDetail.getDisease());
					patinets.setGender(updatePatinetDetail.getGender());
					patinets.setPatientName(updatePatinetDetail.getPatientName());
					return patientsRepo.save(patinets);
				})
				.orElseThrow(()-> new NoSuchElementException("No PatientsId found "+patientId));
	}

	@Override
	public Patients deleteByIdPatientDetail(int patientId) {
		return patientsRepo.findById(patientId)
				.map(patients -> {
					patientsRepo.deleteById(patientId);
					return patients;
				})
				.orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: "));
	}


	@Override
	public List<Patients> getAllPatients() {
		List<Patients> list = patientsRepo.findAll();
		return list;
	}

	@Override
	public Patients addPatient(Patients patients) {
	    // Ensure the medicines list is not null
	    if (patients.getMedicine1() != null) {
	        List<Medicines> associatedMedicines = new ArrayList<>();
	        for (Medicines medicines : patients.getMedicine1()) {
	            // Find the existing medicine by its ID
	            Medicines existingMedicine = medicinesRepo.findById(medicines.getMedicineId()).orElse(null);
	            if (existingMedicine != null) {
	                // Set the patient for this existing medicine
	                existingMedicine.setPatient1(patients);
	                associatedMedicines.add(existingMedicine);
	            } else {
	                return null;
	            }
	        }
	        // Associate the found medicines with the patient
	        patients.setMedicine1(associatedMedicines);
	   }
	    if (patients.getDoctor1() != null) {
	        // Find the existing doctor by its ID
	        Doctors existingDoctor = doctorsRepo.findById(patients.getDoctor1().getId())
	            .orElseThrow(() -> new NoSuchElementException("No Doctor found with id: " + patients.getDoctor1().getId()));

	        // Associate the found doctor with the patient
	        patients.setDoctor1(existingDoctor);
	    }

	    // Save and return the patient
	    return patientsRepo.save(patients);
	}

	public Patients convertToEntity(PatientsRequest patientsRequest) {
		Patients patients = new Patients();
		patients.setPatientId(patientsRequest.getPatientId());
		patients.setAddress(patientsRequest.getAddress());
		patients.setAge(patientsRequest.getAge());
		patients.setDisease(patientsRequest.getDisease());
		patients.setGender(patientsRequest.getGender());
		patients.setPatientName(patientsRequest.getPatientName());
		return patients;
	}
	
	public PatientsRequest convertToResponse(Patients patients) {
		PatientsRequest patientsRequest = new PatientsRequest();;
		patientsRequest.setPatientId(patients.getPatientId());
		patientsRequest.setAddress(patients.getAddress());
		patientsRequest.setAge(patients.getAge());
		patientsRequest.setDisease(patients.getDisease());
		patientsRequest.setPatientName(patients.getPatientName());
		return patientsRequest ;
	}

	@PersistenceContext
	private EntityManager entityManager;

	public List<Patients> searchByMultipleField(String value){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Patients> criteriaQuery = criteriaBuilder.createQuery(Patients.class);
		Root<Patients> root = criteriaQuery.from(Patients.class);

		List<Predicate> predicate = new ArrayList<>();

		if(value != null){
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("patientName")), "%" + value.toLowerCase() + "%"));
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("disease")), "%" + value.toLowerCase()+ "%"));
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("age").as(String.class)), "%" + value.toLowerCase() + "%"));
		}
		criteriaQuery.where(criteriaBuilder.or(predicate.toArray(new Predicate[0])));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
}
