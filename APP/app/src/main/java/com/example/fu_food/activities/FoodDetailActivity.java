package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.fu_food.R;
import com.example.fu_food.activities.fragment.ViewPagerAdapter;
import com.example.fu_food.activities.fragment.ViewPagerFoodDetailAdapter;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FoodDetailActivity extends AppCompatActivity {

    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private ViewPagerFoodDetailAdapter adapter;

    private View viewEndAnimation;
    private ImageView viewAnimation;

    private int quantityFoodInCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        viewEndAnimation = findViewById(R.id.viewEndAnimation);
        viewAnimation = findViewById(R.id.viewAnimation);

        ahBottomNavigation = findViewById(R.id.AHBottomNavigation);
        ahBottomNavigationViewPager = findViewById(R.id.AHBottomNavigationViewPager);

        adapter = new ViewPagerFoodDetailAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ahBottomNavigationViewPager.setAdapter(adapter);
        ahBottomNavigationViewPager.setPagingEnabled(true);
//        ahBottomNavigation.setColored(true);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("", R.drawable.ic_food_bank, R.color.gray);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("", R.drawable.ic_cart, R.color.gray);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("", R.drawable.ic_message, R.color.gray);

        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);
        ahBottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.orange));

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                ahBottomNavigationViewPager.setCurrentItem(position);
                return true;
            }
        });

        ahBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ahBottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setQuantityFoodInCart(getTotalQuantityInCart());
    }

    public View getViewEndAnimation() {
        return viewEndAnimation;
    }

    public ImageView getViewAnimation() {
        return viewAnimation;
    }

    public void setQuantityFoodInCart(int quantity) {
        AHNotification notification = new AHNotification.Builder()
                .setText(quantity + "")
                .setBackgroundColor(ContextCompat.getColor(FoodDetailActivity.this, R.color.orange))
                .setTextColor(ContextCompat.getColor(FoodDetailActivity.this, R.color.white))
                .build();
        ahBottomNavigation.setNotification(notification, 1);

        this.quantityFoodInCart = quantityFoodInCart;
    }

    // get all item in cart from shared preferences
    private List<Cart> getAllItemInCart() {
        List<Cart> carts = SharedPrefConfig.getCartsFromSharedPref(FoodDetailActivity.this);

        return carts;
    }

    // get total quantity in cart
    private int getTotalQuantityInCart() {
        List<Cart> carts = getAllItemInCart();
        int totalQuantity = 0;
        for (Cart cart : carts) {
            totalQuantity += cart.getQuantity();
        }
        return totalQuantity;
    }
}