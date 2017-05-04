package com.qedum.simplyposted.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.adapter.PostRecyclerAdapter;
import com.qedum.simplyposted.model.Post;

import java.util.ArrayList;

/**
 * Created by bogdan.aksonenko on 4/20/17.
 */
public class FragmentAcceptedPosts extends BaseFragment {

    private RecyclerView mRecyclerView;
    private PostRecyclerAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_accepted_posts;
    }

    @Override
    protected void attachFragmentViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_PostsActivity);
    }

    @Override
    protected void initFragmentViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mAdapter = new PostRecyclerAdapter(getDummyItems());
        mRecyclerView.setAdapter(mAdapter);
    }

    @NonNull
    private ArrayList<Post> getDummyItems() {
        ArrayList<Post> items = new ArrayList<>();
        items.add(new Post());
        items.add(new Post());
        items.add(new Post());
        return items;
    }
}
