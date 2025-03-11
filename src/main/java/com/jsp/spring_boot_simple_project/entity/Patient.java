//package com.jsp.spring_boot_simple_project.entity;
//
//import java.util.List;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.SequenceGenerator;
//import lombok.Data;
//
//@Data
//@Entity
//public class Patient {
//
//	@Id
//	 @SequenceGenerator(sequenceName = "pat_id", name = "id_pat", initialValue = 100, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_pat")
//	private int patientId;
//	private String patientName;
//	private String disease;
//	private String gender;
//	private int age;
//	private String address;
//
//	  @ManyToMany(mappedBy = "patients",fetch = FetchType.LAZY)
////	    @JoinTable(
////	        name = "patient_doctor",
////	        joinColumns  = @JoinColumn(name = "patient_id"),
////	        inverseJoinColumns = @JoinColumn(name = "doctor_id")
////	    )
//	    private List<Doctor> doctors;
//
////	    @OneToMany(mappedBy = "patient")
////	    private List<Medicine> medicines;
//
//
//}
