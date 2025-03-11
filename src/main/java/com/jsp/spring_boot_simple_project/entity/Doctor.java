//package com.jsp.spring_boot_simple_project.entity;
//
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.SequenceGenerator;
//import lombok.Data;
//
//@Data
//@Entity
//public class Doctor  {
//
//    @Id
//    @SequenceGenerator(sequenceName = "seq_id", name = "ids", initialValue = 1000, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ids")
//    private int id;
//    private String doctorName;
//    private String gender;
//    private int age;
//    private double fees;
//    private String qualification;
//    private String specility;
//
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Patient> patients;
//
//
//
//
//
//}
