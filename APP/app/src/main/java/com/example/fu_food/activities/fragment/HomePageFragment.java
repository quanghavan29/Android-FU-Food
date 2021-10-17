package com.example.fu_food.activities.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fu_food.R;
import com.example.fu_food.activities.HomeActivity;
import com.example.fu_food.activities.MainActivity;
import com.example.fu_food.activities.SignInActivity;
import com.example.fu_food.adapters.BestSellingFoodAdapter;
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.adapters.ListFoodAdapter;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.FoodCategory;
import com.example.fu_food.services.FoodCategoryService;
import com.example.fu_food.services.FoodService;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageFragment extends Fragment {

    RecyclerView recyclerViewFoodCategories, recyclerViewBestSellingFood, recyclerViewListFoods;
    private View view;
    private MainActivity mainActivity;
    private DrawerLayout drawerLayoutHomePage;
    private NavigationView navViewMain;
    private MaterialToolbar toolBarMenu;
    private TextView textViewCountFoodCategories, textViewCountBestSellingFoods, textViewCountAllFoods,
                     textViewAllFoods;

    ProgressDialog progressDialog;

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        mainActivity = (MainActivity) getActivity();

        recyclerViewFoodCategories = view.findViewById(R.id.recyclerViewFoodCategories);
        recyclerViewBestSellingFood = view.findViewById(R.id.recyclerViewBestSellingFood);
        recyclerViewListFoods = view.findViewById(R.id.recyclerViewListFoods);

        textViewCountFoodCategories = view.findViewById(R.id.textViewCountFoodCategories);
        textViewCountBestSellingFoods = view.findViewById(R.id.textViewCountBestSellingFoods);
        textViewCountAllFoods = view.findViewById(R.id.textViewCountAllFoods);
        textViewAllFoods = view.findViewById(R.id.textViewAllFoods);

        drawerLayoutHomePage = view.findViewById(R.id.drawerLayoutMain);
        navViewMain = (NavigationView) view.findViewById(R.id.navViewMain);
        toolBarMenu = view.findViewById(R.id.toolBarMenu);

        setRecyclerViewFoodCategories();
        setRecyclerViewBestSellingFood();

        // when the home page start to load => foodType = allFood
        String foodType = "allFood";
        setRecyclerViewListFoods(foodType);

        return view;
    }

    // set item view for RecyclerFoodCategories
    private void setRecyclerViewFoodCategories() {
        List<FoodCategory> foodCategories = new ArrayList<>();

        FoodCategoryService.foodCategoryService.getAllFoodCategories().enqueue(new Callback<List<FoodCategory>>() {
            @Override
            public void onResponse(Call<List<FoodCategory>> call, Response<List<FoodCategory>> response) {
                foodCategories.add(new FoodCategory("allFood", "Tất Cả", "https://res.cloudinary.com/fpt-food/image/upload/v1632993707/FPT%20FOOD/all_food_zubkbn.jpg"));
                foodCategories.addAll(response.body());

                textViewCountFoodCategories.setText("(" + foodCategories.size() + " Danh Mục)");

                FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter(foodCategories, mainActivity, new FoodCategoryAdapter.IOnClickItemFoodCategoriesListener() {
                    @Override
                    public void onClickFoodType(FoodCategory foodCategory) {
                        setRecyclerViewListFoods(foodCategory.getId());
                        textViewAllFoods.setText(foodCategory.getName());
                    }
                });
                recyclerViewFoodCategories.setLayoutManager(new LinearLayoutManager(mainActivity, RecyclerView.HORIZONTAL, false));
                recyclerViewFoodCategories.setAdapter(foodCategoryAdapter);
                foodCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FoodCategory>> call, Throwable t) {
                Toast.makeText(mainActivity, "Call API Get All Food Categories Error!", Toast.LENGTH_SHORT).show();
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

                BestSellingFoodAdapter bestSellingFoodAdapter = new BestSellingFoodAdapter(mainActivity, top10BestSellingFoods);
                recyclerViewBestSellingFood.setLayoutManager(new LinearLayoutManager(mainActivity, RecyclerView.HORIZONTAL, false));
                recyclerViewBestSellingFood.setAdapter(bestSellingFoodAdapter);
                bestSellingFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(mainActivity, "Call API Get All Best Selling Foods Error!", Toast.LENGTH_SHORT).show();
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

                ListFoodAdapter listFoodAdapter = new ListFoodAdapter(mainActivity, foods);
                recyclerViewListFoods.setLayoutManager(new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false));
                recyclerViewListFoods.setAdapter(listFoodAdapter);
                listFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(mainActivity, "Call API Get All Best Selling Foods Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setProgressDialog() {
        progressDialog = new ProgressDialog(mainActivity);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
    }

    private void dismissProgressDialog() {
        try {
            Thread.sleep(3000);
            progressDialog.dismiss();
        } catch (InterruptedException e) {
            e.printStackTrace();
            progressDialog.dismiss();
        }
    }

}