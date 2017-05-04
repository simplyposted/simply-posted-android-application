package com.qedum.simplyposted.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.qedum.simplyposted.R;
import com.qedum.simplyposted.SpApp;

@Layout(R.layout.item_post_card_view)
public class PostCard {

    @View(R.id.item_post_iv_image)
    private ImageView ivPost;

    @View(R.id.item_post_tv_title)
    private TextView tvTitle;

    @View(R.id.item_post_tv_link)
    private TextView tvLink;

    @View(R.id.item_post_tv_date)
    private TextView tvDate;

//    @View(R.id.item_post_card_view_root)
//    private CardView root;

    private Post post;
    private SwipePlaceHolderView mSwipeView;

    public PostCard(Post item, SwipePlaceHolderView swipeView) {
        post = item;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved() {
        Glide.with(SpApp.getContext()).load(post.getImageUrl()).placeholder(R.drawable.logo).into(ivPost);
        tvTitle.setText(post.getTitle());
        tvLink.setText(post.getLink());
        tvDate.setText(post.getDate().toString());
    }

    @SwipeOut
    private void onSwipedOut() {
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn() {
        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState() {
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState");
    }

    //https://blog.mindorks.com/android-tinder-swipe-view-example-3eca9b0d4794
}
