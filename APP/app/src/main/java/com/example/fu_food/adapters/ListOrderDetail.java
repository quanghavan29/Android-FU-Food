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
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListOrderDetail extends RecyclerView.Adapter<ListOrderDetail.ListOrderDetailViewHolder> {

    public static Context context;
    public static List<OrderDetail> orderDetails;

    public ListOrderDetail(Context context, List<OrderDetail> orderDetails) {
        this.context = context;
        this.orderDetails = orderDetails;
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
        Picasso.with(context).load(imageUrl + "nem_nuong_ep5gai.jpg")
                .into(holder.imageViewFood);
//        holder.textViewFoodName.setText("Bún Bò Huế");
//        holder.textViewOrderQuantity.setText("2");
//        holder.textViewSubTotal.setText(convertPriceToString(70000));
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    public static class ListOrderDetailViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewFood;
        TextView textViewFoodName, textViewSubTotal, textViewOrderQuantity;

        public ListOrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewFood = itemView.findViewById(R.id.imageViewFood);
//            textViewSubTotal = itemView.findViewById(R.id.textViewSubTotal);
//            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
//            textViewOrderQuantity = itemView.findViewById(R.id.textViewOrderQuantity);

        }

    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }

}

