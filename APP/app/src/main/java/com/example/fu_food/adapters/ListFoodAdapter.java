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
import com.example.fu_food.config.Config;
import com.example.fu_food.models.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.ListFoodViewHolder> {

    public static Context context;
    public static List<Food> foods;

    public ListFoodAdapter(Context context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }


    @NonNull
    @Override
    public ListFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_foods_holder, parent, false);
        return new ListFoodAdapter.ListFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFoodViewHolder holder, int position) {
        String imageUrl = Config.getImageUrl();
        Picasso.with(context).load(imageUrl + foods.get(position).getImageUrl())
                .into(holder.imageViewFood);
        holder.textViewRestaurantName.setText(foods.get(position).getRestaurant().getName());
        holder.textViewFoodName.setText(foods.get(position).getName());
        holder.textViewPrice.setText(convertPriceToString(foods.get(position).getPrice()));
        holder.textViewSalesQuantity.setText(foods.get(position).getSalesQuantity() + "");
        holder.textViewReviewPoint.setText(foods.get(position).getReviewPoint() + "");
        holder.textViewNumberOfReview.setText(foods.get(position).getNumberOfReview() + "");
        holder.textViewFoodType.setText(foods.get(position).getFoodCategory().getName());

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    public static class ListFoodViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewFood;
        TextView textViewRestaurantName, textViewFoodName, textViewPointVoting,
                textViewSalesQuantity, textViewPrice, textViewReviewPoint,
                textViewNumberOfReview, textViewFoodType;

        public ListFoodViewHolder(@NonNull View itemView) {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // get position when user clicked
                    int position = getAdapterPosition();
                    // check if item still exists
                    if(position != RecyclerView.NO_POSITION){
                        Food foodClicked = foods.get(position);
                        String foodId = foodClicked.getId();
                        String restaurantId = foodClicked.getRestaurant().getId();

                        openFoodDetailActivity(foodId, restaurantId);
                    }
                }
            });

        }

    }

    public static void openFoodDetailActivity(String foodId, String restaurantId) {
        Intent intent = new Intent(context, FoodDetailActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("FOOD_ID", foodId);
        bundle.putString("RESTAURANT_ID", restaurantId);

        intent.putExtra("KEY_BUNDLE", bundle);


        context.startActivity(intent);
    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }

}

