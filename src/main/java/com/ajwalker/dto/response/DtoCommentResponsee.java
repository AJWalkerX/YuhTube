package com.ajwalker.dto.response;

import com.ajwalker.entity.UserComment;

public class DtoCommentResponsee {
	private UserComment comment;
	
	public DtoCommentResponsee(UserComment comment) {
		this.comment = comment;
	}
	public UserComment getComment() {
		return comment;
	}
}