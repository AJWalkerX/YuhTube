package com.ajwalker.repository;

import com.ajwalker.database.DatabaseHelper;
import com.ajwalker.database.SQLQueryBuilder;
import com.ajwalker.entity.User;
import com.ajwalker.utility.ICRUD;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ajwalker.database.SQLQueryBuilder.*;

public class UserRepository implements ICRUD<User> {
    private final String TABLE_NAME = "tbluser";
    private DatabaseHelper databaseHelper;
    private static UserRepository instance;

    private UserRepository() {
        this.databaseHelper = new DatabaseHelper();
    }
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }


    @Override
    public boolean save(User user) {
        return databaseHelper.executeUpdate(generateInsert(user, TABLE_NAME));
    }

    @Override
    public boolean update(User user) {
        return databaseHelper.executeUpdate(generateUpdate(user, TABLE_NAME));
    }

    @Override
    public boolean delete(Long id) {
        return databaseHelper.executeUpdate(generateDelete(TABLE_NAME, id));
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE state != 0 ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        return resultSet.map(set -> generateList(User.class, set)).orElseGet(ArrayList::new);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE state != 0 AND id = "+ id +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        if (resultSet.isPresent()) {
            return findBy(User.class, resultSet.get());
        }
        return Optional.empty();
    }
    
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM tbluser WHERE username = '%s'".formatted(username);
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        if(resultSet.isPresent()) {
            return SQLQueryBuilder.findBy(User.class, resultSet.get());
        }
        else return Optional.empty();
    
    }
}