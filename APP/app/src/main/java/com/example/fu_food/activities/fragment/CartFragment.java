package com.example.fu_food.activities.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.activities.FoodDetailActivity;
import com.example.fu_food.activities.SignInActivity;
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.adapters.ListFoodInCartAdapter;
import com.example.fu_food.adapters.ListFoodOfRestaurantAdapter;
import com.example.fu_food.animation.AnimationUtil;
import com.example.fu_food.models.Cart;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.Restaurant;
import com.example.fu_food.services.FoodService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    private View view;
    private FoodDetailActivity foodDetailActivity;

    private RecyclerView recyclerViewListFoods;
    private TextView textViewTotalQuantity, textViewTotalAmount;
    private Button buttonOrder;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        setRecyclerViewListFoods();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        foodDetailActivity = (FoodDetailActivity) getActivity();

        textViewTotalQuantity = view.findViewById(R.id.textViewTotalQuantity);
        textViewTotalAmount = view.findViewById(R.id.textViewTotalAmount);
        buttonOrder = view.findViewById(R.id.buttonOrder);

        recyclerViewListFoods = view.findViewById(R.id.recyclerViewListFoods);

        setRecyclerViewListFoods();

        return view;
    }

    // set item view for RecyclerViewListFoods
    public void setRecyclerViewListFoods() {
        List<Cart> carts = getAllItemInCart();

        int totalQuantity = 0;
        int totalAmount = 0;
        for (Cart cart : carts) {
            totalQuantity += cart.getQuantity();
            totalAmount += cart.getFood().getPrice() * cart.getQuantity();
        }

        textViewTotalQuantity.setText(totalQuantity + "");
        textViewTotalAmount.setText(convertPriceToString(totalAmount));
        buttonOrder.setText("ĐẶT ĐƠN - " + convertPriceToString(totalAmount));

        ListFoodInCartAdapter listFoodInCartAdapter = new ListFoodInCartAdapter(foodDetailActivity, carts);
        recyclerViewListFoods.setLayoutManager(new LinearLayoutManager(foodDetailActivity, RecyclerView.VERTICAL, false));
        recyclerViewListFoods.setAdapter(listFoodInCartAdapter);
        listFoodInCartAdapter.notifyDataSetChanged();
    }


    private List<Cart> getAllItemInCart() {
        List<Cart> carts = new ArrayList<>();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CART_FILE.txt", Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("CART_FOOD", "");

        if (jsonString != null && !jsonString.equals("")) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cart>>() {}.getType();
            carts = gson.fromJson(jsonString, type);

            return carts;
        }

        return carts;

    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }


}