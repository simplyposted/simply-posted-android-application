package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.qedum.simplyposted.R;
import com.qedum.simplyposted.model.Post;
import com.qedum.simplyposted.model.PostCard;
import com.qedum.simplyposted.util.Storage;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private SwipePlaceHolderView mSwipeView;

    public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void attachActivityViews(Bundle savedInstanceState) {
        mSwipeView = (SwipePlaceHolderView) findViewById(R.id.swipeView);
    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        findViewById(R.id.item_post_main_btn_skip).setOnClickListener(this);
        findViewById(R.id.item_post_main_btn_accept).setOnClickListener(this);

        mSwipeView.getBuilder()
                .setDisplayViewCount(2)
                .setSwipeDecor(new SwipeDecor()
//                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.view_post_accepted)
                        .setSwipeOutMsgLayoutId(R.layout.view_post_rejected));


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

    protected boolean isHomeAsUpEnabledShown() {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_settings:
                startActivity(SettingsActivity.getLaunchIntent(this));
                return true;

            case R.id.action_scheduled_posts:
                startActivity(PostsActivity.getLaunchIntent(this));
                return true;

            case R.id.action_about_share:
                share();
                return true;

            case R.id.action_logout:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void share() {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share_text));
        shareIntent.setType("text/plain");
        String title = getString(R.string.menu_action_share);
        try {
            Intent chooser = Intent.createChooser(shareIntent, title);
            startActivity(chooser);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, R.string.no_share_app, Toast.LENGTH_SHORT).show();
        }
    }

    private void logout() {
        Storage.getInstance().clearAll();
        startActivity(LoginActivity.getLaunchIntent(this));
        finish();
    }
}