package com.qedum.simplyposted.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.qedum.simplyposted.SpApp;
import com.qedum.simplyposted.model.api.UserResponse;

import java.util.UUID;

public class Storage {
    private static final int DEFAULT_CHECKBOX_VALUE = -1;
    private static final String EMPTY_STRING = "";
    private static final String PREFERENCES_NAME = "com.qedum.simplyposted.util.Storage";
    private static final String LOGGED_IN_KEY = "logged_in";

    private static final String FB_CONNECTED_KEY = "FB_CONNECTED_KEY";
    private static final String COMPANY_NAME_KEY = "COMPANY_NAME_KEY";
    private static final String COMPANY_PHONE_KEY = "COMPANY_PHONE_KEY";
    private static final String PACKAGE_KEY = "PACKAGE_KEY";
    private static final String ZIP_KEY = "ZIP_KEY";

    private static final String SECRET_KEY = "secret_key";
    private static final String PASSWORD_KEY = "password_key";
    private static final String AUTH_TOKEN_KEY = "AUTH_TOKEN_KEY";
    private static final String AUTH_TOKEN_VALUE = "Token %s";
    private static final String KEY_USER = "key_user";

    private static Storage instance;
    private SharedPreferences sharedPreferences;

    private Storage() {
        sharedPreferences = SpApp.getContext().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
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

    public String getPassword() {
        return getDecryptedValueForKey(PASSWORD_KEY);
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PASSWORD_KEY, (password));
        editor.apply();
    }

    private synchronized String getDecryptedValueForKey(String key) {
        String tmp = sharedPreferences.getString(key, "");
        if (tmp.equals("")) {
            return tmp;
        } else {
            return StringsEncryptorDecryptor.decrypt(getSecretKey(), tmp);
        }
    }

    public synchronized String getSecretKey() {
        String secretKey = sharedPreferences.getString(SECRET_KEY, "");
        if (secretKey.equals("")) {
            secretKey = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(SECRET_KEY, secretKey);
            editor.apply();
        }
        return secretKey;
    }

    public void clearAll() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public String getAuthToken() {
        return sharedPreferences.getString(AUTH_TOKEN_KEY, "");
    }

    public void setAuthToken(String authToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AUTH_TOKEN_KEY, String.format(AUTH_TOKEN_VALUE, authToken));
        editor.apply();
    }

    public void setUser(UserResponse value) {
        Gson gson = new Gson();
        String json = gson.toJson(value);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, json);
        editor.apply();
    }

    public UserResponse getUser() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_USER, EMPTY_STRING);
        UserResponse profile = gson.fromJson(json, UserResponse.class);
        return profile;
    }

}