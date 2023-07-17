package com.gzu.community_cloud.announcement_message_board.pojo;

public class Announcement {
    private Integer id;
    private Integer timestamp;
    private String content;

    public Announcement() {
    }

    public Announcement(Integer id, Integer timestamp, String content) {
        this.id = id;
        this.timestamp = timestamp;
        this.content = content;
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

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }
}
