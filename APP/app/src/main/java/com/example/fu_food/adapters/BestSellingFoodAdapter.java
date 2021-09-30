package com.example.fu_food.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.models.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BestSellingFoodAdapter extends RecyclerView.Adapter<BestSellingFoodAdapter.BestSellingFoodViewHolder> {

    public static Context context;
    public static List<Food> foods;

    public BestSellingFoodAdapter(Context context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public BestSellingFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.best_selling_food_holder, parent, false);
        return new BestSellingFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellingFoodViewHolder holder, int position) {
        Picasso.with(context).load(foods.get(position).getImageUrl())
                .into(holder.imageViewBestSellingFood);
        holder.textViewFoodName.setText(foods.get(position).getName());
        holder.textViewPrice.setText(convertPriceToString(foods.get(position).getPrice()));
        holder.textViewSalesQuantity.setText(foods.get(position).getSalesQuantity() + "");
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static final class BestSellingFoodViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewBestSellingFood;
        TextView textViewPrice, textViewFoodName, textViewSalesQuantity;

        public BestSellingFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewBestSellingFood = itemView.findViewById(R.id.imageViewBestSellingFood);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewSalesQuantity = itemView.findViewById(R.id.textViewSalesQuantity);

        }

    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }
}
