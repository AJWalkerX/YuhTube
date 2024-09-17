package com.ajwalker.service;

import com.ajwalker.dto.request.DtoTokenRequest;
import com.ajwalker.dto.request.DtoVideoNameFilterRequest;
import com.ajwalker.dto.response.DtoVideoThumbnailResponse;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;
import com.ajwalker.dto.response.DtoVideoDetailed;
import com.ajwalker.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

public class VideoService {
    private final static VideoRepository videoRepository = VideoRepository.getInstance();
    private static VideoService instance;

    private VideoService(){
    }

    public static VideoService getInstance() {
        if (instance == null){
            instance = new VideoService();
        }
        return instance;
    }

    public boolean save(Video video) {
        return videoRepository.save(video);
    }

    public boolean update(Video video) {
        return videoRepository.update(video);
    }

    public boolean delete(Long id) {
        return videoRepository.delete(id);
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Optional<Video> findById(Long id) {
        return videoRepository.findById(id);
    }
    
    public List<DtoVideoThumbnailResponse> showAllVideos() {
        List<Video> all = findAll();
        return videoToDto(all);
    }
    
    public List<DtoVideoThumbnailResponse> showByName(DtoVideoNameFilterRequest filterRequest) {
        String videoTitle = filterRequestToVideoTitle(filterRequest);
        List<Video> videos = videoRepository.findByTitle(videoTitle);
        return videoToDto(videos);
    }
    
    private String filterRequestToVideoTitle(DtoVideoNameFilterRequest filterRequest) {
        return filterRequest.getFilter();
    }
    
    public List<DtoVideoThumbnailResponse> videoToDto(List<Video> all){
        return all.stream().map(
                video -> {
                    Optional<User> optCreator = UserService.getInstance().findById(video.getCreator_id());
                    String creatorUsername;
                    if (optCreator.isPresent()) {
                        creatorUsername = optCreator.get().getUsername();
                        return new DtoVideoThumbnailResponse(video.getTitle(), creatorUsername, video.getId());
                    }
                    else return null;
                }).toList();
    }
    
    public List<DtoVideoThumbnailResponse> showMyVideos(DtoTokenRequest tokenRequest) {
        String token = tokenRequest.getToken();
        Optional<User> optUser = UserService.getInstance().getUserIdByToken(token);
        if (optUser.isEmpty()) throw new RuntimeException("No such user in db by token(service)");
        User user = optUser.get();
        List<Video> videos = videoRepository.findByCreatorId(user.getId());
        return videoToDto(videos);
    }
	
	public DtoVideoDetailed generateVideoModel(Video video) {
        Long likeCount = LikeService.getInstance().countLikes(video.getId());
        Long dislikeCount = LikeService.getInstance().countDislikes(video.getId());
        Long commentCount = CommentService.getInstance().countComment(video.getId());
        
        return new DtoVideoDetailed(video, likeCount, dislikeCount, commentCount);
        
	}
    
    public void watched(Video video) {
        video.setViewCount(video.getViewCount() + 1);
        update(video);
    }
}