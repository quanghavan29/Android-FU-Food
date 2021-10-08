package com.example.fu_food.services;

import com.example.fu_food.BuildConfig;
import com.example.fu_food.config.Config;
import com.example.fu_food.models.User;
import com.example.fu_food.models.UserSignIn;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Properties;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").create();
    Config config = new Config();
    String baseUrl = config.getApiUrl();

    AuthService authService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(AuthService.class);

    // authenticate - sign in
    @FormUrlEncoded
    @POST("api/authenticate")
    Call<UserSignIn> signIn(@Field("phone") String phone, @Field("password") String password);
}
