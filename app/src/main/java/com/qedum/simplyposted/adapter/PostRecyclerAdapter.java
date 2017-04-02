package com.qedum.simplyposted.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.model.Post;

import java.util.List;


public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private List<Post> items;

    public PostRecyclerAdapter(List<Post> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return 3;
//        return items.size();
    }

    @Override
    public PostRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_list, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.bind(items.get(position));

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questionName;
        public TextView titleNumberCircle;

        public ViewHolder(View v) {
            super(v);
//            questionName = (TextView) v.findViewById(R.id.row_item_recycler_item);
//            titleNumberCircle = (TextView) v.findViewById(R.id.row_item_title_number);
        }

        public void bind(Post item) {
//            this.questionName.setText(title.getName());
//            this.titleNumberCircle.setText(title.getHtmlFile());
        }
    }
}