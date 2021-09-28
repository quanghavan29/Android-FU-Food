package com.example.fu_food.services;

import com.example.fu_food.models.User;
import com.example.fu_food.models.UserSignIn;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").create();

    AuthService authService = new Retrofit.Builder()
            .baseUrl("http:192.168.230.2:8081/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(AuthService.class);

    // authenticate - sign in
    @FormUrlEncoded
    @POST("api/authenticate")
    Call<UserSignIn> signIn(@Field("phone") String phone, @Field("password") String password);
}
