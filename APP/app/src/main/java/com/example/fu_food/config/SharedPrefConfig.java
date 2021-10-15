package com.example.fu_food.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fu_food.activities.fragment.CartFragment;
import com.example.fu_food.models.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefConfig {

    private static final String CART_FOOD = "CART_FOOD";

    public static void saveCartFoodSharedPref(Context context, List<Cart> carts) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CART_FILE.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // save cart to shared preferences

        Gson gson = new Gson();
        String jsonString = gson.toJson(carts);

        editor.putString("CART_FOOD", jsonString);
        editor.commit();

    }

    public static List<Cart> getCartsPref(Context context) {
        List<Cart> carts = new ArrayList<>();

        SharedPreferences sharedPreferences = context.getSharedPreferences("CART_FILE.txt", Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("CART_FOOD", "");

        if (jsonString != null && !jsonString.equals("")) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cart>>() {}.getType();
            carts = gson.fromJson(jsonString, type);

            return carts;
        }

        return carts;
    }

}
