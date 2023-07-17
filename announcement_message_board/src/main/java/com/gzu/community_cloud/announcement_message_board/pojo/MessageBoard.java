package com.gzu.community_cloud.announcement_message_board.pojo;

public class MessageBoard {
    private Integer id;
    private String username;
    private Integer timestamp;
    private String content;
    private String feedback;

    public MessageBoard() {
    }

    public MessageBoard(Integer id, String username, Integer timestamp, String content, String feedback) {
        this.id = id;
        this.username = username;
        this.timestamp = timestamp;
        this.content = content;
        this.feedback = feedback;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "MessageBoard{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
