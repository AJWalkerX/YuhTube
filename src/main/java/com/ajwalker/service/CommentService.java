package com.ajwalker.service;

import com.ajwalker.dto.request.DtoCommentRequest;
import com.ajwalker.dto.request.DtoVideoToken;
import com.ajwalker.dto.response.DtoCommentResponsee;
import com.ajwalker.entity.User;
import com.ajwalker.entity.UserComment;
import com.ajwalker.entity.Video;
import com.ajwalker.repository.CommentRepository;
import com.ajwalker.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

public class CommentService {
    private static final CommentRepository commentRepository = CommentRepository.getInstance();
    private static CommentService instance;
    
    private CommentService() {
    }
    
    public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }
    
    public boolean save(UserComment userComment) {
        return commentRepository.save(userComment);
    }
    
    public boolean update(UserComment userComment) {
        return commentRepository.update(userComment);
    }
    
    public boolean delete(Long id) {
        return commentRepository.delete(id);
    }
    
    public List<UserComment> findAll() {
        return commentRepository.findAll();
    }
    
    public Optional<UserComment> findById(Long id) {
        return commentRepository.findById(id);
    }
    
    public void comment(DtoCommentRequest commentRequest) {
        Long videoId = commentRequest.getVideoId();
        Optional<User> optUser = UserService.getInstance().getUserByToken(commentRequest.getToken());
        if (optUser.isEmpty()) throw new RuntimeException("Invalid token");
        String comment = commentRequest.getComment();
        Long userId = optUser.get().getId();
        UserComment userComment = new UserComment(videoId, userId, comment);
        Optional<Video> optVideo = VideoRepository.getInstance().findById(videoId);
        if (optVideo.isEmpty()) throw new RuntimeException("Invalid video(service)...");
        Video video = optVideo.get();
        video.setPopularityIndex(video.getPopularityIndex() + 0.1);
        save(userComment);
    }
    
    public Long countComment(Long videoId) {
        return commentRepository.findByVideoId(videoId).stream().filter(comment -> comment.getState() == 1).count();
    }
    
    public List<DtoCommentResponsee> findAllComment(DtoVideoToken videoToken) {
        Long videoId = videoToken.getVideoId();
        List<UserComment> comments = commentRepository.findByVideoId(videoId);
        List<DtoCommentResponsee> commentResponse = comments.stream().map(comment -> new DtoCommentResponsee(comment)).toList();
        return commentResponse;
        
    }
    
}