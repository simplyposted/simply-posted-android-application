package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;

import com.qedum.simplyposted.R;

public class MainActivity extends BaseActivity {

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    protected boolean isHomeAsUpEnabledShown(){
        return false;
    }
}
