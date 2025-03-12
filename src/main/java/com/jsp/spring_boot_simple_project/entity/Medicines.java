package com.jsp.spring_boot_simple_project.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Medicines {

	@Id
	private int medicineId;
    private String medicineName;
    private LocalDate mfeDate;
    private LocalDate expDate;
    private double rate;
    private long quantity;


    @ManyToOne
    private Patients patient;
    
}
