package com.ajwalker.entity;

public class Like extends BaseEntity {
    private Long user_id;
    private Long video_id;
     // state field için -> 1 - > Like, 2 -> Dislike, 0 -> Non

    public Like() {
    }

    public Like(Long user_id, Long video_id, Integer state) {
        this.user_id = user_id;
        this.video_id = video_id;
        this.state = state;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Long video_id) {
        this.video_id = video_id;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                "user_id=" + user_id +
                ", video_id=" + video_id +
                ", state=" + state +
                '}';
    }
    
    
}