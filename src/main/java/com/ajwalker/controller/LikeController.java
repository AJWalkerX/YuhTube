package com.ajwalker.controller;

import com.ajwalker.dto.request.DtoLikeRequest;
import com.ajwalker.entity.Like;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;
import com.ajwalker.repository.LikeRepository;
import com.ajwalker.service.LikeService;

import java.util.List;
import java.util.Optional;

public class LikeController {
    private static final LikeService likeService = LikeService.getInstance();
    private static LikeController instance;

    private LikeController() {}

    public static LikeController getInstance() {
        if (instance == null) {
            instance = new LikeController();
        }
        return instance;
    }

    public boolean save(Like like) {
        return likeService.save(like);
    }

    public boolean update(Like like) {
        return likeService.update(like);
    }

    public boolean delete(Long id) {
        return likeService.delete(id);
    }

    public List<Like> findAll() {
        return likeService.findAll();
    }

    public Optional<Like> findById(Long id) {
        return likeService.findById(id);
    }
    
    public void likeTheVideo(DtoLikeRequest likeRequest) {
	    try {
		    likeService.likeTheVideo(likeRequest);
	    }
	    catch (Exception e) {
            System.out.println("could not like the video(controller)..." + e.getMessage());
	    }
    }
    
    
    public void dislikeTheVideo(DtoLikeRequest likeRequest) {
        try {
            likeService.dislikeTheVideo(likeRequest);
        }
        catch (Exception e) {
            System.out.println("could not like the video(controller)..." + e.getMessage());
        }
    }
    
    public void softDeleteLike(DtoLikeRequest likeRequest) {
        try {
            likeService.softDeleteLike(likeRequest);
        }
        catch (Exception e) {
            System.out.println("could not like the video(controller)..." + e.getMessage());
        }
    }
}