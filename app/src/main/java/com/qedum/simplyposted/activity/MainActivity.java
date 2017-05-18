package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.api.ApiCallback;
import com.qedum.simplyposted.api.ApiClient;
import com.qedum.simplyposted.fragment.FragmentAcceptedPosts;
import com.qedum.simplyposted.fragment.FragmentMain;
import com.qedum.simplyposted.model.Post;
import com.qedum.simplyposted.model.api.PostResponse;
import com.qedum.simplyposted.util.Storage;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity {
    private static final String HOME_TAB_TAG = "1";
    private static final String ACCEPTED_POSTS_TAB_TAG = "2";
    private FragmentTabHost tabHost;

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
        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        initTabHost();
    }

    private void initTabHost() {
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(createTab(HOME_TAB_TAG), FragmentMain.class, null);
        tabHost.addTab(createTab(ACCEPTED_POSTS_TAB_TAG), FragmentAcceptedPosts.class, null);
    }

    private TabHost.TabSpec createTab(String tag) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tag));
        return tabSpec;
    }

    private View getTabIndicator(String tab) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabhost_menu_main, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(getImageResByTag(tab));
        return view;
    }

    @DrawableRes
    private int getImageResByTag(String tag) {
        int drawableId = -1;
        switch (tag) {
            case HOME_TAB_TAG:
                drawableId = R.drawable.tab_selector_home;
                break;
            case ACCEPTED_POSTS_TAB_TAG:
                drawableId = R.drawable.tab_selector_accepted_posts;
                break;
        }
        return drawableId;
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
        showWaitingDialog();
        ApiClient.getSharedInstance().logout(new ApiCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseObject) {
                dismissWaitingDialog();
                Storage.getInstance().clearAll();
                startActivity(LoginActivity.getLaunchIntent(MainActivity.this));
                finish();
            }

            @Override
            public void onFailure(String errorResponse, String rejectReason) {
                dismissWaitingDialog();
                Toast.makeText(MainActivity.this, rejectReason, Toast.LENGTH_SHORT).show();
            }
        });
    }
}