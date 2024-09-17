package com.ajwalker.dto.response;

import com.ajwalker.entity.Video;

public class DtoVideoDetailed {
	private Video video;
	private Long likeCount;
	private Long dislikeCount;
	private Long commentCount;
	
	
	public DtoVideoDetailed(Video video, Long likeCount, Long dislikeCount, Long commentCount) {
		this.video = video;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.commentCount = commentCount;
	}
	
	public Video getVideo() {
		return video;
	}
	
	public Long getLikeCount() {
		return likeCount;
	}
	
	public Long getDislikeCount() {
		return dislikeCount;
	}
	
	public Long getCommentCount() {
		return commentCount;
	}
}