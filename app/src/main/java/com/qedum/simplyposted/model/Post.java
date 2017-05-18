package com.qedum.simplyposted.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.qedum.simplyposted.dao.base.IDbEntity;
import com.qedum.simplyposted.model.api.PostResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Post implements Parcelable, IDbEntity {
    private Long id;
    private String title;
    private String link;
    private String imageUrl;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        setLink("Facebook, Twitter");
        setTitle("Are ready to sell? Here 3 helpful steps to prepare your home for the big sale. #SellingYourHome #Sammamish");
    }

    public Post(Long id, String title, String link, String imageUrl, Date date) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    public Post(PostResponse pr) {
        this(pr.getId(), pr.getTitle(), pr.getContent(), pr.getImage(), new Date());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.imageUrl);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
    }

    protected Post(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.link = in.readString();
        this.imageUrl = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
