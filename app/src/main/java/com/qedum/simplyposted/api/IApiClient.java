package com.qedum.simplyposted.api;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IApiClient {
    @FormUrlEncoded
    @POST("api/v1/account/registration/")
    Call<ResponseBody> register(@Field("username") String username, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/v1/account/login/")
    Call<ResponseBody> login(@Field("username") String username, @Field("email") String email, @Field("password") String password);
//
//    @GET("polygon/per/device")
//    Call<Areas> getAreas(@Query("udid") String udid);
}