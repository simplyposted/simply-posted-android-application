package com.qedum.simplyposted.api;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qedum.simplyposted.R;
import com.qedum.simplyposted.SpApp;
import com.qedum.simplyposted.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient instance;

    private Retrofit retrofitProfileService;
    private Gson gson;
    private IApiClient profileApiService;


//
//    public void getAreas(ApiCallback<Areas> callback) {
//        Call<Areas> call = profileApiService.getAreas(TimerApp.getUdid());
//        call.enqueue(new DefaultApiListener<Areas>(callback));
//    }

    private ApiClient() {
        gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd")
                .create();
        retrofitProfileService = new Retrofit.Builder()
                .baseUrl(SpApp.getContext().getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        profileApiService = retrofitProfileService.create(IApiClient.class);
    }

    public static ApiClient getSharedInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public static boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) SpApp.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public void register(User user, ApiCallback<ResponseBody> callback) {
        Call<ResponseBody> call = profileApiService.register(user.getEmail(), user.getEmail(), user.getPassword());
        call.enqueue(new DefaultApiListener<ResponseBody>(callback));
    }

    public void login(User user, ApiCallback<ResponseBody> callback) {
        Call<ResponseBody> call = profileApiService.login(user.getEmail(), user.getEmail(), user.getPassword());
        call.enqueue(new DefaultApiListener<ResponseBody>(callback));
    }
}