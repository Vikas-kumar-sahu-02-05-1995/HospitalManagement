package com.jsp.spring_boot_simple_project.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.spring_boot_simple_project.EcceptionHandling.ResourceNotFoundException;
import com.jsp.spring_boot_simple_project.dto.DoctorsRequest;
import com.jsp.spring_boot_simple_project.entity.Doctors;
import com.jsp.spring_boot_simple_project.entity.Medicines;
import com.jsp.spring_boot_simple_project.entity.Patients;
import com.jsp.spring_boot_simple_project.repository.DoctorsRepo;
import com.jsp.spring_boot_simple_project.repository.MedicinesRepo;
import com.jsp.spring_boot_simple_project.repository.PatientsRepo;
import com.jsp.spring_boot_simple_project.service.DoctorsService;
import com.jsp.spring_boot_simple_project.specification.DoctorsSpecifications;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class HealthCareService implements DoctorsService{

//	@PersistenceContext
//	private EntityManager entityManager;
	@Autowired
	private DoctorsSpecifications doctorsSpecifications;
	@Autowired
	private DoctorsRepo doctorsRepo;
	@Autowired
	private MedicinesRepo medicineRepo;
	@Autowired
	private PatientsRepo patientsRepo;
	@Override
	 public void saveAll(Doctors doctors) {
	        // Save the doctor first
	        doctorsRepo.save(doctors);

	        // For each patient associated with the doctor
	        if (doctors.getPatient1() != null) {
	            for (Patients patient : doctors.getPatient1()) {
	                // Set the doctor for each patient
	                patient.setDoctor(doctors);

	                // Save the patient first
	                patientsRepo.save(patient);

	                // Save all medicines associated with the patient
	                if (patient.getMedicines() != null) {
	                    for (Medicines medicine : patient.getMedicines()) {
	                        // Set the patient for each medicine
	                        medicine.setPatient(patient);
	                        // Save the medicine
	                        medicineRepo.save(medicine);
	                    }
	                }
	            }
	        }
	    }
	 @Override
	 public Doctors getByIdDoctor(int id) 
	 {
		 return doctorsRepo.findById(id)
				    .orElseThrow(() -> new ResourceNotFoundException("No doctor found with id: " + id));
	 }
	 @Override
	 public Doctors updateDoctorsDetailsById(int id,  Doctors updateDoctorDetails ) {
		 return doctorsRepo.findById(id)
				 .map(doctors -> {
					 doctors.setDoctorsName(updateDoctorDetails.getDoctorsName());
					 doctors.setAge(updateDoctorDetails.getAge());
					 doctors.setFees(updateDoctorDetails.getFees());
					 doctors.setGender(updateDoctorDetails.getGender());
					 doctors.setQualification(updateDoctorDetails.getQualification());
					 doctors.setSpecialization(updateDoctorDetails.getSpecialization());

					 doctorsRepo.save(doctors);
					 return doctors;
				 })
				 .orElseThrow(() -> new ResourceNotFoundException("No doctor found with id: " + id));
	 }
	 
	 @Override
	 public Doctors getByNameDoctors(String doctorsName) {
		    return doctorsRepo.findBydoctorsName(doctorsName)
		    		.orElseThrow(() -> new ResourceNotFoundException("No doctor found with id: " + doctorsName));
		}
	 
	 @Override
	 public Doctors deleteByDoctorsId(int id) {
		 return doctorsRepo.findById(id)
				 .map(doctors -> {
					 doctorsRepo.deleteById(id);
					 return doctors;
				 })
				 .orElseThrow(() -> new ResourceNotFoundException("No doctor found with id: " + id));
	 }

	 @Override
		public List<Doctors> getAllDoctors() {
			List<Doctors> doctors = doctorsRepo.findAll();
			if(doctors.isEmpty())
				throw new ResourceNotFoundException("No doctors found with ");
			return doctors;
		}

	@Override 
	public List<Doctors> getDoctorsBySpecialization(String specialization) {
	        List<Doctors> list = doctorsRepo.findBySpecialization(specialization);
	         if(list.isEmpty())
	        	 throw new ResourceNotFoundException("no doctors found with specilization"+specialization);
	        return list;
	}


	public Doctors convertToEntity(DoctorsRequest doctorsRequest) {
		Doctors doctors = new Doctors();
		doctors.setId(doctorsRequest.getId());
		doctors.setAge(doctorsRequest.getAge());
		doctors.setFees(doctorsRequest.getFees());
		doctors.setGender(doctorsRequest.getGender());
		doctors.setQualification(doctorsRequest.getQualification());
		doctors.setSpecialization(doctorsRequest.getSpecialization());
		return doctors;
	}
	
	public DoctorsRequest convertToResponse(Doctors doctors) {
		DoctorsRequest doctorsRequest = new DoctorsRequest();
		doctorsRequest.setId(doctors.getId());
		doctorsRequest.setAge(doctors.getAge());
		doctorsRequest.setDoctorsName(doctors.getDoctorsName());
		doctorsRequest.setFees(doctors.getFees());
		doctorsRequest.setQualification(doctors.getQualification());
		doctorsRequest.setSpecialization(doctors.getSpecialization());
		return doctorsRequest;
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Doctors> searchByMultipleFields(String value) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Doctors> query = criteriaBuilder.createQuery(Doctors.class);
		Root<Doctors> root = query.from(Doctors.class);

		List<Predicate> predicates = new ArrayList<>();

		if(value != null) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("doctorsName")), "%" + value.toLowerCase() +"%"));
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("id").as(String.class)), "%" + value.toLowerCase() + "%"));
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("age").as(String.class)), "%" + value.toLowerCase() + "%"));
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("qualification")), "%" + value.toLowerCase() + "%"));
 //			double feeValue = Double.parseDouble(value);
//			predicates.add(criteriaBuilder.between(root.get("fees"), feeValue - 10, feeValue + 100));
//			predicates.add(criteriaBuilder.between(root.get("fees"),  Double.parseDouble(value) - 10,  Double.parseDouble(value) + 100));
			predicates.add(criteriaBuilder.between(root.get("fees"),  Double.parseDouble(value) ,  Double.parseDouble(value) ));
		}
		query.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
		return entityManager.createQuery(query).getResultList();
	}

	
}
