package com.ajwalker.dto.request;

public class DtoCommentRequest {
	private String comment;
	private long videoId;
	private String token;
	
	public DtoCommentRequest(long videoId, String token, String comment) {
		this.videoId = videoId;
		this.token = token;
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	public long getVideoId() {
		return videoId;
	}
	
	public String getToken() {
		return token;
	}
}