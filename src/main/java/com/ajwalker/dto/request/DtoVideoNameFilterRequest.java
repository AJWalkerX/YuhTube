package com.ajwalker.dto.request;

public class DtoVideoNameFilterRequest {
	private String filter;
	
	public DtoVideoNameFilterRequest(String filter) {
		this.filter = filter;
	}
	
	public String getFilter() {
		return filter;
	}
}