package com.jsp.spring_boot_simple_project.response;

import java.util.Map;

import lombok.Data;
@Data
public class ApiResponse {

	private String message;
	private boolean status;
	private Map<String, Object> data;
	
	public ApiResponse(String message, boolean status, Map<String, Object> data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}

	public ApiResponse(String message, boolean status,Object data) {
		this.message = message;
		this.status = status;
		this.data = Map.of("data", data);    //wrap data in map
	}

	public ApiResponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
}
