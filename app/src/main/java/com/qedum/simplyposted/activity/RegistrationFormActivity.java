package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.fragment.ChoosePackageFragment;
import com.qedum.simplyposted.fragment.SettingsInformationFragment;
import com.qedum.simplyposted.fragment.SocialNetworksFragment;
import com.qedum.simplyposted.util.Storage;

public class RegistrationFormActivity extends BaseActivity implements View.OnClickListener {

    private static final int STEP_SOCIAL_NETWORKS = 0;
    private static final int STEP_SETTINGS_FRAGMENT = 1;
    private static final int STEP_PACKAGE_FRAGMENT = 2;

    private Button btnNext;
    private int currentStep;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, RegistrationFormActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_registration_form;
    }

    @Override
    protected void attachActivityViews(Bundle savedInstanceState) {
        btnNext = (Button) findViewById(R.id.activity_reg_form_btn_next);

        showSocialNetworksFragment();
    }

    private void showSocialNetworksFragment() {
        SocialNetworksFragment fragment = new SocialNetworksFragment();

        showFragment(R.id.activity_reg_form_fl_content, fragment, false);
    }


    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        currentStep = STEP_SOCIAL_NETWORKS;
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_reg_form_btn_next:
                tryShowNextScreen();
                break;
        }
    }

    private void tryShowNextScreen() {
        switch (currentStep) {

            case STEP_SOCIAL_NETWORKS:
                //TODO: check from storage
//                if(Storage.getInstance().isFbConnected())
                    //TODO: add ||Storage.isTwitterConnected() )
                showSettingsFragment();
                break;

            case STEP_SETTINGS_FRAGMENT:
                //TODO: check from storage
                showChoosePackageFragment();
                break;

            case STEP_PACKAGE_FRAGMENT:
                startActivity(MainActivity.getLaunchIntent(this));
                finish();
                break;
        }
    }

    private void showSettingsFragment() {
        currentStep = STEP_SETTINGS_FRAGMENT;

        showFragment(R.id.activity_reg_form_fl_content, new SettingsInformationFragment(), true);
    }

    private void showChoosePackageFragment() {
        currentStep = STEP_PACKAGE_FRAGMENT;

        showFragment(R.id.activity_reg_form_fl_content, new ChoosePackageFragment(), true);
    }

    @Override
    public void onBackPressed() {
        currentStep--;
        super.onBackPressed();
    }
}