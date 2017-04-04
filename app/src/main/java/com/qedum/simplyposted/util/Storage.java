package com.qedum.simplyposted.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.qedum.simplyposted.SpApp;

public class Storage {
    private static final int DEFAULT_CHECKBOX_VALUE = -1;
    private static final String EMPTY_STRING = "";
    private static final String PREFERENCES_NAME = "com.qedum.simplyposted.util.Storage";
    private static final String LOGGED_IN_KEY = "logged_in";

    private static final String FB_CONNECTED_KEY = "FB_CONNECTED_KEY";
    private static final String EMAIL_KEY = "email_key";
    private static final String PASSWORD_KEY = "password_key";
    private static final String FIRST_NAME_KEY = "first_name_key";
    private static final String LAST_NAME_KEY = "last_name_key";
    private static final String ADDRESS_KEY = "address_key";
    private static final String COMPANY_NAME_KEY = "COMPANY_NAME_KEY";
    private static final String COMPANY_PHONE_KEY = "COMPANY_PHONE_KEY";
    private static final String PACKAGE_KEY = "PACKAGE_KEY";
    private static final String ZIP_KEY = "ZIP_KEY";


    private static Storage instance;
    private SharedPreferences sharedPreferences;

    private Storage() {
        sharedPreferences = SpApp.getAppContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    private void put(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private boolean get(String key, Boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public String getCompanyName() {
        return sharedPreferences.getString(COMPANY_NAME_KEY, EMPTY_STRING);
    }

    public void setCompanyName(String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(COMPANY_NAME_KEY, value);
        editor.apply();
    }

    public String getCompanyPhone() {
        return sharedPreferences.getString(COMPANY_PHONE_KEY, EMPTY_STRING);
    }

    public void setCompanyPhone(String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(COMPANY_PHONE_KEY, value);
        editor.apply();
    }

    public String getZipCode() {
        return sharedPreferences.getString(ZIP_KEY, EMPTY_STRING);
    }

    public void setZipCode(String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ZIP_KEY, value);
        editor.apply();
    }

    public void setUserPackage(int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PACKAGE_KEY, value);
        editor.apply();
    }

    public int getUserPackage() {
        return sharedPreferences.getInt(PACKAGE_KEY, DEFAULT_CHECKBOX_VALUE);
    }

    private boolean getBoolean(String key) {
        return get(key, false);
    }

    public boolean isUserLoggedIn() {
        return getBoolean(LOGGED_IN_KEY);
    }

    public void setUserLoggedIn(boolean isLoggedIn) {
        put(LOGGED_IN_KEY, isLoggedIn);
    }


    public boolean isFbConnected() {
        return getBoolean(FB_CONNECTED_KEY);
    }

    public void setFbConnected(boolean value) {
        put(LOGGED_IN_KEY, value);
    }

//    public void saveUser(User user) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(EMAIL_KEY, user.getEmail());
//        editor.putString(FIRST_NAME_KEY, user.getFirstName());
//        editor.putString(LAST_NAME_KEY, user.getLastName());
//        editor.apply();
//    }
//
//    public User getUser() {
//        RitualsUser user = new RitualsUser();
//        user.setEmail(sharedPreferences.getString(EMAIL_KEY, ""));
//        user.setFirstName(sharedPreferences.getString(FIRST_NAME_KEY, ""));
//        user.setLastName(sharedPreferences.getString(LAST_NAME_KEY, ""));
//        return user;
//    }

    public void clearAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}