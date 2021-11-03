package com.example.fu_food.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.config.Config;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.Cart;
import com.example.fu_food.models.OrderDetail;
import com.example.fu_food.models.OrderItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListOrderDetail extends RecyclerView.Adapter<ListOrderDetail.ListOrderDetailViewHolder> {

    public static Context context;
    public static List<OrderItem> orderItems;

    public ListOrderDetail(Context context, List<OrderItem> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public ListOrderDetail.ListOrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_order_detail_holder, parent, false);
        return new ListOrderDetail.ListOrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOrderDetail.ListOrderDetailViewHolder holder, int position) {
        String imageUrl = Config.getImageUrl();
        Picasso.with(context).load(imageUrl + orderItems.get(position).getImageFood())
                .into(holder.imageViewFood);
        holder.textViewFoodName.setText(orderItems.get(position).getFoodName());
        holder.textViewOrderQuantity.setText(orderItems.get(position).getSubQuantity() + "");
        holder.textViewSubTotal.setText(convertPriceToString(orderItems.get(position).getSubAmount()));
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public static class ListOrderDetailViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewFood;
        TextView textViewFoodName, textViewSubTotal, textViewOrderQuantity;

        public ListOrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewFood = itemView.findViewById(R.id.imageViewFood);
            textViewSubTotal = itemView.findViewById(R.id.textViewSubTotal);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewOrderQuantity = itemView.findViewById(R.id.textViewOrderQuantity);

        }

    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }

}

