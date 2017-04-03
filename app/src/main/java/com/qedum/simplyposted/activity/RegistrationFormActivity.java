package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.fragment.SettingsInformationFragment;
import com.qedum.simplyposted.fragment.SocialNetworksFragment;

public class RegistrationFormActivity extends BaseActivity implements View.OnClickListener {
    private static final int SOCIAL_NETWORKS = 0;
    private static final int SETTINGS_FRAGMENT = 1;
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

        showFragment(R.id.activity_reg_form_fl_content, fragment);
    }


    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        currentStep = SOCIAL_NETWORKS;
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
            case SOCIAL_NETWORKS:
                currentStep = SETTINGS_FRAGMENT;
                showSettingsFragment();
                //TODO: check from storage
//                if( connected )
                break;
            case SETTINGS_FRAGMENT:

                break;
        }
    }

    private void showSettingsFragment() {
        showFragment(R.id.activity_reg_form_fl_content, new SettingsInformationFragment());
    }
}
