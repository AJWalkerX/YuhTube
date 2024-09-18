package com.ajwalker.gui;

import com.ajwalker.dto.response.DtoUserLoginResponse;

import java.util.Optional;
import java.util.Scanner;

public class MainMenu {
	private MainMenu() {}
	private static MainMenu instance = new MainMenu();
	public static MainMenu getInstance() {
		return instance;
	}
	private  Optional<String> token = Optional.empty();
	private  Scanner scanner = new Scanner(System.in);
	
	public void mainMenu(){
		int opt;
		do{
			if (token.isEmpty()) opt = anonymousMainMenu();
			else opt = userMainMenu();
		} while(opt != 0);
		System.out.println(opt);
	}
	public int anonymousMainMenu(){
		System.out.println("""
				                   YuhTube
				                   Main Menu
				                   1. Login
				                   2. Register
				                   3. Watch Videos
				                   0. Exit
				                   """);
		return mainMenuOptions(choice());
	}
	
	private int mainMenuOptions(int choice) {
		switch (choice){
			case 1:
				login();
				break;
			case 2:
				new RegisterMenu().register();
				break;
			case 3:
				token = new VideoModule().videoModule(token);
				break;
			case 0:
				System.out.println("Have a nice day!");
				break;
				default:
					System.out.println("Invalid option!");
		}
		
		return choice;
	}
	
	public Optional<String> login() {
		Optional<DtoUserLoginResponse> tempResponse = LoginMenu.getInstance().loginModule();
		tempResponse.ifPresent(this::loginRequestToToken);
		return token;
	}
	
	public int choice() {
		while (true) {
			try {
				System.out.print("Enter int value> ");
				int i = scanner.nextInt();
				scanner.nextLine();
				return i;
			}
			catch (Exception e) {
				System.out.println("invalid value");
				scanner.nextLine();
			}
		}
	}
	
	private int userMainMenu() {
		System.out.println("""
				                   YuhTube
				                   Main Menu
				                   1. Logout
				                   2. Watch Videos
				                   0. Exit
				                   
				                   """);
		return userMainMenuOptions(choice());
	}
	
	private int userMainMenuOptions(int choice) {
		switch (choice){
			case 1:
				token = Optional.empty();
				break;
			case 2:
				token = (new VideoModule()).videoModule(token);
				break;
			case 3:
				break;
			case 0:
				System.out.println("Have a nice day!");
				break;
			default:
				System.out.println("Invalid option!");
		}
		
		return choice;
	}
	
	public void loginRequestToToken(DtoUserLoginResponse loginResponse) {
		token = Optional.ofNullable(loginResponse.getUsername());
	}
}