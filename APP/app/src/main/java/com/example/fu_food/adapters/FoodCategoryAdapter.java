package com.example.fu_food.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.FoodCategory;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import com.squareup.picasso.Picasso;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.CategoryViewHolder> {

    public static Context context;
    public static List<FoodCategory> foodCategories;

    public FoodCategoryAdapter(List<FoodCategory> foodCategories, Context context) {
        this.context = context;
        this.foodCategories = foodCategories;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // get position when user clicked
                    int position = getAdapterPosition();
                    // check if item still exists
                    if(position != RecyclerView.NO_POSITION){
                        FoodCategory foodCategoryClicked = foodCategories.get(position);
                        Toast.makeText(view.getContext(), "You clicked " + foodCategoryClicked.getId(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

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
        Picasso.with(context).load(foodCategories.get(position).getImage())
            .into(holder.image);
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

}