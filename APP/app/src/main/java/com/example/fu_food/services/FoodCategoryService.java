package com.example.fu_food.services;

import com.example.fu_food.config.Config;
import com.example.fu_food.models.FoodCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodCategoryService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").create();
    Config config = new Config();
    String baseUrl = config.getApiUrl();

    FoodCategoryService foodCategoryService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(FoodCategoryService.class);

    @GET("api/food-categories/get-all")
    Call<List<FoodCategory>> getAllFoodCategories();

}
