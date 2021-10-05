package com.example.fu_food.activities.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerFoodDetailAdapter extends FragmentStatePagerAdapter {


    public ViewPagerFoodDetailAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FoodDetailFragment();

            default:
                return new FoodDetailFragment();
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

}