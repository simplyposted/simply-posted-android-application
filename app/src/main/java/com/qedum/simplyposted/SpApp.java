package com.qedum.simplyposted;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SpApp extends Application {

    private static Context context;
    private static String appVersion = "";
    private static int buildNumber = 0;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        context = getApplicationContext();
//        isDebuggable = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersion = pInfo.versionName;
            buildNumber = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getAppVersion() {
        return appVersion;
    }

    public static int getBuildNumber() {
        return buildNumber;
    }

    public static Context getContext() {
        return context;
    }
}
