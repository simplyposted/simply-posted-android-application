package com.qedum.simplyposted.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.util.Storage;


public class SplashActivity extends AppCompatActivity {

    private static final int TIME_ACTIVITY_SHOWN = 2 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startContent();
            }
        }, TIME_ACTIVITY_SHOWN);
    }

    private void startContent() {
        Intent intentTarget;

        //TODO: remove the line below
        Storage.getInstance().setUserLoggedIn(true);

        if (Storage.getInstance().isUserLoggedIn()) {
            intentTarget = MainActivity.getLaunchIntent(this);
        } else {
            intentTarget = LoginActivity.getLaunchIntent(this);
        }
        startActivity(intentTarget);
        finish();
    }
}