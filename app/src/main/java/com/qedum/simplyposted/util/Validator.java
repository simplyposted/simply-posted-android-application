package com.qedum.simplyposted.util;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class Validator {
    protected static final String EMAIL_REGEX = "[a-zA-Z0-9\\+\\._%\\-\\+]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";
    
    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isPasswordValid(String password) {
        return (password.length() > 7);
    }


    public static boolean isJsonFieldNull(JSONObject object, String name) {
        Object value = object.opt(name);
        return value == null || value == JSONObject.NULL || "null".equals(value);
    }
}
