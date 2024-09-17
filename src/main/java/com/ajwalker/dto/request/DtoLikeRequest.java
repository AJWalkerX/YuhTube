package com.ajwalker.dto.request;

import java.util.Optional;

public class DtoLikeRequest {
	private String token;
	private Long videoId;
	public DtoLikeRequest(Long videoId, String token) {
		this.videoId = videoId;
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
	public Long getVideoId() {
		return videoId;
	}
}