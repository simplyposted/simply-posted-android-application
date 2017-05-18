package com.qedum.simplyposted.model.api;

import com.google.gson.annotations.SerializedName;

public class PostResponse {
    @SerializedName("image")
    private String image;

    @SerializedName("start")
    private String dateTime;

    @SerializedName("id")
    private Long id;

    @SerializedName("content")
    private String content;

    @SerializedName("title")
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PostResponse() {
    }

    public PostResponse(String image, String dateTime, Long id, String content, String title) {
        this.image = image;
        this.dateTime = dateTime;
        this.id = id;
        this.content = content;
        this.title = title;
    }
}
