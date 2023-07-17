package com.gzu.community_cloud.announcement_message_board.pojo;

public class Announcement {
    private Integer id;
    private Integer timestamp;
    private String content;
    private Integer likes;

    public Announcement() {
    }

    public Announcement(Integer id, Integer timestamp, String content, Integer likes) {
        this.id = id;
        this.timestamp = timestamp;
        this.content = content;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                '}';
    }
}
