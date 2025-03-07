package com.jsp.spring_boot_simple_project.Controller;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(HealthCareController.class)
public class HealthCareControllerTest {

    @Autowired
    private MockMvc mockMvc;


   
    public void testSaveAllController()  {
//        Doctors doctors = new Doctors();
//        doctors.setId(1);
//        doctors.setDoctorsName("Dr. Smith");
//        doctors.setAge(24);
//        doctors.setFees(500.5);
//        doctors.setGender("Male");
//        doctors.setQualification("MD");
//        doctors.setSpecialization("dental");;
//
//        Patients patient = new Patients();
//        patient.setPatientId(11);
//        patient.setPatientName("John Doe");
//        patient.setAddress("sector-23");
//        patient.setDisease("dengu");
//        patient.setAge(56);
//        patient.setGender("male");
//
//        Medicines medicine = new Medicines();
//        medicine.setMedicineId(21);
//        medicine.setMedicineName("Medicine A");
//        medicine.setExpDate(LocalDate.of(2025, 1, 1));
//        medicine.setMfeDate(LocalDate.parse("2023-01-01"));
//        medicine.setQuantity(34);
//        medicine.setRate(10);
//
//        patient.setMedicine1(List.of(medicine));
//        doctors.setPatient1(List.of(patient));
//
//        // Convert the Doctors object to JSON
//        String doctorsJson =objectMapper.writeValueAsString(doctors);
//
//        mockMvc.perform(post("/doctor/saveAllData")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(doctorsJson))
//                .andExpect(status().isOk())
//                .andExpect(content().string(org.hamcrest.Matchers.containsString("data saved successfully")));
//
//
//        verify(healthCareService, times(1)).saveAll(doctors);
    }


	
	public void test_getByIdDoctorController(){
		
	}
	
	public void test_updateDoctorsDetailsByIdController(){
		
	}
	
	
	public void test_deleteByDoctorsIdController(){
		
	}
	
	public void test_getByNameDoctorsController(){
		
	}
	
	@Test
	void test_getAllDoctorsController() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/doctor/getAllDocotrs")
				.accept(MediaType.APPLICATION_JSON))
		   .andDo(print())
		   .andExpect(status().isOk())
		   .andExpect(MockMvcResultMatchers.jsonPath("$.Docotrs").exists())
		   .andExpect(MockMvcResultMatchers.jsonPath("$.Doctors[*].id").isNotEmpty());
	}
	
	
	public void test_getDoctorsBySpecialization() {
		
	}
}
