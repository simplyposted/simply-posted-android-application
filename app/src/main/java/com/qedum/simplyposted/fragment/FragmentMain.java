package com.qedum.simplyposted.fragment;

import android.graphics.Point;
import android.view.Gravity;
import android.view.View;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.qedum.simplyposted.R;
import com.qedum.simplyposted.model.Post;
import com.qedum.simplyposted.model.PostCard;
import com.qedum.simplyposted.util.ScreenUtil;

/**
 * Created by bogdan.aksonenko on 4/20/17.
 */
public class FragmentMain extends BaseFragment implements View.OnClickListener {
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
//        mSwipeView.set

        for (int i = 0; i < 10; i++) {
            mSwipeView.addView(new PostCard(new Post(), mSwipeView));
        }
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


}
