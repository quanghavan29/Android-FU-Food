package com.example.fu_food.services;

import com.example.fu_food.config.Config;
import com.example.fu_food.models.Cart;
import com.example.fu_food.models.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").create();
    Config config = new Config();
    String baseUrl = config.getApiUrl();

    OrderService orderService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(OrderService.class);

    // orders - get-orders-of-user
    @GET("api/orders/get-orders-of-user")
    Call<List<Order>> getOrdersOfUser(@Query("userId") String userId);

}
