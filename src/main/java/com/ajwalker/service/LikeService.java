package com.ajwalker.service;

import com.ajwalker.database.DatabaseHelper;
import com.ajwalker.dto.request.DtoLikeRequest;
import com.ajwalker.entity.Like;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;
import com.ajwalker.repository.LikeRepository;
import com.ajwalker.utility.ICRUD;

import java.util.List;
import java.util.Optional;

public class LikeService {
    private static final LikeRepository likeRepository = LikeRepository.getInstance();
    private static LikeService instance;

    private LikeService() {}

    public static LikeService getInstance() {
        if (instance == null) {
            instance = new LikeService();
        }
        return instance;
    }

    public boolean save(Like like) {
        return likeRepository.save(like);
    }

    public boolean update(Like like) {
        return likeRepository.update(like);
    }

    public boolean delete(Long id) {
        return likeRepository.delete(id);
    }

    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    public Optional<Like> findById(Long id) {
        return likeRepository.findById(id);
    }
    
    public void likeTheVideo(DtoLikeRequest likeRequest) {
        Long videoId = likeRequest.getVideoId();
        Optional<User> optUser = UserService.getInstance().getUserIdByToken(likeRequest.getToken());
        if (optUser.isEmpty()) throw new RuntimeException("Invalid token...(service)");
        Long userId = optUser.get().getId();
        Optional<Like> optLike = findByVideoAndUser(videoId, userId);
        if (optLike.isPresent()) {
            Like like = optLike.get();
            like.setState(1);
            update(like);
        }
        else {
            save(new Like(userId, videoId, 1));
        }
    }
    
    public Optional<Like> findByVideoAndUser(Long videoId, Long userId) {
        return likeRepository.findByVideoAndUserId(videoId, userId);
    }
    
    public void dislikeTheVideo(DtoLikeRequest likeRequest) {
        Long videoId = likeRequest.getVideoId();
        Optional<User> optUser = UserService.getInstance().getUserIdByToken(likeRequest.getToken());
        if (optUser.isEmpty()) throw new RuntimeException("Invalid token...(service)");
        Long userId = optUser.get().getId();
        Optional<Like> optLike = findByVideoAndUser(videoId, userId);
        if (optLike.isPresent()) {
            Like like = optLike.get();
            like.setState(-1);
            update(like);
        }
        else {
            save(new Like(userId, videoId, -1));
        }
    }
    
    public void softDeleteLike(DtoLikeRequest likeRequest) {
        Long videoId = likeRequest.getVideoId();
        Optional<User> optUser = UserService.getInstance().getUserIdByToken(likeRequest.getToken());
        if (optUser.isEmpty()) throw new RuntimeException("Invalid token...(service)");
        Long userId = optUser.get().getId();
        Optional<Like> optLike = findByVideoAndUser(videoId, userId);
        if (optLike.isPresent()) {
            Like like = optLike.get();
            like.setState(0);
            update(like);
        }
    }
    
    public Long countLikes(Long videoId) {
        return likeRepository.findByVideoId(videoId).stream().filter(like -> like.getState() == 1).count();
    }
    
    public Long countDislikes(Long videoId) {
        return likeRepository.findByVideoId(videoId).stream().filter(like -> like.getState() == -1).count();
    }
    
}