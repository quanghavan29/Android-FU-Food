package com.example.fu_food.adapters;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.activities.FoodDetailActivity;
import com.example.fu_food.activities.fragment.CartFragment;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.Cart;
import com.example.fu_food.models.Food;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListFoodInCartAdapter extends RecyclerView.Adapter<ListFoodInCartAdapter.ListFoodInCartViewHolder> {

    public static Context context;
    public static List<Cart> carts;
    public IOnClickItemCartListener iOnClickItemCartListener;

    public interface IOnClickItemCartListener {

        void onClickButtonAdd(Cart cart);

        void onClickButtonSub(Cart cart);

        void onClickButtonDelete(Cart cart);

    }

    public ListFoodInCartAdapter(Context context, List<Cart> carts, IOnClickItemCartListener iOnClickItemCartListener) {
        this.context = context;
        this.carts = carts;
        this.iOnClickItemCartListener = iOnClickItemCartListener;
    }

    @NonNull
    @Override
    public ListFoodInCartAdapter.ListFoodInCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_foods_in_cart_holder, parent, false);
        return new ListFoodInCartAdapter.ListFoodInCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFoodInCartAdapter.ListFoodInCartViewHolder holder, int position) {
        Cart cart = carts.get(position);
        Picasso.with(context).load(carts.get(position).getFood().getImageUrl())
                .into(holder.imageViewFood);
        holder.textViewRestaurantName.setText(carts.get(position).getRestaurant().getName());
        holder.textViewFoodName.setText(carts.get(position).getFood().getName()  + " - ");
        holder.textViewOrderQuantity.setText(carts.get(position).getQuantity() + "");
        holder.textViewSubTotal.setText(convertPriceToString(Integer.parseInt(String.valueOf(carts.get(position).getFood().getPrice() * carts.get(position).getQuantity()))));

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickItemCartListener.onClickButtonAdd(cart);
                int oldQuantity = cart.getQuantity();
                int newQuantity = oldQuantity + 1;

                cart.setQuantity(newQuantity);

                holder.textViewOrderQuantity.setText(cart.getQuantity() + "");
                holder.textViewSubTotal.setText(convertPriceToString(Integer.parseInt(String.valueOf(cart.getFood().getPrice() * cart.getQuantity()))));

                SharedPrefConfig.saveCartFoodSharedPref(context, carts);
            }
        });

        holder.buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickItemCartListener.onClickButtonSub(cart);
                int oldQuantity = cart.getQuantity();
                if (oldQuantity > 1) {
                    int newQuantity = oldQuantity - 1;

                    cart.setQuantity(newQuantity);
                }

                holder.textViewOrderQuantity.setText(cart.getQuantity() + "");
                holder.textViewSubTotal.setText(convertPriceToString(Integer.parseInt(String.valueOf(cart.getFood().getPrice() * cart.getQuantity()))));

                SharedPrefConfig.saveCartFoodSharedPref(context, carts);
            }
        });

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickItemCartListener.onClickButtonDelete(cart);
                carts.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());

                carts.remove(cart);
                SharedPrefConfig.saveCartFoodSharedPref(context, carts);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public static class ListFoodInCartViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewFood, imageViewDelete;
        TextView textViewRestaurantName, textViewFoodName, textViewOrderQuantity,
                 textViewSubTotal;
        Button buttonAdd, buttonSub;

        public ListFoodInCartViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewFood = itemView.findViewById(R.id.imageViewFood);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);

            textViewRestaurantName = itemView.findViewById(R.id.textViewRestaurantName);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewOrderQuantity = itemView.findViewById(R.id.textViewOrderQuantity);
            textViewSubTotal = itemView.findViewById(R.id.textViewSubTotal);

            buttonAdd = itemView.findViewById(R.id.buttonAdd);
            buttonSub = itemView.findViewById(R.id.buttonSub);

        }

    }

    private void sendCartsData() {
        List<Cart> dataCarts = carts;
    }

    private String convertPriceToString(int price) {
        return (price / 1000) + ".000đ";
    }

}

