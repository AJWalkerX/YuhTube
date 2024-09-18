package com.ajwalker.gui;



import com.ajwalker.controller.UserController;
import com.ajwalker.dto.request.DtoUserLoginRequest;
import com.ajwalker.dto.response.DtoUserLoginResponse;

import java.util.Optional;
import java.util.Scanner;

public class LoginMenu {
	private static LoginMenu instance = new LoginMenu();
	private LoginMenu() {}
	public static LoginMenu getInstance() {
		return instance;
	}
	private Scanner scanner = new Scanner(System.in);
	private UserController userController = UserController.getInstance();
	
	public Optional<DtoUserLoginResponse> loginModule(){
		return loginMenu();
	}
	
	
	private Optional<DtoUserLoginResponse> loginMenu(){
		System.out.print("Enter your username: ");
		String username = scanner.nextLine();
		System.out.print("Enter your password: ");
		String password = scanner.nextLine();
		DtoUserLoginRequest tempLoginRequest = new DtoUserLoginRequest(username, password);
		return userController.login(tempLoginRequest);
	}
	
	private Optional<String> findByUsername() {
		System.out.println("Enter username: ");
		String username = scanner.nextLine();
		if (existsUsername(username)) return Optional.of(username);
		else return Optional.empty();
	}
	
	private boolean existsUsername(String username) {
		return false;
	}
}