//package com.jsp.spring_boot_simple_project.model;
//
//import java.util.Collection;
//import java.util.Collections;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.jsp.spring_boot_simple_project.entity.Patients;
//
//public class PatientsPrinciple implements UserDetails {
//
//private Patients patients;
//	
//	public PatientsPrinciple(Patients patients) {
//		this.patients = patients ;
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return Collections.singleton(new SimpleGrantedAuthority("PATIENTS"));
//	}
//
//	@Override
//	public String getPassword() {
//		return patients.getDisease();
//	}
//
//	@Override
//	public String getUsername() {
//		return patients.getPatientName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//}
