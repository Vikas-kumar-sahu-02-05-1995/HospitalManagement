package com.jsp.spring_boot_simple_project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jsp.spring_boot_simple_project.entity.Doctors;
@Repository
public interface DoctorsRepo extends JpaRepository<Doctors, Integer>, JpaSpecificationExecutor<Doctors> {

	Optional<Doctors> findBydoctorsName(String doctorsName);
	
	  List<Doctors> findBySpecialization(String specialization);
}
