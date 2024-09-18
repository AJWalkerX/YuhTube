package com.ajwalker.dto.request;

public class DtoVideoUploadRequest {
	private String title;
	private String content;
	private String description;
	private String creatorToken;
	
	public DtoVideoUploadRequest(String title, String content, String description, String creatorToken) {
		this.title = title;
		this.content = content;
		this.description = description;
		this.creatorToken = creatorToken;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getCreatorToken() {
		return creatorToken;
	}
}