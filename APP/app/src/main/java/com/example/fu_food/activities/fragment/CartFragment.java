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
import android.widget.EditText;
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
import com.example.fu_food.activities.MainActivity;
import com.example.fu_food.activities.MyOrderActivity;
import com.example.fu_food.activities.SignInActivity;
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.adapters.ListFoodInCartAdapter;
import com.example.fu_food.adapters.ListFoodOfRestaurantAdapter;
import com.example.fu_food.animation.AnimationUtil;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.Cart;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.Order;
import com.example.fu_food.models.Restaurant;
import com.example.fu_food.models.User;
import com.example.fu_food.models.UserSignIn;
import com.example.fu_food.services.CheckOutService;
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
    private TextView textViewTotalQuantity, textViewTotalAmount,
                    textViewPhone, textViewFullName;
    private Button buttonOrder;
    private EditText editTextAddress;

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
        textViewPhone = view.findViewById(R.id.textViewPhone);
        textViewFullName = view.findViewById(R.id.textViewFullName);

        editTextAddress = view.findViewById(R.id.editTextAddress);

        buttonOrder = view.findViewById(R.id.buttonOrder);

        recyclerViewListFoods = view.findViewById(R.id.recyclerViewListFoods);

        setInfoDelivery();
        setRecyclerViewListFoods();
        checkOut();

        return view;
    }

    // set info delivery of user
    private void setInfoDelivery() {
        User user = SharedPrefConfig.getUserLoginFromSharedPref(foodDetailActivity);

        textViewFullName.setText(user.getFullName() + " - ");
        textViewPhone.setText(user.getPhone());
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

        ListFoodInCartAdapter listFoodInCartAdapter = new ListFoodInCartAdapter(foodDetailActivity, carts, new ListFoodInCartAdapter.IOnClickItemCartListener() {
            @Override
            public void onClickButtonAdd(Cart cart) {

                int totalQuantity = Integer.parseInt(textViewTotalQuantity.getText().toString());
                int totalAmount = getTotalAmount();
                totalQuantity += 1;
                totalAmount += cart.getFood().getPrice();

                textViewTotalQuantity.setText(totalQuantity + "");
                textViewTotalAmount.setText(convertPriceToString(totalAmount));
                buttonOrder.setText("ĐẶT ĐƠN - " + convertPriceToString(totalAmount));

                foodDetailActivity.setQuantityFoodInCart(totalQuantity);

            }

            @Override
            public void onClickButtonSub(Cart cart) {

                int totalQuantity = Integer.parseInt(textViewTotalQuantity.getText().toString());
                int totalAmount = getTotalAmount();
                if (cart.getQuantity() > 1) {
                    totalQuantity -= 1;
                    totalAmount -= cart.getFood().getPrice();
                }

                textViewTotalQuantity.setText(totalQuantity + "");
                textViewTotalAmount.setText(convertPriceToString(totalAmount));
                buttonOrder.setText("ĐẶT ĐƠN - " + convertPriceToString(totalAmount));

                foodDetailActivity.setQuantityFoodInCart(totalQuantity);

            }

            @Override
            public void onClickButtonDelete(Cart cart) {
                int totalQuantity = Integer.parseInt(textViewTotalQuantity.getText().toString());
                int totalAmount = getTotalAmount();

                totalQuantity -= cart.getQuantity();
                totalAmount -= cart.getFood().getPrice() * cart.getQuantity();

                textViewTotalQuantity.setText(totalQuantity + "");
                textViewTotalAmount.setText(convertPriceToString(totalAmount));
                buttonOrder.setText("ĐẶT ĐƠN - " + convertPriceToString(totalAmount));
            }
        });
        recyclerViewListFoods.setLayoutManager(new LinearLayoutManager(foodDetailActivity, RecyclerView.VERTICAL, false));
        recyclerViewListFoods.setAdapter(listFoodInCartAdapter);
        listFoodInCartAdapter.notifyDataSetChanged();
    }

    private void checkOut() {
        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Cart> carts = SharedPrefConfig.getCartsFromSharedPref(foodDetailActivity);

                if (carts.size() == 0) {
                    Toast.makeText(foodDetailActivity, "Giỏ hàng của bạn trống!", Toast.LENGTH_SHORT).show();
                } else {
                    String address = editTextAddress.getText().toString().trim();
                    if (address.isEmpty() || address == "") {
                        Toast.makeText(foodDetailActivity, "Hãy nhập địa chỉ giao hàng!", Toast.LENGTH_SHORT).show();
                    } else {
                        int totalAmount = getTotalAmount();
                        int totalQuantity = getTotalQuantityInCart();
                        String userId = SharedPrefConfig.getUserLoginFromSharedPref(foodDetailActivity).getId();

                        CheckOutService.checkOutService.checkOut(carts, address, userId, totalAmount, totalQuantity).enqueue(new Callback<Order>() {
                            @Override
                            public void onResponse(Call<Order> call, Response<Order> response) {
                                Toast.makeText(foodDetailActivity, "Check out success!", Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferencesCart = foodDetailActivity.getSharedPreferences("CART_FILE.txt", getContext().MODE_PRIVATE);
                                sharedPreferencesCart.edit().clear().commit();

                                // open my order activity
                                Intent intent = new Intent(foodDetailActivity, MyOrderActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<Order> call, Throwable t) {
                                Toast.makeText(foodDetailActivity, "Call api error!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

            }
        });
    }

    // get carts from shared preferences
    private List<Cart> getAllItemInCart() {
        List<Cart> carts = SharedPrefConfig.getCartsFromSharedPref(foodDetailActivity);

        return carts;

    }

    // get total amount of carts
    public int getTotalAmount() {
        List<Cart> carts = SharedPrefConfig.getCartsFromSharedPref(getActivity());

        int totalAmount = 0;
        for (Cart cart : carts) {
            totalAmount += cart.getFood().getPrice() * cart.getQuantity();
        }

        return totalAmount;

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

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }

}