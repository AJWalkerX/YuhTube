package com.ajwalker.entity;

public class Video extends BaseEntity {
    private Long creator_id;
    private String title;
    private String content;
    private String description;
    private Long likecount;
    private Long dislikecount;
    private Long commentcount;
    private Long viewcount;
    private Float popularityindex;
    
    {
        likecount = dislikecount = viewcount = commentcount = 0L;
        state = 1;
        popularityindex = 0f;
    }
    public Long getLikeCount() {
        return likecount;
    }
    
    public void setLikeCount(Long likeCount) {
        this.likecount = likeCount;
    }
    
    public Long getDislikeCount() {
        return dislikecount;
    }
    
    public void setDislikeCount(Long dislikeCount) {
        this.dislikecount = dislikeCount;
    }
    
    public Long getCommentCount() {
        return commentcount;
    }
    
    public void setCommentCount(Long commentCount) {
        this.commentcount = commentCount;
    }
    
    public Float getPopularityIndex() {
        return popularityindex;
    }
    
    public void setPopularityIndex(Float popularityIndex) {
        this.popularityindex = popularityIndex;
    }
    
    public Long getViewCount() {
        return viewcount;
    }
    
    public void setViewCount(Long viewCount) {
        this.viewcount = viewCount;
    }
    
    public Video() {
    }

    public Video(Long creator_id, String title, String content, String description) {
        this.creator_id = creator_id;
        this.title = title;
        this.content = content;
        this.description = description;
    }

    public Video(Long id, Long creator_id, String title, String content, String description) {
        super.id = id;
        this.creator_id = creator_id;
        this.title = title;
        this.content = content;
        this.description = description;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + getId() +
                "creator_id=" + creator_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}