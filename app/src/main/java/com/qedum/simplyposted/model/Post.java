package com.qedum.simplyposted.model;

import java.util.Date;

/**
 * Created by bogdan.aksonenko on 4/3/17.
 */
public class Post {
    private String title;
    private String link;
    private String imageUrl;
    //TODO: profile field should be added
//    private String profile
    private Date date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Post() {
    }

    public Post(String title, String link, String imageUrl, Date date) {
        this.title = title;
        this.link = link;
        this.imageUrl = imageUrl;
        this.date = date;
    }
}
