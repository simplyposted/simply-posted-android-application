package com.qedum.simplyposted.api;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultApiListener<T> implements Callback<T> {

    public static final String RESPONSE_NO_INTERNET_CONNECTION = "no_internet_connection";
    public static final String RESPONSE_SERVER_ERROR = "server_error";
    private ApiCallback callback;

    public DefaultApiListener(@Nullable ApiCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (callback != null) {
            Integer statusCode = response.code();
            if (statusCode == 200 || statusCode == 201) {
                T responseBody = (T) response.body();
                callback.deliverSuccess(responseBody);
            } else {
                callback.deliverError(statusCode.toString(), tryGetErrorMessage(response));
            }
        }
    }

    private String tryGetErrorMessage(Response response) {
        try {
            return response.errorBody().string();
        } catch (IOException e) {
            e.printStackTrace();
            return response.message();
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if (ApiClient.hasConnection())
            callback.deliverError(RESPONSE_SERVER_ERROR, null);
        else
            callback.deliverError(RESPONSE_NO_INTERNET_CONNECTION, null);
    }
}