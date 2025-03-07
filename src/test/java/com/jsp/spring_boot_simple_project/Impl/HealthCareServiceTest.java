package com.jsp.spring_boot_simple_project.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.jsp.spring_boot_simple_project.EcceptionHandling.ResourceNotFoundException;
import com.jsp.spring_boot_simple_project.entity.Doctors;
import com.jsp.spring_boot_simple_project.entity.Medicines;
import com.jsp.spring_boot_simple_project.entity.Patients;
import com.jsp.spring_boot_simple_project.repository.DoctorsRepo;
import com.jsp.spring_boot_simple_project.repository.MedicinesRepo;
import com.jsp.spring_boot_simple_project.repository.PatientsRepo;

@SpringBootTest
@ActiveProfiles("test") // Optional: use a test profile if needed
class HealthCareServiceTest {

	@MockBean
	private PatientsRepo patientsRepo;
	@MockBean
	private MedicinesRepo medicinesRepo;
	@MockBean
    private DoctorsRepo doctorsRepo;

    @Autowired
    private HealthCareService doctorsService;

    @Test
    public void testGetByNameDoctors_DoctorExists() {
        String doctorsName = "John Doe";
        Doctors doctor = new Doctors(); 
        doctor.setDoctorsName(doctorsName);
        when(doctorsRepo.findBydoctorsName(doctorsName)).thenReturn(Optional.of(doctor));
   
        Doctors result = doctorsService.getByNameDoctors(doctorsName);
       
        assertNotNull(result);
        assertEquals(doctorsName, result.getDoctorsName());
        verify(doctorsRepo, times(1)).findBydoctorsName(doctorsName);
    }
    @Test
    public void testdeleteByDoctorsId() {
    	int doctorId = 13;
    	Doctors doctors = new Doctors();
    	doctors.setId(doctorId);
    	when(doctorsRepo.findById(doctorId)).thenReturn(Optional.of(doctors));
    	
    	doctorsService.deleteByDoctorsId(doctorId);
    	
    	verify(doctorsRepo, times(1)).findById(doctorId);
    	verify(doctorsRepo, times(1)).deleteById(doctorId);
    }

    @Test
    public void getDoctorsBySpecialization() {
        String doctorSpecilization= "Sargan";
        Doctors d = new Doctors();
        d.setSpecialization(doctorSpecilization);
        List<Doctors> doctorlist= new ArrayList<>();
        doctorlist.add(d);
                when(doctorsRepo.findBySpecialization(doctorSpecilization)).thenReturn(doctorlist);
          
        List<Doctors> result = doctorsService.getDoctorsBySpecialization(doctorSpecilization);
          
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(doctorSpecilization, result.get(0).getSpecialization());
        verify(doctorsRepo, times(1)).findBySpecialization(doctorSpecilization); 
    }
    
    @Test
    public void testgetByIdDoctor() {
    	int doctorId = 12;
    	Doctors doctors = new Doctors();
    	doctors.setId(doctorId);		
    	when(doctorsRepo.findById(doctorId)).thenReturn(Optional.of(doctors));
    	
    	Doctors results = doctorsService.getByIdDoctor(doctorId);
    	
    	assertNotNull(results);
    	assertEquals(doctorId, results.getId());
    	verify(doctorsRepo, times(1)).findById(doctorId);
    }
 
    @Test
    public void testSaveAll_DoctorWithPatientsAndMedicines() {
        // Arrange
        Doctors doctor = new Doctors();
        doctor.setDoctorsName("Dr. John");

        Patients patient1 = new Patients();
        patient1.setPatientName("Patient A");

        Patients patient2 = new Patients();
        patient2.setPatientName("Patient B");

        Medicines medicine1 = new Medicines();
        medicine1.setMedicineName("Medicine X");

        Medicines medicine2 = new Medicines();
        medicine2.setMedicineName("Medicine Y");

        // Set relationships
        patient1.setMedicine1(List.of(medicine1));
        patient2.setMedicine1(List.of(medicine2));
        doctor.setPatient1(List.of(patient1, patient2));

        // Act
        doctorsService.saveAll(doctor);

        // Assert and Verify Interactions
        verify(doctorsRepo, times(1)).save(doctor);

        // Verify each patient is saved with the associated doctor
        verify(patientsRepo, times(1)).save(patient1);
        verify(patientsRepo, times(1)).save(patient2);
        assertEquals(doctor, patient1.getDoctor1());
        assertEquals(doctor, patient2.getDoctor1());

        // Verify medicines are saved with associated patients
        verify(medicinesRepo, times(1)).save(medicine1);
        verify(medicinesRepo, times(1)).save(medicine2);
        assertEquals(patient1, medicine1.getPatient1());
        assertEquals(patient2, medicine2.getPatient1());
    }
    
    @Test
    public void testUpdateDoctorsDetailsById_DoctorExists() {
        int doctorId = 1;
        Doctors existingDoctor = new Doctors();
        existingDoctor.setId(doctorId);
        existingDoctor.setDoctorsName("Old Name");
        existingDoctor.setAge(45);
        existingDoctor.setFees(300);
        existingDoctor.setGender("Male");
        existingDoctor.setQualification("MBBS");
        existingDoctor.setSpecialization("Cardiology");

        Doctors updatedDoctorDetails = new Doctors();
        updatedDoctorDetails.setDoctorsName("Updated Name");
        updatedDoctorDetails.setAge(50);
        updatedDoctorDetails.setFees(350);
        updatedDoctorDetails.setGender("Male");
        updatedDoctorDetails.setQualification("MD");
        updatedDoctorDetails.setSpecialization("Neurology");

        when(doctorsRepo.findById(doctorId)).thenReturn(Optional.of(existingDoctor));
        when(doctorsRepo.save(any(Doctors.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Doctors result = doctorsService.updateDoctorsDetailsById(doctorId, updatedDoctorDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getDoctorsName());
        assertEquals(50, result.getAge());
        assertEquals(350, result.getFees());
        assertEquals("Male", result.getGender());
        assertEquals("MD", result.getQualification());
        assertEquals("Neurology", result.getSpecialization());
        verify(doctorsRepo, times(1)).findById(doctorId);
        verify(doctorsRepo, times(1)).save(existingDoctor);
    }

//    @Mock
//    private DoctorsRepo doctorsRepo;
//
//    @InjectMocks
//    private HealthCareService careService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//	
//	@Test
//	public void testGetByNameDoctors() {
//		String doctorName= "amit";
//		Doctors doctors = new Doctors();
//		doctors.setDoctorsName(doctorName);
//		when(doctorsRepo.findBydoctorsName(doctorName)).thenReturn(Optional.of(doctors));
//	    
//		Doctors result = careService.getByNameDoctors(doctorName);
//		
//		assertNotNull(result);
//		assertEquals(doctorName, result.getDoctorsName());
//		verify(doctorsRepo, times(1)).findBydoctorsName(doctorName);
//	}
//	  @Test
//	    public void testGetByNameDoctors_DoctorDoesNotExist() {
//	        // Arrange
//	        String doctorsName = "Unknown Doctor";
//	        when(doctorsRepo.findBydoctorsName(doctorsName)).thenReturn(Optional.empty());
//
//	        // Act & Assert
//	        ResourceNotFoundException exception = assertThrows(
//	            ResourceNotFoundException.class,
//	            () -> careService.getByNameDoctors(doctorsName)
//	        );
//
//	        assertEquals("No doctor found with id: " + doctorsName, exception.getMessage());
//	        verify(doctorsRepo, times(1)).findBydoctorsName(doctorsName);
//	    }
//    @Test
//    public void testGetByNameDoctors_DoctorDoesNotExist() {
//        // Arrange
//        String doctorsName = "Unknown Doctor";
//        when(doctorsRepo.findBydoctorsName(doctorsName)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        ResourceNotFoundException exception = assertThrows(
//            ResourceNotFoundException.class,
//            () -> doctorsService.getByNameDoctors(doctorsName)
//        );
//
//        assertEquals("No doctor found with id: " + doctorsName, exception.getMessage());
//        verify(doctorsRepo, times(1)).findBydoctorsName(doctorsName);
//    }
	
}
