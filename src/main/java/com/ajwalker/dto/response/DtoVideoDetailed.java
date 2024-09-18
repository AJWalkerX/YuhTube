package com.ajwalker.dto.response;

import com.ajwalker.entity.Video;

public class DtoVideoDetailed {
	private Video video;
	
	public DtoVideoDetailed(Video video) {
		this.video = video;
	}
	
	public Video getVideo() {
		return video;
	}
	
}