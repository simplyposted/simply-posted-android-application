package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.fragment.ChoosePackageFragment;
import com.qedum.simplyposted.fragment.SettingsInformationFragment;
import com.qedum.simplyposted.fragment.SocialNetworksFragment;

/**
 * Created by bogdan.aksonenko on 4/3/17.
 */
public class SettingsActivity extends BaseActivity {
    public static final int SOCIAL_TAB_INDEX = 0;
    public static final int SETTINGS_TAB_INDEX = 1;
    public static final int PACKAGE_TAB_INDEX = 2;
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
            case SOCIAL_TAB_INDEX:
                return SOCIAL_TAB_TAG;
            case SETTINGS_TAB_INDEX:
                return SETTINGS_TAB_TAG;
            case PACKAGE_TAB_INDEX:
                return PACKAGE_TAB_TAG;
        }
        return SOCIAL_TAB_TAG;
    }

    private TabHost.TabSpec createTab(String tag) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getResources().getString(getStringByTag(tag)));
        return tabSpec;
    }


    private int getStringByTag(String tag) {
        int resId = -1;
        switch (tag) {
            case SOCIAL_TAB_TAG:
                resId = R.string.activity_settings_menu_social_networks;
                break;
            case SETTINGS_TAB_TAG:
                resId = R.string.activity_settings_menu_settings;
                break;
            case PACKAGE_TAB_TAG:
                resId = R.string.activity_settings_menu_package;
                break;
        }
        return resId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_save:
                Toast.makeText(this, getResources().getString(R.string.activity_settings_menu_saved), Toast.LENGTH_SHORT).show();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}