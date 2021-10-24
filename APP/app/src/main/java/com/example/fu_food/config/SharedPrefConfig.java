package com.example.fu_food.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fu_food.activities.fragment.CartFragment;
import com.example.fu_food.models.Cart;
import com.example.fu_food.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefConfig {

    private static final String CART_FOOD = "CART_FOOD";

    public static void saveCartFoodToSharedPref(Context context, List<Cart> carts) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CART_FILE.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // save cart to shared preferences

        Gson gson = new Gson();
        String jsonString = gson.toJson(carts);

        editor.putString("CART_FOOD", jsonString);
        editor.commit();

    }

    public static List<Cart> getCartsFromSharedPref(Context context) {
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

    public static void saveUserLoginToSharedPref(Context context, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_LOGIN_FILE.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // save user login to shared preferences

        Gson gson = new Gson();
        String jsonString = gson.toJson(user);

        editor.putString("USER_LOGIN", jsonString);
        editor.commit();
    }

    public static User getUserLoginFromSharedPref(Context context) {
        User user = new User();

        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_LOGIN_FILE.txt", Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("USER_LOGIN", "");

        if (jsonString != null && !jsonString.equals("")) {
            Gson gson = new Gson();
            Type type = new TypeToken<User>() {}.getType();
            user = gson.fromJson(jsonString, type);

            return user;
        }

        return user;
    }

    public static void clearSharedPref(Context context) {
        SharedPreferences sharedPreferencesUser = context.getSharedPreferences("USER_LOGIN_FILE.txt", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferencesCart = context.getSharedPreferences("CART_FILE.txt", Context.MODE_PRIVATE);
        sharedPreferencesUser.edit().clear().commit();
        sharedPreferencesCart.edit().clear().commit();
    }

}
