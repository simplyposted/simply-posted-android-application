package com.qedum.simplyposted.model;


public class SchedulePost {

    private long postId;
    private long userId;
    private String publicationDate;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long post) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public SchedulePost(long postId, long userId, String publicationDate) {
        this.postId = postId;
        this.userId = userId;
        this.publicationDate = publicationDate;
    }
}
