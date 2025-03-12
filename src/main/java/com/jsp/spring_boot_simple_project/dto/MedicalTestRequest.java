package com.jsp.spring_boot_simple_project.dto;

import lombok.Data;

@Data
public class MedicalTestRequest {

    private Long id;
    private String testName;
    private String testDate;
    private String result;
}
