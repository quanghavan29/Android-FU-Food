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
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.adapters.ListOrderDetail;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.FoodCategory;
import com.example.fu_food.models.Order;
import com.example.fu_food.models.OrderDetail;
import com.example.fu_food.models.OrderItem;
import com.example.fu_food.models.User;
import com.example.fu_food.services.FoodCategoryService;
import com.example.fu_food.services.OrderService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListOrderDetails;
    private TextView textViewFullName, textViewPhone, textViewAddress,
            textViewOrderId, textViewOrderDate, textViewTotalAmount,
            textViewOrderStatus, textViewTotalQuantity;

    private Button buttonBackListOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        textViewFullName = findViewById(R.id.textViewFullName);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewOrderId = findViewById(R.id.textViewOrderId);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewOrderDate = findViewById(R.id.textViewOrderDate);
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        textViewOrderStatus = findViewById(R.id.textViewOrderStatus);
        textViewTotalQuantity = findViewById(R.id.textViewTotalQuantity);

        buttonBackListOrders = findViewById(R.id.buttonBackListOrders);

        recyclerViewListOrderDetails = findViewById(R.id.recyclerViewListOrderDetails);
        setRecyclerViewOrderDetails(getUserId(), getOrderId());

        backToListOrders();
    }

    private void backToListOrders() {
        buttonBackListOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderDetailActivity.this, MyOrderActivity.class);

                startActivity(intent);
            }
        });
    }

    private String getUserId() {
        User user = SharedPrefConfig.getUserLoginFromSharedPref(OrderDetailActivity.this);
        String userId = user.getId();

        return userId;
    }

    private String getOrderId() {
        Intent data = OrderDetailActivity.this.getIntent();
        Bundle bundle = data.getBundleExtra("KEY_BUNDLE");
        String orderId = bundle.getString("ORDER_ID");

        return orderId;
    }

    // set item view for RecyclerFoodCategories
    private void setRecyclerViewOrderDetails(String userId, String orderId) {

        OrderService.orderService.getOrderDetail(userId, orderId).enqueue(new Callback<OrderDetail>() {
            @Override
            public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
                OrderDetail orderDetail = response.body();
                List<OrderItem> orderItems = orderDetail.getOrderItems();
                Order order = orderDetail.getOrder();

                textViewFullName.setText(order.getUser().getFullName() + " - ");
                textViewPhone.setText(order.getUser().getPhone());
                textViewOrderId.setText("Mã đơn hàng: " + order.getId());
                textViewAddress.setText(order.getAddress());
                textViewOrderDate.setText(order.getOrderedDate().toLocaleString());
                textViewTotalAmount.setText(convertPriceToString(order.getTotalAmount()));
                textViewTotalQuantity.setText(order.getTotalQuantity() + " (Item)");

                if (order.getStatus().equals("pending")) {
                    textViewOrderStatus.setText("Chờ xác nhận");
                    textViewOrderStatus.setTextColor(OrderDetailActivity.this.getResources().getColor(R.color.pending));
                } else if (order.getStatus().equals("success")) {
                    textViewOrderStatus.setText("Đã giao hàng");
                    textViewOrderStatus.setTextColor(OrderDetailActivity.this.getResources().getColor(R.color.green));
                } else if (order.getStatus().equals("processing")) {
                    textViewOrderStatus.setText("Đang giao hàng");
                    textViewOrderStatus.setTextColor(OrderDetailActivity.this.getResources().getColor(R.color.processing));
                }

                ListOrderDetail listOrderDetail = new ListOrderDetail(OrderDetailActivity.this, orderItems);
                recyclerViewListOrderDetails.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this, RecyclerView.VERTICAL, false));
                recyclerViewListOrderDetails.setAdapter(listOrderDetail);
                listOrderDetail.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<OrderDetail> call, Throwable t) {
                Toast.makeText(OrderDetailActivity.this, "Call api load order detail fail!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }
}

