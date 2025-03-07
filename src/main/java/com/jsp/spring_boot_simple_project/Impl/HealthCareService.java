package com.jsp.spring_boot_simple_project.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
	                patient.setDoctor1(doctors);

	                // Save the patient first
	                patientsRepo.save(patient);

	                // Save all medicines associated with the patient
	                if (patient.getMedicine1() != null) {
	                    for (Medicines medicine : patient.getMedicine1()) {
	                        // Set the patient for each medicine
	                        medicine.setPatient1(patient);
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

	@Override
	public List<Doctors> searchByMultipleFields(String value) {
		return List.of();
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
	
//
	
//	@Override
//	public List<Doctors> getListOfDoctors(Doctors doctors) {
//		Specification<Doctors> speci = doctorsSpecifications.findByCriteria(doctors);
//		
//		List<Doctors> result = doctorsRepo.findAll(speci);
//	    return result;
//	}
	
}
