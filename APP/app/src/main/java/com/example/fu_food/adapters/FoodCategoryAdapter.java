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
import com.example.fu_food.activities.HomeActivity;
import com.example.fu_food.config.Config;
import com.example.fu_food.models.Cart;
import com.example.fu_food.models.Food;
import com.example.fu_food.models.FoodCategory;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import com.squareup.picasso.Picasso;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.FoodCategoryViewHolder> {

    public static Context context;
    public static List<FoodCategory> foodCategories;
    public static IOnClickItemFoodCategoriesListener iOnClickItemFoodCategoriesListener;

    public interface IOnClickItemFoodCategoriesListener {
        void onClickFoodType(FoodCategory foodCategory);

    }

    public FoodCategoryAdapter() {
    }

    public FoodCategoryAdapter(List<FoodCategory> foodCategories, Context context, IOnClickItemFoodCategoriesListener iOnClickItemFoodCategoriesListener) {
        this.context = context;
        this.foodCategories = foodCategories;
        this.iOnClickItemFoodCategoriesListener = iOnClickItemFoodCategoriesListener;
    }

    public static class FoodCategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        MaterialCardView cardView;

        public FoodCategoryViewHolder(@NonNull View itemView) {
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
                        iOnClickItemFoodCategoriesListener.onClickFoodType(foodCategoryClicked);
//                        name.setTextColor(context.getResources().getColor(R.color.orange));
//                        cardView.setStrokeColor(context.getResources().getColor(R.color.orange));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public FoodCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(context).inflate(R.layout.food_category_holder, parent, false);

        // here we need to create a layout for recyclerview call items.
        return new FoodCategoryViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull FoodCategoryViewHolder holder, int position) {
        if (foodCategories.get(position).getImageUrl() != null && !foodCategories.get(position).getImageUrl().equals("")) {
            String imageUrl = Config.getImageUrl();
            Picasso.with(context).load(imageUrl + foodCategories.get(position).getImageUrl())
                    .into(holder.image);
        }
        holder.name.setText(foodCategories.get(position).getName());
//        if (foodCategories.get(position).getId().equals("allFood")) {
//            holder.name.setTextColor(context.getResources().getColor(R.color.orange));
//            holder.cardView.setStrokeColor(context.getResources().getColor(R.color.orange));
//        }
    }

    @Override
    public int getItemCount() {
        return foodCategories.size();
    }

}
