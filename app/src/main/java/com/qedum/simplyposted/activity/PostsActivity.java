package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.adapter.PostRecyclerAdapter;
import com.qedum.simplyposted.model.Post;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by bogdan.aksonenko on 4/3/17.
 */

public class PostsActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private PostRecyclerAdapter mAdapter;


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, PostsActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_posts;
    }

    @Override
    protected void attachActivityViews(Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_PostsActivity);

    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new PostRecyclerAdapter(new ArrayList<Post>());
        mRecyclerView.setAdapter(mAdapter);
    }

}
