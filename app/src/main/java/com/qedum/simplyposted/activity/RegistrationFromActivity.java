package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.qedum.simplyposted.R;

public class RegistrationFromActivity extends BaseActivity implements View.OnClickListener {


    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, RegistrationFromActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_registration;
    }

    @Override
    protected void attachActivityViews(Bundle savedInstanceState) {
    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View view) {

    }
}
