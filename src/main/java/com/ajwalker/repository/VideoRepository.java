package com.ajwalker.repository;

import com.ajwalker.database.DatabaseHelper;
import com.ajwalker.entity.User;
import com.ajwalker.entity.Video;
import com.ajwalker.utility.ICRUD;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ajwalker.database.SQLQueryBuilder.*;

public class VideoRepository implements ICRUD<Video> {
    private final String TABLE_NAME = "tblvideo";
    private DatabaseHelper databaseHelper;
    private static VideoRepository instance;
    private static String sql;
    VideoRepository() {
        this.databaseHelper = new DatabaseHelper();
    }
    public static VideoRepository getInstance() {
        if (instance == null) {
            instance = new VideoRepository();
        }
        return instance;
    }


    @Override
    public boolean save(Video video) {
        return databaseHelper.executeUpdate(generateInsert(video, TABLE_NAME));
    }

    @Override
    public boolean update(Video video) {
        return databaseHelper.executeUpdate(generateUpdate(video, TABLE_NAME));
    }

    @Override
    public boolean delete(Long id) {
        return databaseHelper.executeUpdate(generateDelete(TABLE_NAME, id));
    }

    @Override
    public List<Video> findAll() {
        sql = "SELECT * FROM " + TABLE_NAME +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        return resultSet.map(set -> generateList(Video.class, set)).orElseGet(ArrayList::new);
    }

    @Override
    public Optional<Video> findById(Long id) {
        sql = "SELECT * FROM " + TABLE_NAME +" WHERE id = "+ id +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        if (resultSet.isPresent()) {
            return findBy(Video.class, resultSet.get());
        }
        return Optional.empty();
    }
    
    public List<Video> findByTitle(String videoTitle) {
        sql = "SELECT * FROM " + TABLE_NAME +" WHERE title ILIKE '%"+ videoTitle +"%' ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        return resultSet.map(set -> generateList(Video.class, set)).orElseGet(ArrayList::new);
    }
    
    public List<Video> findByCreatorId(Long userId) {
        sql = "SELECT * FROM " + TABLE_NAME +" WHERE creator_id = "+ userId +" ORDER BY id DESC";
        Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
        return resultSet.map(set -> generateList(Video.class, set)).orElseGet(ArrayList::new);
    }
}