package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fu_food.R;
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.adapters.ListOrderDetail;
import com.example.fu_food.models.FoodCategory;
import com.example.fu_food.models.OrderDetail;
import com.example.fu_food.services.FoodCategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListOrderDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        recyclerViewListOrderDetails = findViewById(R.id.recyclerViewListOrderDetails);
        setRecyclerViewOrderDetails();
    }

    // set item view for RecyclerFoodCategories
    private void setRecyclerViewOrderDetails() {

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail());
        orderDetails.add(new OrderDetail());
        orderDetails.add(new OrderDetail());
        ListOrderDetail listOrderDetail = new ListOrderDetail(OrderDetailActivity.this, orderDetails);
        recyclerViewListOrderDetails.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this, RecyclerView.VERTICAL, false));
        recyclerViewListOrderDetails.setAdapter(listOrderDetail);
        listOrderDetail.notifyDataSetChanged();

    }
}

