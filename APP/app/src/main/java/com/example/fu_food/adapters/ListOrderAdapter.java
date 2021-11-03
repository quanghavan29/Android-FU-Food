package com.example.fu_food.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.activities.FoodDetailActivity;
import com.example.fu_food.activities.MainActivity;
import com.example.fu_food.activities.OrderDetailActivity;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.Order;
import com.example.fu_food.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListOrderAdapter extends RecyclerView.Adapter<ListOrderAdapter.ListOrderViewHolder> {

    public static Context context;
    public static List<Order> orders;

    public ListOrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public ListOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_order_holder, parent, false);
        return new ListOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOrderViewHolder holder, int position) {

        holder.textViewOrderID.setText("ID: " + orders.get(position).getId());
        holder.textViewFullName.setText(orders.get(position).getUser().getFullName() + " - ");
        holder.textViewPhone.setText(orders.get(position).getUser().getPhone());
        holder.textViewAddress.setText("Địa chỉ: " + orders.get(position).getAddress());
        holder.textViewOrderQuantity.setText(orders.get(position).getTotalQuantity() + " sản phẩm");
        holder.textViewOrderedDate.setText(orders.get(position).getOrderedDate().toLocaleString());
        holder.textViewTotalAmount.setText(convertPriceToString(orders.get(position).getTotalAmount()));

        if (orders.get(position).getStatus().equals("pending")) {
            holder.textViewOrderStatus.setText("Chờ xác nhận");
            holder.textViewOrderStatus.setTextColor(context.getResources().getColor(R.color.pending));
        } else if (orders.get(position).getStatus().equals("success")) {
            holder.textViewOrderStatus.setText("Đã giao hàng");
            holder.textViewOrderStatus.setTextColor(context.getResources().getColor(R.color.green));
        } else if (orders.get(position).getStatus().equals("processing")) {
            holder.textViewOrderStatus.setText("Đang giao hàng");
            holder.textViewOrderStatus.setTextColor(context.getResources().getColor(R.color.processing));
        }

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static final class ListOrderViewHolder extends RecyclerView.ViewHolder {

        TextView textViewOrderID, textViewOrderDetailView, textViewOrderedDate,
                textViewFullName, textViewPhone, textViewAddress, textViewTotalAmount,
                textViewOrderStatus, textViewOrderQuantity;

        public ListOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewOrderID = itemView.findViewById(R.id.textViewOrderID);
            textViewOrderDetailView = itemView.findViewById(R.id.textViewOrderDetailView);
            textViewOrderedDate = itemView.findViewById(R.id.textViewOrderedDate);
            textViewFullName = itemView.findViewById(R.id.textViewFullName);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewTotalAmount = itemView.findViewById(R.id.textViewTotalAmount);
            textViewOrderStatus = itemView.findViewById(R.id.textViewOrderStatus);
            textViewOrderQuantity = itemView.findViewById(R.id.textViewOrderQuantity);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // get position when user clicked
                    int position = getAdapterPosition();
                    // check if item still exists
                    if(position != RecyclerView.NO_POSITION){
                        Order orderClicked = orders.get(position);
                        String orderIdClicked = orderClicked.getId();
                        openOrderDetail(orderIdClicked);
                    }
                }
            });

        }

    }

    private static void openOrderDetail(String orderId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("ORDER_ID", orderId);

        intent.putExtra("KEY_BUNDLE", bundle);
        context.startActivity(intent);
    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }
}
