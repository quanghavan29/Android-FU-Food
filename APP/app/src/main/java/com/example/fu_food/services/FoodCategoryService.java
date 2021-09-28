package com.example.fu_food.services;

import com.example.fu_food.models.FoodCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface FoodCategoryService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").create();

    FoodCategoryService foodCategoryService = new Retrofit.Builder()
            .baseUrl("http:192.168.230.2:8081/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(FoodCategoryService.class);

    @GET("api/food-categories/get-all")
    Call<List<FoodCategory>> getAllFoodCategories();

}
