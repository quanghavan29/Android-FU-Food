package com.example.fu_food.activities.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fu_food.R;
import com.example.fu_food.activities.FoodDetailActivity;
import com.example.fu_food.adapters.FoodCategoryAdapter;
import com.example.fu_food.adapters.ListFoodOfRestaurantAdapter;
import com.example.fu_food.models.Food;
import com.example.fu_food.services.FoodService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFragment extends Fragment {

    private View view;
    private FoodDetailActivity foodDetailActivity;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        foodDetailActivity = (FoodDetailActivity) getActivity();

        return view;
    }

}