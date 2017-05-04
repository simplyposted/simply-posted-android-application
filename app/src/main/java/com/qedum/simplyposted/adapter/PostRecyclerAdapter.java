package com.qedum.simplyposted.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qedum.simplyposted.R;
import com.qedum.simplyposted.SpApp;
import com.qedum.simplyposted.model.Post;

import java.util.List;


public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {
    private List<Post> items;

    public PostRecyclerAdapter(List<Post> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PostRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_list, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bind(items.get(position));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;
        public TextView tvTitle;
        //        public TextView tvLink;
        public TextView tvProfile;
        public TextView tvDateTime;
        public ImageView ivArrow;
        public View viewOpen;
        public View viewContent;
        private boolean isOpened;

        public ViewHolder(View v) {
            super(v);
            ivImage = (ImageView) v.findViewById(R.id.list_item_post_iv_image);
            tvTitle = (TextView) v.findViewById(R.id.list_item_post_tv_title);
//            tvLink = (TextView) v.findViewById(R.id.list_item_post_tv_link);
            tvProfile = (TextView) v.findViewById(R.id.list_item_post_tv_profile);
            tvDateTime = (TextView) v.findViewById(R.id.list_item_post_tv_date);
            ivArrow = (ImageView) v.findViewById(R.id.list_item_post_arrow_open);
            viewOpen = v.findViewById(R.id.list_item_post_ll_open);
            viewContent = v.findViewById(R.id.list_item_post_ll_content);
        }

        public void bind(Post item) {
            Glide.with(SpApp.getContext()).load(item.getImageUrl()).into(ivImage);
            this.tvTitle.setText(item.getTitle());
            this.tvDateTime.setText("Mon, 4/1/2017, 12:00 AM");
//            this.tvLink.setText("link");
            showActualState();

            viewOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isOpened = !isOpened;
                    showActualState();
                }
            });
        }

        private void showActualState() {
            if (isOpened) {
                showContent();
            } else {
                hideContent();
            }
        }

        private void hideContent() {
            viewContent.setVisibility(View.GONE);
            ivArrow.setImageResource(R.drawable.ic_arrow_down_gray);
        }

        private void showContent() {
            viewContent.setVisibility(View.VISIBLE);
            ivArrow.setImageResource(R.drawable.ic_arrow_up_gray);
        }
    }
}