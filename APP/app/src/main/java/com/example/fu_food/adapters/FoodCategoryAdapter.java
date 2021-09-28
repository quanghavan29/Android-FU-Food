package com.example.fu_food.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.models.FoodCategory;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import com.squareup.picasso.Picasso;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<FoodCategory> foodCategories;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(context).inflate(R.layout.food_category_holder, parent, false);

        // here we need to create a layout for recyclerview call items.
        return new CategoryViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Picasso.with(context).load("http://hri.vtgo.vn:8005/profileimages/31e4b3670-4487-408d-92f5-5423a61c8889.jpg")
            .into(holder.image);
//        holder.image.setImageResource(foodCategories.get(position).getImage());

        holder.name.setText(foodCategories.get(position).getName());
        if (foodCategories.get(position).getName().equals("Đồ ăn")) {
            holder.name.setTextColor(context.getResources().getColor(R.color.orange));
            holder.cardView.setStrokeColor(context.getResources().getColor(R.color.orange));
        }
    }

    @Override
    public int getItemCount() {
        return foodCategories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        MaterialCardView cardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            image = itemView.findViewById(R.id.imageViewFoodCategory);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    public FoodCategoryAdapter(List<FoodCategory> foodCategories, Context context) {
        this.context = context;
        this.foodCategories = foodCategories;
    }
}
