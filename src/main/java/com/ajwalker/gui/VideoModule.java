package com.ajwalker.gui;

import com.ajwalker.controller.VideoController;
import com.ajwalker.dto.request.DtoTokenRequest;
import com.ajwalker.dto.request.DtoVideoNameFilterRequest;
import com.ajwalker.dto.request.DtoVideoUploadRequest;
import com.ajwalker.dto.response.DtoVideoThumbnailResponse;
import com.ajwalker.entity.Video;
import com.ajwalker.dto.response.DtoVideoDetailed;
import com.ajwalker.model.VideoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class VideoModule {
	private Optional<String> token;
	private Scanner scanner = new Scanner(System.in);
	private VideoController videoController = VideoController.getInstance();
	private List<DtoVideoThumbnailResponse> videosToWatch = new ArrayList<>();
	
	private Integer choice(){ //TODO alt katmandan üst katmana ilerleme, yeni bir sınıf aç choice'ı oraya koy
		return MainMenu.getInstance().choice();
	}
	
	public Optional<String> videoModule(Optional<String> token) {
		this.token = token;
		int opt;
		do{
			System.out.println("""
					                   YuhTube
					                   Videos
					                   1. Show All Videos
					                   2. Filter By Title
					                   3. Show My Videos
					                   4. What's Trending
					                   5. Choose Video To Watch
					                   6. Upload Video
					                   0. Go Back
					                   
					                   """);
			opt = mainMenuOptions(choice());
		}while(opt != 0);
		return token;
	}
	
	private int mainMenuOptions(int choice) {
		switch (choice){
			case 1:
				videosToWatch = showAllVideos();
				break;
			case 2:
				videosToWatch = filterByTitle();
				break;
			case 3:
				if (token.isEmpty()){
					System.out.println("You must log in to see your videos");
					token = MainMenu.getInstance().login();
					if(token.isEmpty()){
						System.out.println("Cannot see your videos since you have not logged in");
						break;
					}
				}
				videosToWatch = showMyVideos();
				break;
			case 4:
				videosToWatch = findTrending20();
				break;
			case 5:
				chooseVideoToWatch();
				break;
			case 6:
				if (token.isEmpty()){
					System.out.println("You must log in to see your videos");
					token = MainMenu.getInstance().login();
					if(token.isEmpty()){
						System.out.println("Cannot see your videos since you have not logged in");
						break;
					}
				}
				uploadVideo();
				break;
			default:
				System.out.println("Invalid option!");
		}
		
		return choice;
	}
	
	private void uploadVideo() {
		System.out.println("Drag your video here: ");
		String content = scanner.nextLine();
		System.out.println("What would you like the title to be?");
		String title = scanner.nextLine();
		System.out.println("and the description: ");
		String description = scanner.nextLine();
		var videoUploadRequest = new DtoVideoUploadRequest(title, content, description, token.get());
		videoController.uploadVideo(videoUploadRequest);
	}
	
	private List<DtoVideoThumbnailResponse> findTrending20() {
		List<DtoVideoThumbnailResponse> trending20 = videoController.findTrending20();
		printVideos(trending20);
		return trending20;
	}
	
	private void chooseVideoToWatch() {
		int opt;
		do{
			System.out.println("Which video(ex: 1)");
			opt = choice();
			try{
				Long videoId = videosToWatch.get(opt - 1).getVideoId();
				Optional<Video> optVideo = videoController.findById(videoId);
				if (optVideo.isEmpty()){
					System.out.println("Video not found");
					return;
				}
				Video video = optVideo.get();
				DtoVideoDetailed dtoVideoDetailed= videoController.generateVideoModel(video);
				VideoModel videoModel = new VideoModel(dtoVideoDetailed);
				new WatchModule().watchMenu(videoModel, token);
				opt = 0;
			}
			catch (Exception e) {
				System.out.println("No such video (controller chosevideotowatch)..." + e.getMessage());
			}
			
		}while(opt != 0);
	}
	
	private List<DtoVideoThumbnailResponse> showMyVideos() {
		if (token.isEmpty()) throw new RuntimeException("Problem occured when trying to obtain the user token...(videomodule)");
		DtoTokenRequest tokenRequest = new DtoTokenRequest(token.get());
		List<DtoVideoThumbnailResponse> videos = videoController.showMyVideos(tokenRequest);
		printVideos(videos);
		return videos;
	}
	
	private List<DtoVideoThumbnailResponse> filterByTitle() {
		System.out.print("Apply filter> ");
		DtoVideoNameFilterRequest filterRequest = new DtoVideoNameFilterRequest(scanner.nextLine());
		List<DtoVideoThumbnailResponse> videos = videoController.showByName(filterRequest);
		printVideos(videos);
		return videos;
	}
	
	private List<DtoVideoThumbnailResponse> showAllVideos() {
		List<DtoVideoThumbnailResponse> videos = videoController.showAllVideos();
		printVideos(videos);
		return videos;
	}
	
	// TODO bunu bir modele entegre et
	private void printVideos(List<DtoVideoThumbnailResponse> videos){
		System.out.println("####### VIDEOS #######");
		for (int i = 0; i < videos.size(); i++) {
			System.out.println((i + 1) + ". Video");
			System.out.println(videos.get(i));
			System.out.println("-  -  -   -   -   -   -   -  -   -   -   -   -   -   -   -\n");
		}
	}
}