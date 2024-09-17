package com.ajwalker.repository;

import com.ajwalker.database.DatabaseHelper;
import com.ajwalker.entity.Like;
import com.ajwalker.entity.User;
import com.ajwalker.entity.UserComment;
import com.ajwalker.utility.ICRUD;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ajwalker.database.SQLQueryBuilder.*;

public class CommentRepository implements ICRUD<UserComment> {
    private final String TABLE_NAME = "tblusercomment";
    private DatabaseHelper databaseHelper;
    private static CommentRepository instance;

    private CommentRepository() {
        this.databaseHelper = new DatabaseHelper();
    }
    public static CommentRepository getInstance() {
        if (instance == null) {
            instance = new CommentRepository();
        }
        return instance;
    }


    @Override
    public boolean save(UserComment userComment) {
        return databaseHelper.executeUpdate(generateInsert(userComment, TABLE_NAME));
    }

    @Override
    public boolean update(UserComment userComment) {
        return databaseHelper.executeUpdate(generateUpdate(userComment, TABLE_NAME));
    }

    @Override
    public boolean delete(Long id) {
        return databaseHelper.executeUpdate(generateDelete(TABLE_NAME, id));
    }

    @Override
    public List<UserComment> findAll() {
        String sql = "SELECT * FROM " + TABLE_NAME +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        return resultSet.map(set -> generateList(UserComment.class, set)).orElseGet(ArrayList::new);
    }

    @Override
    public Optional<UserComment> findById(Long id) {
        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE id = "+ id +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        if (resultSet.isPresent()) {
            return findBy(UserComment.class, resultSet.get());
        }
        return Optional.empty();
    }
	
	public List<UserComment> countComment(Long videoId) {
        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE video_id = "+ videoId +" ORDER" +
                " BY" +
                " id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        return resultSet.map(set -> generateList(UserComment.class, set)).orElseGet(ArrayList::new);
	}
}