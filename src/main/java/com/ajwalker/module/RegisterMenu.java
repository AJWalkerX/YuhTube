package com.ajwalker.module;


import com.ajwalker.controller.UserController;
import com.ajwalker.dto.request.DtoUserRegisterRequest;
import com.ajwalker.entity.User;

import java.util.List;
import java.util.Scanner;

public class RegisterMenu {
	private Scanner scanner = new Scanner(System.in);
	private UserController userController = UserController.getInstance();
	
	public void register(){
		String email = createEmail();
		String username = createUsername();
		String password = createPassword();
		var registerRequest = new DtoUserRegisterRequest(email, password, username);
		if (userController.save(registerRequest)) {
			System.out.println("Successfulled");
		}
	}
	private String createEmail(){
		while (true){
			System.out.print("Enter your e-mail: ");
			String email = scanner.nextLine();
			if (!email.contains("@")){
				System.out.println("Invalid e-mail");
			}
			else return email;
		}
	}
	
	private String createPassword() {
		int i = 0;
		while (i < 3){
			System.out.print("Enter your password: ");
			String password = scanner.nextLine();
			System.out.print("Enter your password again: ");
			String passwordAgain = scanner.nextLine();
			if (password.equals(passwordAgain)){
				if (isValidPassword(password)) return password;
				else System.out.println("Password does not satisfy the restrictions");
			}
			else System.out.println("Password does not match");
			i++;
		}
		return null;
	}
	
	private boolean isValidPassword(String password) {
		if (password.length() < 8) return false;
		return true;
	}
	
	private String createUsername() {
		while (true){
			System.out.print("Enter your username: ");
			String username = scanner.nextLine();
			if (existByUsername(username)) return username;
			else System.out.println("Invalid username");
		}
	}
	
	private boolean existByUsername(String username) {
		List<User> userList = userController.findAll();
		for (User user : userList){
			if(user.getUsername().equals(username)){
				return false;
			}
		}
		return true;
		
	}
}