package com.ajwalker.entity;

public class Video extends BaseEntity {
    private Long creator_id;
    private String title;
    private String content;
    private String description;
    private Long likeCount;
    private Long dislikeCount;
    private Long commentCount;
    private Long viewCount;
    private Double popularityIndex;
    
    public Long getLikeCount() {
        return likeCount;
    }
    
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
    
    public Long getDislikeCount() {
        return dislikeCount;
    }
    
    public void setDislikeCount(Long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }
    
    public Long getCommentCount() {
        return commentCount;
    }
    
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
    
    public Double getPopularityIndex() {
        return popularityIndex;
    }
    
    public void setPopularityIndex(Double popularityIndex) {
        this.popularityIndex = popularityIndex;
    }
    
    public Long getViewCount() {
        return viewCount;
    }
    
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
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