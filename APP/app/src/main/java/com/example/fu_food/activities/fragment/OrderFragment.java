package com.example.fu_food.activities.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.activities.MainActivity;
import com.example.fu_food.activities.MyOrderActivity;
import com.example.fu_food.adapters.ListOrderAdapter;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.Order;
import com.example.fu_food.models.User;
import com.example.fu_food.services.OrderService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {

    private MainActivity mainActivity;
    private View view;

    private RecyclerView recyclerViewListOrder;
    private Button buttonBackToHome;
    private TextView textViewOrderIsEmpty;

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order, container, false);
        mainActivity = (MainActivity) getActivity();

        recyclerViewListOrder = view.findViewById(R.id.recyclerViewListOrder);
        buttonBackToHome = view.findViewById(R.id.buttonBackToHome);
        textViewOrderIsEmpty = view.findViewById(R.id.textViewOrderIsEmpty);

        setListOrder();

        return view;
    }

    // set item view for RecyclerViewBestSellingFood
    private void setListOrder() {

        List<Order> orders = new ArrayList<>();

        User user = SharedPrefConfig.getUserLoginFromSharedPref(mainActivity);

        OrderService.orderService.getOrdersOfUser(user.getId()).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                orders.addAll(response.body());

                if (orders.size() == 0) {
                    textViewOrderIsEmpty.setText("Bạn chưa có đơn hàng nào!");
                }
                ListOrderAdapter listOrderAdapter = new ListOrderAdapter(mainActivity, orders);
                recyclerViewListOrder.setLayoutManager(new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false));
                recyclerViewListOrder.setAdapter(listOrderAdapter);
                listOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(mainActivity, "Call API Get Orders Of User Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}