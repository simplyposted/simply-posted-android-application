package com.qedum.simplyposted.fragment;

import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.qedum.simplyposted.R;
import com.qedum.simplyposted.api.ApiCallback;
import com.qedum.simplyposted.api.ApiClient;
import com.qedum.simplyposted.model.Post;
import com.qedum.simplyposted.adapter.model.PostCard;
import com.qedum.simplyposted.model.SchedulePost;
import com.qedum.simplyposted.model.api.PostResponse;
import com.qedum.simplyposted.util.ScreenUtil;
import com.qedum.simplyposted.util.Storage;

import java.util.List;

/**
 * Created by bogdan.aksonenko on 4/20/17.
 */
public class FragmentMain extends BaseFragment implements View.OnClickListener, PostCard.PostPositionListener {
    private SwipePlaceHolderView mSwipeView;

    @Override
    protected int getContentView() {
        return R.layout.fragment_main;
    }

    @Override
    protected void attachFragmentViews(View view) {
        mSwipeView = (SwipePlaceHolderView) view.findViewById(R.id.swipeView);
        view.findViewById(R.id.item_post_main_btn_skip).setOnClickListener(this);
        view.findViewById(R.id.item_post_main_btn_accept).setOnClickListener(this);
    }

    @Override
    protected void initFragmentViews() {
        int bottomMargin = ScreenUtil.dpToPx(200);
        Point windowSize = ScreenUtil.getDisplaySize(getActivity().getWindowManager());

        mSwipeView.getBuilder()
                .setDisplayViewCount(2)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeAnimFactor(0f)
                        .setSwipeRotationAngle(0)

//                        .setPaddingTop(20)
//                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.view_post_accepted)
                        .setSwipeOutMsgLayoutId(R.layout.view_post_rejected));

        loadPosts();

    }

    private void loadPosts() {
        showWaitingDialog("");
        ApiClient.getSharedInstance().getPosts(new ApiCallback<List<PostResponse>>(this) {
            @Override
            public void onSuccess(List<PostResponse> responseObject) {
                dismissWaitingDialog();
                for (PostResponse pr : responseObject) {
                    mSwipeView.addView(new PostCard(new Post(pr), mSwipeView, FragmentMain.this));
                }
            }

            @Override
            public void onFailure(String errorResponse, String rejectReason) {
                dismissWaitingDialog();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_post_main_btn_skip:
                rejectPost();
                break;
            case R.id.item_post_main_btn_accept:
                acceptPost();
                break;
        }
    }

    private void rejectPost() {
        mSwipeView.doSwipe(false);
    }

    private void acceptPost() {
        mSwipeView.doSwipe(true);
    }


    @Override
    public void onSwipe(Post post) {
        ApiClient.getSharedInstance().addPost(post.getId(), Storage.getInstance().getUser().getId(), "2017-05-29T16:24:22.795799+05:30", new ApiCallback<SchedulePost>(this) {
            @Override
            public void onSuccess(SchedulePost responseObject) {
                Toast.makeText(FragmentMain.this.getActivity(), "Added ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String errorResponse, String rejectReason) {
                Toast.makeText(FragmentMain.this.getActivity(), "Server Error: " + rejectReason, Toast.LENGTH_LONG).show();
            }
        });
    }
}
