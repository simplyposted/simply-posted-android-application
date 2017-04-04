package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.fragment.ChoosePackageFragment;
import com.qedum.simplyposted.fragment.SettingsInformationFragment;
import com.qedum.simplyposted.fragment.SocialNetworksFragment;

/**
 * Created by bogdan.aksonenko on 4/3/17.
 */
public class SettingsActivity extends BaseActivity {

    public static final String TAB_INDEX_KEY = "TAB_INDEX_KEY";
    public static final int HOME_TAB_INDEX = 0;
    public static final int HEMA_TAB_INDEX = 1;
    public static final int SHOP_TAB_INDEX = 2;
    public static final String SOCIAL_TAB_TAG = "SOCIAL_TAB_TAG";
    public static final String SETTINGS_TAB_TAG = "SETTINGS_TAB_TAG";
    public static final String PACKAGE_TAB_TAG = "PACKAGE_TAB_TAG";
    private FragmentTabHost tabHost;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_settings;
    }

    @Override
    protected void attachActivityViews(Bundle savedInstanceState) {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        initTabHost();
    }

    private void initTabHost() {
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(createTab(SOCIAL_TAB_TAG), SocialNetworksFragment.class, null);
        tabHost.addTab(createTab(SETTINGS_TAB_TAG), SettingsInformationFragment.class, null);
        tabHost.addTab(createTab(PACKAGE_TAB_TAG), ChoosePackageFragment.class, null);
        tabHost.getTabWidget().setDividerDrawable(null);

    }

    private String getTag(int i) {
        switch (i) {
            case HOME_TAB_INDEX:
                return SOCIAL_TAB_TAG;
            case HEMA_TAB_INDEX:
                return SETTINGS_TAB_TAG;
            case SHOP_TAB_INDEX:
                return PACKAGE_TAB_TAG;
        }
        return SOCIAL_TAB_TAG;
    }

    private TabHost.TabSpec createTab(String tag) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tag));
        return tabSpec;
    }

    private View getTabIndicator(String tab) {
        View view = LayoutInflater.from(this).inflate(R.layout.tabhost_settings_menu, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tabhost_settings_tx_title);
        tvTitle.setText(getStringByTag(tab));
        return view;
    }

    private int getStringByTag(String tag) {
        int resId = -1;
        switch (tag) {
            case SOCIAL_TAB_TAG:
                resId = R.string.app_name;
                break;
            case SETTINGS_TAB_TAG:
                resId = R.string.app_name;
                break;
            case PACKAGE_TAB_TAG:
                resId = R.string.app_name;
                break;
        }
        return resId;
    }
}