package com.example.fu_food.services;

import com.example.fu_food.config.Config;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.FoodCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").create();
    Config config = new Config();
    String baseUrl = config.getApiUrl();

    FoodService foodService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(FoodService.class);

    @GET("api/foods/get-top10-best-selling-foods")
    Call<List<Food>> getTop10BestSellingFoods();

    @GET("api/foods/get-all")
    Call<List<Food>> getAllFoods(@Query("foodType") String foodType);

    @GET("api/foods/get-food-detail")
    Call<Food> getFoodById(@Query("foodId") String foodId);

    @GET("api/foods/get-all-food-of-restaurant")
    Call<List<Food>> getAllFoodsOfRestaurant(@Query("restaurantId") String restaurantId, @Query("foodId") String foodId);

}
