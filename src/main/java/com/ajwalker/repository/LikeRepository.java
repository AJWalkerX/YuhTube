package com.ajwalker.repository;

import com.ajwalker.database.DatabaseHelper;
import com.ajwalker.entity.Like;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;
import com.ajwalker.utility.ICRUD;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ajwalker.database.SQLQueryBuilder.*;

public class LikeRepository implements ICRUD<Like> {
    private final String TABLE_NAME = "tbllike";
    private DatabaseHelper databaseHelper;
    private static LikeRepository instance;

    private LikeRepository() {
        this.databaseHelper = new DatabaseHelper();
    }
    public static LikeRepository getInstance() {
        if (instance == null) {
            instance = new LikeRepository();
        }
        return instance;
    }


    @Override
    public boolean save(Like like) {
        return databaseHelper.executeUpdate(generateInsert(like, TABLE_NAME));
    }

    @Override
    public boolean update(Like like) {
        return databaseHelper.executeUpdate(generateUpdate(like, TABLE_NAME));
    }

    @Override
    public boolean delete(Long id) {
        return databaseHelper.executeUpdate(generateDelete(TABLE_NAME, id));
    }

    @Override
    public List<Like> findAll() {
        String sql = "SELECT * FROM " + TABLE_NAME +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        return resultSet.map(set -> generateList(Like.class, set)).orElseGet(ArrayList::new);
    }

    @Override
    public Optional<Like> findById(Long id) {
        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE id = "+ id +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        if (resultSet.isPresent()) {
            return findBy(Like.class, resultSet.get());
        }
        return Optional.empty();
    }
    
    public Optional<Like> findByVideoAndUserId(Long videoId, Long userId) {
        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE video_id = "+ videoId +" AND user_id = " + userId +" ORDER" +
                " BY" +
                " id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        if (resultSet.isPresent()) {
            return findBy(Like.class, resultSet.get());
        }
        return Optional.empty();
    }
	
	public List<Like> findByVideoId(Long videoId) {
        String sql = "SELECT * FROM " + TABLE_NAME +" WHERE video_id = "+ videoId +" ORDER" +
                " BY" +
                " id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        return resultSet.map(set -> generateList(Like.class, set)).orElseGet(ArrayList::new);
	}
}