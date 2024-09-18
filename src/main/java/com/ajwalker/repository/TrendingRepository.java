package com.ajwalker.repository;

import com.ajwalker.database.DatabaseHelper;
import com.ajwalker.entity.Video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrendingRepository {
	private DatabaseHelper databaseHelper = new DatabaseHelper();
	private static TrendingRepository instance = new TrendingRepository();
	private TrendingRepository(){}
	public static TrendingRepository getInstance(){
		return instance;
	}
	
	public List<Video> findTrending20(){
		String sql = "SELECT v.*, COUNT(CASE WHEN l.state = 1 THEN 1 END) - count(CASE WHEN l.state = -1 then 1 END)" +
				"*0.3 + COUNT(CASE WHEN uc.state = 1 THEN 1 END)*0.1+ SUM(v.viewcount)*0.6 AS trending_score FROM tblvideo v JOIN tbllike l ON l.video_id = v.id JOIN tblusercomment uc ON uc.video_id = v.id GROUP BY v.id ORDER BY trending_score DESC LIMIT 20;";
		Optional<ResultSet> resultSet = databaseHelper.executeQuery(sql);
		List<Video> musteriList = new ArrayList<>();
		try {
			if (resultSet.isPresent()) {
				ResultSet rs = resultSet.get();
				while (rs.next()) {
					Optional<Video> optVideo = getValueFromResultSet(rs);
					optVideo.ifPresent(video -> musteriList.add(video));
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Müşteri Listesi getirirken bir sorun yaşandı..." + e.getMessage());
		}
		return musteriList;
	}
	
	private Optional<Video> getValueFromResultSet(ResultSet rs) {
		try {
			Video video = new Video();
			video.setId(rs.getLong("id"));
			video.setTitle(rs.getString("title"));
			video.setDescription(rs.getString("description"));
			video.setContent(rs.getString("content"));
			video.setViewCount(rs.getLong("viewcount"));
			video.setCreator_id(rs.getLong("creator_id"));
			return Optional.ofNullable(video);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}