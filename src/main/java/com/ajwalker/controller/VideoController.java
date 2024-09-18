package com.ajwalker.controller;

import com.ajwalker.dto.request.DtoTokenRequest;
import com.ajwalker.dto.request.DtoVideoNameFilterRequest;
import com.ajwalker.dto.request.DtoVideoToken;
import com.ajwalker.dto.request.DtoVideoUploadRequest;
import com.ajwalker.dto.response.DtoVideoThumbnailResponse;
import com.ajwalker.entity.Video;
import com.ajwalker.dto.response.DtoVideoDetailed;
import com.ajwalker.service.VideoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoController {
    private final static VideoService videoService = VideoService.getInstance();
    private static VideoController instance;

    private VideoController(){
    }

    public static VideoController getInstance() {
        if (instance == null){
            instance = new VideoController();
        }
        return instance;
    }

    public boolean save(Video video) {
        return videoService.save(video);
    }

    public boolean update(Video video) {
        return videoService.update(video);
    }

    public boolean delete(Long id) {
        return videoService.delete(id);
    }

    public List<Video> findAll() {
        return videoService.findAll();
    }

    public Optional<Video> findById(Long id) {
        return videoService.findById(id);
    }
    
    public List<DtoVideoThumbnailResponse> showAllVideos() {
	    try {
		   return videoService.showAllVideos();
	    }
	    catch (Exception e) {
            System.out.println("could not show all videos(controller)..." + e.getMessage());
            return new ArrayList<>();
	    }
    }
    
    public List<DtoVideoThumbnailResponse> showByName(DtoVideoNameFilterRequest filterRequest) {
        try {
            return videoService.showByName(filterRequest);
        }
        catch(Exception e){
            System.out.println("could not show the videos by title(controller)..." + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    
    public List<DtoVideoThumbnailResponse> showMyVideos(DtoTokenRequest tokenRequest) {
        try{
            return videoService.showMyVideos(tokenRequest);
        }
        catch (Exception e) {
            System.out.println("Could not show your videos(controller)..." + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public List<DtoVideoThumbnailResponse> findTrending20(){
        try{
            return videoService.findTrending20();
        }
        catch (Exception e) {
            System.out.println("could not show trending20(controller)..." + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public DtoVideoDetailed generateVideoModel(Video video) {
        try{
            return videoService.generateVideoModel(video);
        }
        catch (Exception e) {
            System.out.println("could not generate video model(controller)..." + e.getMessage());
        }
       return null;
    }
    
    public void watched(Video video) {
        try{
            videoService.watched(video);
        }catch (Exception e){
            System.out.println("could not watch video(controller)..." + e.getMessage());
        }
    }
    
    
    public void uploadVideo(DtoVideoUploadRequest videoUploadRequest) {
        try{
            videoService.uploadVideo(videoUploadRequest);
        }
        catch (Exception e) {
            System.out.println("could not upload video(controller)..." + e.getMessage());
        }
    }
}