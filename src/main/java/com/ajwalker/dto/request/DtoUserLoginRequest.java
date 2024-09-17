package com.ajwalker.dto.request;

public class DtoUserLoginRequest {
	private String username;
	private String password;
	
	public DtoUserLoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}