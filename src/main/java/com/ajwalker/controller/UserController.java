package com.ajwalker.controller;

import com.ajwalker.dto.request.DtoUserLoginRequest;
import com.ajwalker.dto.request.DtoUserRegisterRequest;
import com.ajwalker.dto.response.DtoUserLoginResponse;
import com.ajwalker.entity.User;
import com.ajwalker.repository.UserRepository;
import com.ajwalker.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController {
    private static final UserService userService = UserService.getInstance();
    private static UserController instance;

    private UserController() {}

    public static UserController getInstance() {
        if (instance == null){
            instance = new UserController();
        }
        return instance;
    }

    public boolean save(DtoUserRegisterRequest registerRequest) {
	    try {
		    return userService.save(registerRequest);
	    }
	    catch (Exception e) {
            System.out.println("failed(controller level)..." + e.getMessage());
            return false;
	    }
    }

    public boolean update(User user) {
        try {
        return userService.update(user);
    }
	    catch (Exception e) {
        System.out.println("failed(controller level)..." + e.getMessage());
            return false;
    }
    }

    public boolean delete(Long id) {
        try {
        return userService.delete(id);
    }
	    catch (Exception e) {
        System.out.println("failed(controller level)..." + e.getMessage());
            return false;
        }
    }

    public List<User> findAll() {
        try {
        return userService.findAll();
    }
	    catch (Exception e) {
        System.out.println("failed(controller level)..." + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Optional<User> findById(Long id) {
        try {
        return userService.findById(id);
    }
	    catch (Exception e) {
        System.out.println("failed(controller level)..." + e.getMessage());
        return Optional.empty();
        }
    }
    
    public Optional<User> findByUsername(String username) {
        try {
        return userService.findByUsername(username);
    }
	    catch (Exception e) {
        System.out.println("failed(controller level)..." + e.getMessage());
            return Optional.empty();
            
        }
    }
    
    public Optional<DtoUserLoginResponse> login(DtoUserLoginRequest tempLoginRequest) {
        try{
            return userService.login(tempLoginRequest);
        }
        catch (Exception e) {
            System.out.println("Failed login(controller)..." + e.getMessage());
        }
    }
}