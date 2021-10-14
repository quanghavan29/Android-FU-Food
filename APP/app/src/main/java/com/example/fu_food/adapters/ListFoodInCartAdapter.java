package com.example.fu_food.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.fu_food.models.Cart;
import com.example.fu_food.models.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListFoodInCartAdapter extends RecyclerView.Adapter<ListFoodInCartAdapter.ListFoodInCartViewHolder> {

    public static Context context;
    public static List<Cart> carts;

    public ListFoodInCartAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @NonNull
    @Override
    public ListFoodInCartAdapter.ListFoodInCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_foods_in_cart_holder, parent, false);
        return new ListFoodInCartAdapter.ListFoodInCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFoodInCartAdapter.ListFoodInCartViewHolder holder, int position) {
        Picasso.with(context).load(carts.get(position).getFood().getImageUrl())
                .into(holder.imageViewFood);
        holder.textViewRestaurantName.setText(carts.get(position).getRestaurant().getName());
        holder.textViewFoodName.setText(carts.get(position).getFood().getName());
//        holder.textViewPrice.setText(convertPriceToString(carts.get(position).getgetPrice()));
//        holder.textViewSalesQuantity.setText(foods.get(position).getSalesQuantity() + "");
//        holder.textViewReviewPoint.setText(foods.get(position).getReviewPoint() + "");
//        holder.textViewNumberOfReview.setText(foods.get(position).getNumberOfReview() + "");
//        holder.textViewFoodType.setText(foods.get(position).getFoodCategory().getName());
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public static class ListFoodInCartViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewFood;
        TextView textViewRestaurantName, textViewFoodName, textViewPointVoting,
                textViewSalesQuantity, textViewPrice, textViewReviewPoint,
                textViewNumberOfReview, textViewFoodType;

        public ListFoodInCartViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewFood = itemView.findViewById(R.id.imageViewFood);
            textViewRestaurantName = itemView.findViewById(R.id.textViewRestaurantName);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewPointVoting = itemView.findViewById(R.id.textViewReviewPoint);
            textViewSalesQuantity = itemView.findViewById(R.id.textViewSalesQuantity);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewReviewPoint = itemView.findViewById(R.id.textViewReviewPoint);
            textViewNumberOfReview = itemView.findViewById(R.id.textViewNumberOfReview);
            textViewFoodType = itemView.findViewById(R.id.textViewFoodType);

        }

    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }

}

