package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fu_food.R;
import com.example.fu_food.adapters.BestSellingFoodAdapter;
import com.example.fu_food.adapters.ListOrderAdapter;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.Order;
import com.example.fu_food.models.User;
import com.example.fu_food.services.FoodService;
import com.example.fu_food.services.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
MyOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListOrder;
    private Button buttonBackToHome;
    private TextView textViewOrderIsEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        buttonBackToHome = findViewById(R.id.buttonBackToHome);
        onClickButtonBackToHome();

        textViewOrderIsEmpty = findViewById(R.id.textViewOrderIsEmpty);

        recyclerViewListOrder = findViewById(R.id.recyclerViewListOrder);
        setListOrder();
    }

    private void onClickButtonBackToHome() {
        buttonBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyOrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // set item view for RecyclerViewBestSellingFood
    private void setListOrder() {

        List<Order> orders = new ArrayList<>();

        User user = SharedPrefConfig.getUserLoginFromSharedPref(MyOrderActivity.this);

        OrderService.orderService.getOrdersOfUser(user.getId()).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orders.addAll(response.body());

                if (orders.size() == 0) {
                    textViewOrderIsEmpty.setText("Bạn chưa có đơn hàng nào!");
                }
                ListOrderAdapter listOrderAdapter = new ListOrderAdapter(MyOrderActivity.this, orders);
                recyclerViewListOrder.setLayoutManager(new LinearLayoutManager(MyOrderActivity.this, RecyclerView.VERTICAL, false));
                recyclerViewListOrder.setAdapter(listOrderAdapter);
                listOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(MyOrderActivity.this, "Call API Get Orders Of User Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}