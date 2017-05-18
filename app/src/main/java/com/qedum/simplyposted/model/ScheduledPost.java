package com.qedum.simplyposted.model;

/**
 * Created by bogdan.aksonenko on 5/17/17.
 */

public class ScheduledPost {
    private Post post;
    private int userId;
    private String dateTime;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
