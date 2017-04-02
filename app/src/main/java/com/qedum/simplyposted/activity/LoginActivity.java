package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;

import com.qedum.simplyposted.R;

/**
 * Created by bogdan.aksonenko on 4/3/17.
 */
public class LoginActivity extends BaseActivity {

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    protected boolean isHomeAsUpEnabledShown(){
        return false;
    }
}
