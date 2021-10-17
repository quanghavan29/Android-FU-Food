package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fu_food.R;
import com.example.fu_food.adapters.BestSellingFoodAdapter;
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.adapters.ListFoodAdapter;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.FoodCategory;
import com.example.fu_food.services.FoodCategoryService;
import com.example.fu_food.services.FoodService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerViewFoodCategories, recyclerViewBestSellingFood, recyclerViewListFoods;
    ImageView imageViewProfileImage;
    TextView textViewCountFoodCategories, textViewCountBestSellingFoods, textViewCountAllFoods;
    TextView textViewFood, textViewDrinks, textViewNoodle, textViewAllFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewFoodCategories = findViewById(R.id.recyclerViewFoodCategories);
        recyclerViewBestSellingFood = findViewById(R.id.recyclerViewBestSellingFood);
        recyclerViewListFoods = findViewById(R.id.recyclerViewListFoods);
        imageViewProfileImage = findViewById(R.id.imageViewProfileImage);
        textViewCountFoodCategories = findViewById(R.id.textViewCountFoodCategories);
        textViewCountBestSellingFoods = findViewById(R.id.textViewCountBestSellingFoods);
        textViewCountAllFoods = findViewById(R.id.textViewCountAllFoods);

        setImageProfile();
        setRecyclerViewFoodCategories();
        setRecyclerViewBestSellingFood();

        // when the home page start to load => foodType = allFood
        String foodType = "allFood";
        setRecyclerViewListFoods(foodType);

        // Test onclick food categories
        textViewFood = findViewById(R.id.textViewFood);
        textViewDrinks = findViewById(R.id.textViewDrinks);
        textViewNoodle = findViewById(R.id.textViewNoodle);
        textViewAllFood = findViewById(R.id.textViewAllFood);

        onClickFood();
        onClickDrinks();
        onClickNoodle();
        onClickAllFood();

    }

    private void setImageProfile() {
        Intent data = HomeActivity.this.getIntent();
        Bundle bundle = data.getBundleExtra("KEY_BUNDLE");
        String imageUrl = bundle.getString("IMAGE_PROFILE_URL");
        // if don't have image url => set image profile default
        if (imageUrl.equals("") || imageUrl == null) {
            imageViewProfileImage.setImageResource(R.drawable.profile_image_default);
        } else {
            Picasso.with(HomeActivity.this).load(imageUrl)
                    .into(imageViewProfileImage);
        }
    }

    // set item view for RecyclerFoodCategories
    private void setRecyclerViewFoodCategories() {
        List<FoodCategory> foodCategories = new ArrayList<>();

        FoodCategoryService.foodCategoryService.getAllFoodCategories().enqueue(new Callback<List<FoodCategory>>() {
            @Override
            public void onResponse(Call<List<FoodCategory>> call, Response<List<FoodCategory>> response) {
                foodCategories.add(new FoodCategory("all_food", "Tất Cả", "https://res.cloudinary.com/fpt-food/image/upload/v1632993707/FPT%20FOOD/all_food_zubkbn.jpg"));
                foodCategories.addAll(response.body());

                textViewCountFoodCategories.setText("(" + foodCategories.size() + " Danh Mục)");

                FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter(foodCategories, HomeActivity.this, new FoodCategoryAdapter.IOnClickItemFoodCategoriesListener() {
                    @Override
                    public void onClickFoodType(FoodCategory foodCategory) {

                    }
                });
                recyclerViewFoodCategories.setLayoutManager(new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false));
                recyclerViewFoodCategories.setAdapter(foodCategoryAdapter);
                foodCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FoodCategory>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Call API Get All Food Categories Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // set item view for RecyclerViewBestSellingFood
    private void setRecyclerViewBestSellingFood() {
        List<Food> top10BestSellingFoods = new ArrayList<>();

        FoodService.foodService.getTop10BestSellingFoods().enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                top10BestSellingFoods.addAll(response.body());

                textViewCountBestSellingFoods.setText("(" + top10BestSellingFoods.size() + " Items)");

                BestSellingFoodAdapter bestSellingFoodAdapter = new BestSellingFoodAdapter(HomeActivity.this, top10BestSellingFoods);
                recyclerViewBestSellingFood.setLayoutManager(new LinearLayoutManager(HomeActivity.this, RecyclerView.HORIZONTAL, false));
                recyclerViewBestSellingFood.setAdapter(bestSellingFoodAdapter);
                bestSellingFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Call API Get All Best Selling Foods Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // set item view for RecyclerViewListFoods
    private void setRecyclerViewListFoods(String foodType) {

        FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter();

        List<Food> foods = new ArrayList<>();

        FoodService.foodService.getAllFoods(foodType).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                foods.addAll(response.body());

                textViewCountAllFoods.setText("(" + foods.size() + " Items)");

                ListFoodAdapter listFoodAdapter = new ListFoodAdapter(HomeActivity.this, foods);
                recyclerViewListFoods.setLayoutManager(new LinearLayoutManager(HomeActivity.this, RecyclerView.VERTICAL, false));
                recyclerViewListFoods.setAdapter(listFoodAdapter);
                listFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Call API Get All Best Selling Foods Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void onClickFood() {
        String foodType = "food";
        textViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewFood.setTextColor(Color.parseColor("#f37021"));

                textViewDrinks.setTextColor(Color.parseColor("#808080"));
                textViewNoodle.setTextColor(Color.parseColor("#808080"));
                textViewAllFood.setTextColor(Color.parseColor("#808080"));
                setRecyclerViewListFoods(foodType);
            }
        });
    }

    private void onClickDrinks() {
        String foodType = "drinks";
        textViewDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewDrinks.setTextColor(Color.parseColor("#f37021"));

                textViewFood.setTextColor(Color.parseColor("#808080"));
                textViewNoodle.setTextColor(Color.parseColor("#808080"));
                textViewAllFood.setTextColor(Color.parseColor("#808080"));
                setRecyclerViewListFoods(foodType);
            }
        });
    }

    private void onClickNoodle() {
        String foodType = "noodle";
        textViewNoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewNoodle.setTextColor(Color.parseColor("#f37021"));

                textViewFood.setTextColor(Color.parseColor("#808080"));
                textViewDrinks.setTextColor(Color.parseColor("#808080"));
                textViewAllFood.setTextColor(Color.parseColor("#808080"));
                setRecyclerViewListFoods(foodType);
            }
        });
    }

    private void onClickAllFood() {
        String foodType = "allFood";
        textViewAllFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewAllFood.setTextColor(Color.parseColor("#f37021"));

                textViewFood.setTextColor(Color.parseColor("#808080"));
                textViewDrinks.setTextColor(Color.parseColor("#808080"));
                textViewNoodle.setTextColor(Color.parseColor("#808080"));
                setRecyclerViewListFoods(foodType);
            }
        });
    }

}