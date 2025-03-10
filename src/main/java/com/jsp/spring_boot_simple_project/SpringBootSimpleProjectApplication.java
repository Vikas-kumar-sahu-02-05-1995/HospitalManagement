package com.jsp.spring_boot_simple_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSimpleProjectApplication  {    //implements CommandLineRunner

 public static void main(String[] args) {
		SpringApplication.run(SpringBootSimpleProjectApplication.class, args);
		System.out.println("server started");
	}
}