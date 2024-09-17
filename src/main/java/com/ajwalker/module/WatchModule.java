package com.ajwalker.module;

import com.ajwalker.controller.CommentController;
import com.ajwalker.controller.LikeController;
import com.ajwalker.controller.VideoController;
import com.ajwalker.dto.request.DtoLikeRequest;
import com.ajwalker.entity.Video;
import com.ajwalker.model.VideoModel;

import java.util.Optional;
import java.util.Scanner;


public class WatchModule {
	private VideoModel videoModel;
	private Scanner scanner = new Scanner(System.in);
	private LikeController likeController = LikeController.getInstance();
	private CommentController commentController = CommentController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	private Video video;
	private Optional<String> token;
	
	Thread watchThread = new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(10000);
				videoController.watched(video);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});
	
	{
		watchThread.setDaemon(true);
	}
	
	public void watchMenu(VideoModel videoModel, Optional<String> token) {
		watchThread.start();
		this.videoModel = videoModel;
		this.video = videoModel.getDtoVideoDetailed().getVideo();
		this.token = token;
		int opt;
		do {
			System.out.printf("### %s ###\n", video.getTitle()); // videonun kaç like ve dislike'ı var
			// yazdırabilirsin burada
			System.out.println("""
					                   1. Watch video
					                   2. Read Description
					                   3. Like video
					                   4. Comment video
					                   5. Read Comments
					                   6. Dislike video
					                   7. Take Back like/ dislike
					                   8. Delete comment
					                   9. Show Video Statistics
					                   0. Go Back
					                   """); // 8. abone ol
			opt = MainMenu.getInstance().choice();
			watchMenuOptions(opt);
		} while (opt != 0);
	}
	
	private int watchMenuOptions(int opt) {
		switch (opt) {
			case 1:
				System.out.println(video.getContent());
				break;
			case 2:
				System.out.println(video.getDescription());
				break;
			case 3:
				token = isLoggedIn();
				if (token.isPresent()) {
					var likeRequest = new DtoLikeRequest(video.getId(), token.get());
					likeController.likeTheVideo(likeRequest);
				}
				break;
			case 4:
				token = isLoggedIn();
				if (token.isPresent()) {
					commentToTheVideo();
				}
				break;
			case 5:
				break;
				case 6:
					token = isLoggedIn();
					if (token.isPresent()) {
						var likeRequest = new DtoLikeRequest(video.getId(), token.get());
						likeController.dislikeTheVideo(likeRequest);
					}
					break;
			case 7:
				token = isLoggedIn();
				if (token.isPresent()) {
					var likeRequest = new DtoLikeRequest(video.getId(), token.get());
					likeController.softDeleteLike(likeRequest);
				}
				break;
			case 8:
				break;
			case 9:
				videoModel.videoStatistics();
				break;
			case 0:
				System.out.println("Going back...");
				break;
			default:
				System.out.println("Invalid option in watchmenuoptions");
				break;
			
		}
		return opt;
	}
	
	private void commentToTheVideo() {
		System.out.print("Enter your comment here: ");
		String comment = scanner.nextLine();
		DtoComment
		commentController.comment(video, user.get(), comment);
	}
	
	private Optional<String> isLoggedIn() {
		if (token.isEmpty()) {
			System.out.println("You have to log in or register to like a video");
			System.out.println("""
					                   1. Login
					                   2. Register
					                   0. Go Back
					                   """);
			switch (choice()){
				case 0:
					return Optional.empty();
				case 1:
					return MainMenu.getInstance().login();
				case 2:
					new RegisterMenu().register();
			}
		}
		return token;
		
	}
	public Integer choice(){
		return MainMenu.getInstance().choice();
	}
}