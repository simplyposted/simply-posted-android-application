package com.qedum.simplyposted.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by bogdan.aksonenko on 4/3/17.
 */
public class Post implements Parcelable {
    //TODO: remove hardcoded images
    List<String> images = new ArrayList<>();

    {
        images.add("http://cdn.cavemancircus.com//wp-content/uploads/images/2015/june/pretty_girls_3/pretty_girls_15.jpg");
        images.add("https://pbs.twimg.com/profile_images/572905100960485376/GK09QnNG.jpeg");
        images.add("http://i.imgur.com/N6SaAlZ.jpg");
        images.add("http://cdn.cavemancircus.com//wp-content/uploads/images/2015/january/pretty_girls_2/pretty_girls_5.jpg");
        images.add("http://cdn.cavemancircus.com//wp-content/uploads/images/2015/june/pretty_girls_3/pretty_girls_20.jpg");
        images.add("http://cdn.cavemancircus.com//wp-content/uploads/images/2015/march/pretty_girls_2/pretty_girls_12.jpg");
        images.add("https://s-media-cache-ak0.pinimg.com/736x/de/87/7b/de877bcccc2295a58fe8758fee0ebc7d.jpg");
        images.add("https://scontent.cdninstagram.com/hphotos-xpf1/t51.2885-15/e15/10986280_404995676329336_1177563605_n.jpg");
    }

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
        int randomNumber = new Random().nextInt(images.size());
        setImageUrl(images.get(randomNumber));
        setLink("Brandon Foote");
        setTitle("Are ready to sell? Here 3 helpful steps to prepare your home for the big sale. #SellingYourHome #Sammamish" + randomNumber);
    }

    public Post(String title, String link, String imageUrl, Date date) {
        this.title = title;
        this.link = link;
        this.imageUrl = imageUrl;
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.images);
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.imageUrl);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
    }

    protected Post(Parcel in) {
        this.images = in.createStringArrayList();
        this.title = in.readString();
        this.link = in.readString();
        this.imageUrl = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
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
