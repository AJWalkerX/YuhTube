package com.ajwalker.dto.request;

public class DtoVideoToken {
	private Long videoId;
	
	public DtoVideoToken(Long videoId) {
		this.videoId = videoId;
	}
	public Long getVideoId() {
		return videoId;
	}
}