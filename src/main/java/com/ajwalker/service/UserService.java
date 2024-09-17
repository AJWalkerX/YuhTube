package com.ajwalker.service;

import com.ajwalker.database.DatabaseHelper;
import com.ajwalker.database.SQLQueryBuilder;
import com.ajwalker.dto.request.DtoUserLoginRequest;
import com.ajwalker.dto.request.DtoUserRegisterRequest;
import com.ajwalker.dto.response.DtoUserLoginResponse;
import com.ajwalker.entity.User;
import com.ajwalker.repository.UserRepository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static final UserRepository userRepository = UserRepository.getInstance();
    private static UserService instance;
    private DatabaseHelper databaseHelper = new DatabaseHelper();
    private String sql;

    private UserService() {}

    public static UserService getInstance() {
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public boolean save(DtoUserRegisterRequest registerRequest) {
        User user = registerRequestToUser(registerRequest);
        return userRepository.save(user);
    }
    
    private User registerRequestToUser(DtoUserRegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String email = registerRequest.getEmail();
        User user = new User(username, password, email);
        return user;
    }
    
    public boolean update(User user) {
        return userRepository.update(user);
    }

    public boolean delete(Long id) {
        return userRepository.delete(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    
    public Optional<User> findByUsername(String username) {
        sql = "SELECT * FROM tbluser WHERE username = '%s'".formatted(username);
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        if(resultSet.isPresent()) {
            return SQLQueryBuilder.findBy(User.class, resultSet.get());
        }
        else return Optional.empty();
    }
    
    public Optional<DtoUserLoginResponse> login(DtoUserLoginRequest tempLoginRequest) {
        Optional<User> optUser = findByUsername(tempLoginRequest.getUsername());
        if(optUser.isEmpty()){
            System.out.println("No such username exists...(service)");
            return Optional.empty();
        }
        boolean isCorrectPassword = optUser.get().getPassword().equals(tempLoginRequest.getPassword());
        if(!isCorrectPassword){
            System.out.println("Incorrect password...(service)");
            return Optional.empty();
        }
        String finalUsername = optUser.get().getUsername();
        return Optional.ofNullable(new DtoUserLoginResponse(finalUsername));
    }
    
    public Optional<User> getUserIdByToken(String token) {
        return findByUsername(token);
    }
}