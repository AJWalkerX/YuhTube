package com.ajwalker.service;

import com.ajwalker.dto.request.DtoTokenRequest;
import com.ajwalker.dto.request.DtoVideoNameFilterRequest;
import com.ajwalker.dto.request.DtoVideoUploadRequest;
import com.ajwalker.dto.response.DtoVideoThumbnailResponse;
import com.ajwalker.entity.Like;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;
import com.ajwalker.dto.response.DtoVideoDetailed;
import com.ajwalker.repository.TrendingRepository;
import com.ajwalker.repository.VideoRepository;
import com.ajwalker.utility.PopularityAction;

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
        Optional<User> optUser = UserService.getInstance().getUserByToken(token);
        if (optUser.isEmpty()) throw new RuntimeException("No such user in db by token(service)");
        User user = optUser.get();
        List<Video> videos = videoRepository.findByCreatorId(user.getId());
        return videoToDto(videos);
    }
	
	public DtoVideoDetailed generateVideoModel(Video video) {
        return new DtoVideoDetailed(video);
        
	}
    
    public List<DtoVideoThumbnailResponse> findTrending20(){
        /*List<Video> trending20 = TrendingRepository.getInstance().findTrending20();
        return videoToDto(trending20);*/
        List<Video> trending20 = videoRepository.findTrending20();
        return videoToDto(trending20);
    }
    
    public void watched(Video video) {
        video.setViewCount(video.getViewCount() + 1);
        video.setPopularityIndex(video.getPopularityIndex() + 0.6f);
        update(video);
    }
    
    public void updatePopularityIndex(Video video, Like like, PopularityAction popularityAction) {
        switch(like.getState()){
            case 1:
                video.setLikeCount(video.getLikeCount() - 1);
                video.setPopularityIndex(video.getPopularityIndex() - 0.3f);
                break;
            case -1:
                video.setDislikeCount(video.getDislikeCount() -1);
                video.setPopularityIndex(video.getPopularityIndex() + 0.3f);
                break;
        }
        
        switch (popularityAction){
            case LIKE:
                video.setLikeCount(video.getLikeCount() + 1);
                video.setPopularityIndex(video.getPopularityIndex() + 0.3f);
                break;
            case DISLIKE:
                video.setDislikeCount(video.getLikeCount() + 1);
                video.setPopularityIndex(video.getPopularityIndex() - 0.3f);
                break;
        }
        update(video);
    }
    
    public void uploadVideo(DtoVideoUploadRequest videoUploadRequest) {
        String title = videoUploadRequest.getTitle();
        String content = videoUploadRequest.getContent();
        String description = videoUploadRequest.getDescription();
        String token = videoUploadRequest.getCreatorToken();
        Optional<User> optUser = UserService.getInstance().getUserByToken(token);
        if (optUser.isEmpty()) throw new RuntimeException("No such user in db by token(service)");
        User user = optUser.get();
        Long creatorId = user.getId();
        Video video = new Video(creatorId, title, content, description);
        save(video);
    }
}