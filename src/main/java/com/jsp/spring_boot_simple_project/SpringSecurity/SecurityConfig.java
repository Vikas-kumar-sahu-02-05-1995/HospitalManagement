//package com.jsp.spring_boot_simple_project.SpringSecurity;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
////@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//	
//
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.
//		     authorizeRequests()
//		         .requestMatchers("/patients/getAllPatients","doctor/getAllDocotrs",
//		        		 "doctor/getListOfDoctorBySpecilization/{specialization}"
//		        		 ,"patients/getBypatientId/{patientId}","/login").permitAll()
//		         .anyRequest().authenticated()
//		         .and()
//		         .oauth2Login();
//		
//	}
//
//	
//}
