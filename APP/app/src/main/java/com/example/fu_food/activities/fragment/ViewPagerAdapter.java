package com.example.fu_food.activities.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.fu_food.activities.fragment.FavoriteFragment;
import com.example.fu_food.activities.fragment.HomePageFragment;
import com.example.fu_food.activities.fragment.NoticeFragment;
import com.example.fu_food.activities.fragment.OrderFragment;
import com.example.fu_food.activities.fragment.ProfileFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomePageFragment();
            case 1:
                return new OrderFragment();
            case 2:
                return new FavoriteFragment();
            case 3:
                return new NoticeFragment();
            case 4:
                return new ProfileFragment();

            default:
                return new HomePageFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

}