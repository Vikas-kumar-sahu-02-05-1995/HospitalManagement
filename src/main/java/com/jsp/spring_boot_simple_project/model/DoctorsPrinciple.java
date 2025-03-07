//package com.jsp.spring_boot_simple_project.model;
//
//import java.util.Collection;
//import java.util.Collections;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.jsp.spring_boot_simple_project.entity.Doctors;
//
//public class DoctorsPrinciple implements UserDetails {
//
//	private Doctors doctors;
//	
//	public DoctorsPrinciple(Doctors doctors) {
//		this.doctors = doctors;
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return Collections.singleton(new SimpleGrantedAuthority("DOCTORS"));
//	}
//
//	@Override
//	public String getPassword() {
//		return doctors.getQualification();
//	}
//
//	@Override
//	public String getUsername() {
//		return doctors.getDoctorsName();
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
