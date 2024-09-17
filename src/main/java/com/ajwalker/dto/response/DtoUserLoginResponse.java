package com.ajwalker.dto.response;

public class DtoUserLoginResponse {
	
	private String username;
	
	public DtoUserLoginResponse(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}