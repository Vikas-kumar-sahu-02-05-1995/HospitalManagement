package com.jsp.spring_boot_simple_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.spring_boot_simple_project.entity.Doctors;
@Service
public interface DoctorsService {

	 public void saveAll(Doctors doctors) ;
	 
	 public Doctors getByIdDoctor(int id) ;
	 
	 public Doctors updateDoctorsDetailsById(int id,  Doctors updateDoctorDetails );
	 
	 public Doctors getByNameDoctors(String doctorsName) ;
	 
	 public Doctors deleteByDoctorsId(int id) ;
	 
	 public List<Doctors> getAllDoctors();
	 
	 public List<Doctors> getDoctorsBySpecialization(String specialization) ;
	 
	 public List<Doctors> searchByMultipleFields(String value);
	 
//	 public List<Doctors> getListOfDoctors(Doctors doctors) ;
}
