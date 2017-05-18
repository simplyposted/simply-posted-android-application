package com.qedum.simplyposted.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qedum.simplyposted.R;
import com.qedum.simplyposted.SpApp;
import com.qedum.simplyposted.model.SchedulePost;
import com.qedum.simplyposted.model.api.LoginResponse;
import com.qedum.simplyposted.model.api.PostResponse;
import com.qedum.simplyposted.model.api.UserResponse;
import com.qedum.simplyposted.util.Storage;

import java.util.List;

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

    public void register(String email, String password, ApiCallback<ResponseBody> callback) {
        Call<ResponseBody> call = profileApiService.register(email, email, password);
        call.enqueue(new DefaultApiListener<ResponseBody>(callback));
    }

    public void login(String email, String password, ApiCallback<LoginResponse> callback) {
        Call<LoginResponse> call = profileApiService.login(email, email, password);
        call.enqueue(new DefaultApiListener<LoginResponse>(callback));
    }


    public void getPosts(ApiCallback<List<PostResponse>> callback) {
        Call<List<PostResponse>> call = profileApiService.getPosts(Storage.getInstance().getAuthToken(), Storage.getInstance().getUser().getId().toString());
        call.enqueue(new DefaultApiListener<List<PostResponse>>(callback));
    }

    public void getUser(ApiCallback<UserResponse> callback) {
        Call<UserResponse> call = profileApiService.getUser(Storage.getInstance().getAuthToken());
        call.enqueue(new DefaultApiListener<UserResponse>(callback));
    }

    public void resetPassword(String email, ApiCallback<ResponseBody> callback) {
        Call<ResponseBody> call = profileApiService.resetPassword(email);
        call.enqueue(new DefaultApiListener<ResponseBody>(callback));
    }

    public void logout(ApiCallback<ResponseBody> callback) {
        Call<ResponseBody> call = profileApiService.logout(Storage.getInstance().getAuthToken());
        call.enqueue(new DefaultApiListener<ResponseBody>(callback));
    }

    public void addPost(Long postId, long userId, String publicationDate, ApiCallback<SchedulePost> callback) {
        Call<SchedulePost> call = profileApiService.addPost(Storage.getInstance().getAuthToken(), postId, userId, publicationDate);
        call.enqueue(new DefaultApiListener<SchedulePost>(callback));
    }
}