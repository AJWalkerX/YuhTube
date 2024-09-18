package com.ajwalker.dto.response;

public class DtoUserLoginResponse {
	
	private String token;
	
	public DtoUserLoginResponse(String username) {
		this.token = username;
	}
	
	public String getUsername() {
		return token;
	}
}