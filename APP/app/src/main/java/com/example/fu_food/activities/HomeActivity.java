package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fu_food.R;
import com.example.fu_food.adapters.BestSellingFoodAdapter;
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.FoodCategory;
import com.example.fu_food.services.FoodCategoryService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerFoodCategories, recyclerBestSellingFood;
    ImageView imageViewProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerFoodCategories = findViewById(R.id.recyclerFoodCategories);
        recyclerBestSellingFood = findViewById(R.id.recyclerBestSellingFood);
        imageViewProfileImage = findViewById(R.id.imageViewProfileImage);

        setImageProfile();
        setFoodCategories();
        setBestSellingFoods();

    }

    private void setImageProfile() {
        Intent data = HomeActivity.this.getIntent();
        Bundle bundle = data.getBundleExtra(SignInActivity.KEY_BUNDLE);
        String imageUrl = bundle.getString(SignInActivity.IMAGE_PROFILE_URL);
        // if don't have image url => set image profile default
        if (imageUrl.equals("") || imageUrl == null) {
            imageViewProfileImage.setImageResource(R.drawable.profile_image_default);
        } else {
            Picasso.with(HomeActivity.this).load(imageUrl)
                    .into(imageViewProfileImage);
        }
    }

    private void setFoodCategories() {

        FoodCategoryService.foodCategoryService.getAllFoodCategories().enqueue(new Callback<List<FoodCategory>>() {
            @Override
            public void onResponse(Call<List<FoodCategory>> call, Response<List<FoodCategory>> response) {

                List<FoodCategory> foodCategories = response.body();

                FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter(foodCategories, HomeActivity.this);
                recyclerFoodCategories.setLayoutManager(new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false));
                recyclerFoodCategories.setAdapter(foodCategoryAdapter);
                foodCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FoodCategory>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Call API Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setBestSellingFoods() {

        List<Food> bestSellingFoods = new ArrayList<>();
        Food food1 = new Food("1", "Bún Cá", 30000, "noodle", "https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/bun_ca_k0pehs.jpg", 43);
        Food food2 = new Food("2", "Bún Bò Huế", 35000, "noodle", "https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/bun_bo_hue_mhhwtp.jpg", 30);
        Food food3 = new Food("3", "Hindlands Coffe", 49000, "noodle", "https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/highlands_coffe_hrvjsp.jpg", 28);
        Food food4 = new Food("4", "Bánh Mì", 25000, "noodle", "https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/banh_mi_nam_dinh_msawq2.jpg", 19);
        Food food5 = new Food("5", "Cơm Gà", 30000, "noodle", "https://res.cloudinary.com/fpt-food/image/upload/v1632900950/FPT%20FOOD/com_ga_lmyncr.jpg", 12);

        bestSellingFoods.add(food1);
        bestSellingFoods.add(food2);
        bestSellingFoods.add(food3);
        bestSellingFoods.add(food4);
        bestSellingFoods.add(food5);


        BestSellingFoodAdapter bestSellingFoodAdapter = new BestSellingFoodAdapter(HomeActivity.this, bestSellingFoods);
        recyclerBestSellingFood.setLayoutManager(new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false));
        recyclerBestSellingFood.setAdapter(bestSellingFoodAdapter);
        bestSellingFoodAdapter.notifyDataSetChanged();

    }

}