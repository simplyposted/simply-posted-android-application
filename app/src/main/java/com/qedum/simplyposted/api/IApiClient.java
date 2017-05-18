package com.qedum.simplyposted.api;


import com.qedum.simplyposted.model.SchedulePost;
import com.qedum.simplyposted.model.api.LoginResponse;
import com.qedum.simplyposted.model.api.PostResponse;
import com.qedum.simplyposted.model.api.UserResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiClient {
    @FormUrlEncoded
    @POST("api/v1/account/registration/")
    Call<ResponseBody> register(@Field("username") String username, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/v1/account/login/")
    Call<LoginResponse> login(@Field("username") String username, @Field("email") String email, @Field("password") String password);

    @GET("api/v1/account/user/")
    Call<UserResponse> getUser(@Header("Authorization") String token);

    @GET("api/v1/post/{userId}/")
    Call<List<PostResponse>> getPosts(@Header("Authorization") String token, @Path("userId") String userId);

    @FormUrlEncoded
    @POST("api/v1/post/")
    Call<SchedulePost> addPost(@Header("Authorization") String token, @Field("post") long postId, @Field("user") long userId, @Field("publication_date") String publicationDate);

    @FormUrlEncoded
    @POST("api/v1/account/password/reset/")
    Call<ResponseBody> resetPassword(@Field("email") String email);

    @POST("api/v1/account/logout/")
    Call<ResponseBody> logout(@Header("Authorization") String token);
}