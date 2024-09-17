package com.ajwalker.dto.response;




public class DtoVideoThumbnailResponse {
	Long videoId;
	String videoTitle;
	String creatorUsername;
	
	public DtoVideoThumbnailResponse(String videoTitle, String creatorUsername, Long videoId) {
		this.videoTitle = videoTitle;
		this.creatorUsername = creatorUsername;
		this.videoId = videoId;
	}
	
	@Override
	public String toString() {
		return "Title: " + videoTitle + "\nby " + creatorUsername;
	}
	
	public Long getVideoId() {
		return videoId;
	}
	
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	
	public String getVideoTitle() {
		return videoTitle;
	}
	
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	
	public String getCreatorUsername() {
		return creatorUsername;
	}
	
	public void setCreatorUsername(String creatorUsername) {
		this.creatorUsername = creatorUsername;
	}
}