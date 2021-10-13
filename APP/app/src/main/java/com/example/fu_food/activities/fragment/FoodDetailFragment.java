package com.example.fu_food.activities.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.activities.FoodDetailActivity;
import com.example.fu_food.activities.HomeActivity;
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.adapters.ListFoodOfRestaurantAdapter;
import com.example.fu_food.animation.AnimationUtil;
import com.example.fu_food.models.Food;
import com.example.fu_food.services.FoodService;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailFragment extends Fragment {

    private FrameLayout frameLayoutImageFood;
    private RecyclerView recyclerViewListFoods;
    private TextView textViewFoodName, textViewRestaurantName, textViewPrice,
            textViewNumberOfReview, textViewSalesQuantity, textViewViewMoreFood,
            textViewOrderQuantity;
    private View view;
    private ImageView imageViewFoodDetail;
    private Button buttonAdd, buttonSub, buttonAddToCart;
    private FoodDetailActivity foodDetailActivity;

    public FoodDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_food_detail, container, false);
        foodDetailActivity = (FoodDetailActivity) getActivity();

        recyclerViewListFoods = view.findViewById(R.id.recyclerViewListFoods);

        textViewFoodName = view.findViewById(R.id.textViewFoodName);
        textViewRestaurantName = view.findViewById(R.id.textViewRestaurantName);
        textViewPrice = view.findViewById(R.id.textViewPrice);
        textViewNumberOfReview = view.findViewById(R.id.textViewNumberOfReview);
        textViewSalesQuantity = view.findViewById(R.id.textViewSalesQuantity);
        textViewViewMoreFood = view.findViewById(R.id.textViewViewMoreFood);
        textViewOrderQuantity = view.findViewById(R.id.textViewOrderQuantity);

        imageViewFoodDetail = view.findViewById(R.id.imageViewFoodDetail);

        buttonAddToCart = view.findViewById(R.id.buttonAddToCart);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonSub = view.findViewById(R.id.buttonSub);
        onClickButtonAdd();
        onClickButtonSub();

        String foodId = getFoodIdBundle();
        loadFoodDetail(foodId);

        String restaurantId = getRestaurantIdBundle();
        setRecyclerViewListFoods(restaurantId, foodId);

        onClickButtonAddToCart();

        return view;
    }

    private String getFoodIdBundle() {
        Intent data = foodDetailActivity.getIntent();
        Bundle bundle = data.getBundleExtra("KEY_BUNDLE");
        String foodId = bundle.getString("FOOD_ID");

        return foodId;
    }

    private String getRestaurantIdBundle() {
        Intent data = foodDetailActivity.getIntent();
        Bundle bundle = data.getBundleExtra("KEY_BUNDLE");
        String restaurantId = bundle.getString("RESTAURANT_ID");

        return restaurantId;
    }


    // load food detail
    private void loadFoodDetail(String foodId) {
        FoodService.foodService.getFoodById(foodId).enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                Food food = response.body();
                textViewFoodName.setText(food.getName() + " - ");
                textViewRestaurantName.setText(food.getRestaurant().getName());
                textViewPrice.setText(convertPriceToString(food.getPrice()));
                textViewNumberOfReview.setText(food.getNumberOfReview() + "");
                textViewSalesQuantity.setText(food.getSalesQuantity() + "");
                textViewOrderQuantity.setText("0");

                Picasso.with(foodDetailActivity).load(food.getImageUrl())
                        .into(imageViewFoodDetail);
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {
                Toast.makeText(foodDetailActivity, "Call API Get Food Detail Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // set item view for RecyclerViewListFoods
    private void setRecyclerViewListFoods(String restaurantId, String foodId) {

        FoodCategoryAdapter foodCategoryAdapter = new FoodCategoryAdapter();

        List<Food> foods = new ArrayList<>();

        FoodService.foodService.getAllFoodsOfRestaurant(restaurantId, foodId).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                foods.addAll(response.body());
                textViewViewMoreFood.setText("Xem thêm trong nhà hàng (" + foods.size() + " Items)");

                ListFoodOfRestaurantAdapter listFoodOfRestaurantAdapter = new ListFoodOfRestaurantAdapter(foodDetailActivity, foods);
                recyclerViewListFoods.setLayoutManager(new LinearLayoutManager(foodDetailActivity, RecyclerView.VERTICAL, false));
                recyclerViewListFoods.setAdapter(listFoodOfRestaurantAdapter);
                listFoodOfRestaurantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(foodDetailActivity, "Call API Get All Foods Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void onClickButtonAdd() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int orderQuantity = Integer.parseInt(textViewOrderQuantity.getText().toString());
                orderQuantity += 1;
                textViewOrderQuantity.setText(orderQuantity + "");
            }
        });
    }

    private void onClickButtonSub() {
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int orderQuantity = Integer.parseInt(textViewOrderQuantity.getText().toString());
                if (orderQuantity > 0) {
                    orderQuantity -= 1;
                }
                textViewOrderQuantity.setText(orderQuantity + "");
            }
        });
    }

    private void onClickButtonAddToCart() {
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.translateAnimation(foodDetailActivity.getViewAnimation(), view, foodDetailActivity.getViewEndAnimation(), new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }


                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int quantity = Integer.parseInt(textViewOrderQuantity.getText().toString());
                        foodDetailActivity.setQuantityFoodInCart(quantity);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }
}