package com.ajwalker.controller;

import com.ajwalker.dto.request.DtoCommentRequest;
import com.ajwalker.dto.request.DtoVideoToken;
import com.ajwalker.dto.response.DtoCommentResponsee;
import com.ajwalker.entity.User;
import com.ajwalker.entity.UserComment;
import com.ajwalker.entity.Video;
import com.ajwalker.service.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentController {
    private CommentService commentService = CommentService.getInstance();
    private static CommentController instance;

    private CommentController() {}

    public static CommentController getInstance() {
        if (instance == null) {
            instance = new CommentController();
        }
        return instance;
    }

    public boolean save(UserComment userComment) {
        return commentService.save(userComment);
    }

    public boolean update(UserComment userComment) {
        return commentService.update(userComment);
    }

    public boolean delete(Long id) {
        return commentService.delete(id);
    }

    public List<UserComment> findAll() {
        return commentService.findAll();
    }

    public Optional<UserComment> findById(Long id) {
        return commentService.findById(id);
    }
	
	public void comment(DtoCommentRequest commentRequest) {
		try {
			commentService.comment(commentRequest);
		}
		catch (Exception e) {
            System.out.println("cannot comment(controller)... " + e.getMessage());
		}
	}
	
	public List<DtoCommentResponsee> readComments(DtoVideoToken dtoVideoToken) {
		try{
			return commentService.findAllComment(dtoVideoToken);
		}
		catch (Exception e) {
			System.out.println("cannot readComments(controller)... " + e.getMessage());
			return new ArrayList<>();
		}
	}
}