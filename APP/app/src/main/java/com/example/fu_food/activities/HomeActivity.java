package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fu_food.R;
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.models.FoodCategory;
import com.example.fu_food.services.FoodCategoryService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerFoodCategories;
    ImageView imageViewProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerFoodCategories = findViewById(R.id.recyclerFoodCategories);
        imageViewProfileImage = findViewById(R.id.imageViewProfileImage);

        setFoodCategories();
        setImageProfile();
    }

    private void setImageProfile() {
        Intent data = HomeActivity.this.getIntent();
        Bundle bundle = data.getBundleExtra(SignInActivity.KEY_BUNDLE);
        String imageUrl = bundle.getString(SignInActivity.IMAGE_PROFILE_URL);
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
                Toast.makeText(HomeActivity.this, "Call API Success! - " + foodCategories.size(), Toast.LENGTH_SHORT).show();
                for (FoodCategory foodCategory: foodCategories) {
                    foodCategory.setImage(R.drawable.food);
                }
//                FoodCategory foodCategory1 = new FoodCategory("1","Đồ ăn", R.drawable.food);
//                FoodCategory foodCategory2 = new FoodCategory("2","Đồ uống", R.drawable.drinks);
//                FoodCategory foodCategory3 = new FoodCategory("3","Bún phở", R.drawable.noodle);
//                FoodCategory foodCategory4 = new FoodCategory("4","Bánh mì", R.drawable.sandwich);
//                FoodCategory foodCategory5 = new FoodCategory("5","Vỉa hè", R.drawable.street_food);
//
//                foodCategories.add(foodCategory1);
//                foodCategories.add(foodCategory2);
//                foodCategories.add(foodCategory3);
//                foodCategories.add(foodCategory4);
//                foodCategories.add(foodCategory5);

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
}