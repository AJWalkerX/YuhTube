package com.ajwalker.repository;

import com.ajwalker.database.DatabaseHelper;
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

    public boolean softDelete (Long id){
        Optional<User> userOptional = findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            System.out.println(user.getId());
            user.setState(0);
            return update(user);
        }
        else
            return false;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM " + TABLE_NAME +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        return resultSet.map(set -> generateList(User.class, set)).orElseGet(ArrayList::new);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE id = "+ id +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        if (resultSet.isPresent()) {
            return findBy(User.class, resultSet.get());
        }
        return Optional.empty();
    }
}
