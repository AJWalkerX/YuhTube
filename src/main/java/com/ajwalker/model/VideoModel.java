package com.ajwalker.model;

import com.ajwalker.dto.response.DtoVideoDetailed;



public class VideoModel {
	private DtoVideoDetailed dtoVideoDetailed;
	
	public VideoModel(DtoVideoDetailed dtoVideoDetailed) { //
		this.dtoVideoDetailed = dtoVideoDetailed;
	}
	
	public void videoStatistics(){
		System.out.println("### Video Statistics ###");
		System.out.println("likes: " + dtoVideoDetailed.getLikeCount());
		System.out.println("dislikes: " + dtoVideoDetailed.getDislikeCount());
		System.out.println("comments: " + dtoVideoDetailed.getCommentCount());
		System.out.println("view count: " + dtoVideoDetailed.getVideo().getViewCount());
	}
	public DtoVideoDetailed getDtoVideoDetailed() {
		return dtoVideoDetailed;
	}
}