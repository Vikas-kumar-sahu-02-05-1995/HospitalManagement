package com.jsp.spring_boot_simple_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.spring_boot_simple_project.entity.Medicines;
@Repository
public interface MedicinesRepo extends JpaRepository<Medicines, Integer> {


}
