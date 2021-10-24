package com.example.fu_food.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.example.fu_food.R;
import com.example.fu_food.activities.fragment.FavoriteFragment;
import com.example.fu_food.activities.fragment.HomePageFragment;
import com.example.fu_food.activities.fragment.NoticeFragment;
import com.example.fu_food.activities.fragment.OrderFragment;
import com.example.fu_food.activities.fragment.ProfileFragment;
import com.example.fu_food.activities.fragment.ViewPagerAdapter;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.User;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final int FRM_HOME = 0;
    private static final int FRM_ORDER = 1;
    private static final int FRM_FAVORITE = 2;
    private static final int FRM_NOTICE = 3;
    private static final int FRM_PROFILE = 4;

    private int currentFrm = FRM_HOME;

    private DrawerLayout drawerLayoutHomePage;
    private NavigationView navViewMain;
    private BottomNavigationView navViewMainBottom;
    private Toolbar toolBarMenu;
    private ImageView imageViewProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayoutHomePage = findViewById(R.id.drawerLayoutMain);
        navViewMain = (NavigationView) findViewById(R.id.navViewMain);
        navViewMainBottom = findViewById(R.id.navViewMainBottom);
        toolBarMenu = findViewById(R.id.toolBarMenu);
        imageViewProfileImage = findViewById(R.id.imageViewProfileImage);
        setImageProfile();

        replaceFragment(new HomePageFragment());
        navViewMain.getMenu().findItem(R.id.nav_home).setChecked(true);

        onClickToolBarMenu();
        onClickMenuItem();

        navViewMainBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_home:
                        openHomeFragment();
                        break;
                    case R.id.nav_order:
                        openOrderFragment();
                        break;
                    case R.id.nav_my_favorite:
                        openFavoriteFragment();
                        break;
                    case R.id.nav_notice:
                        openNoticeFragment();
                        break;
                    case R.id.nav_profile:
                        openProfileFragment();
                        break;

                    default: return true;
                }
                drawerLayoutHomePage.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayoutHomePage.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutHomePage.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void onClickToolBarMenu() {
        toolBarMenu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayoutHomePage.openDrawer(GravityCompat.START);
            }
        });
    }

    private void onClickMenuItem() {
        navViewMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_home:
                        openHomeFragment();
                        break;
                    case R.id.nav_order:
                        openOrderFragment();
                        break;
                    case R.id.nav_my_favorite:
                        openFavoriteFragment();
                        break;
                    case R.id.nav_notice:
                        openNoticeFragment();
                        break;
                    case R.id.nav_profile:
                        openProfileFragment();
                        break;
                    case R.id.nav_sign_out:
                        SharedPrefConfig.clearSharedPref(MainActivity.this);
                        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                        startActivity(intent);
                        break;

                    default: return true;
                }
                drawerLayoutHomePage.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_main, fragment);
        transaction.commit();
    }

    private void openHomeFragment() {
        if (currentFrm != FRM_HOME) {
            replaceFragment(new HomePageFragment());
            DeselectMenuItem();
            navViewMain.getMenu().findItem(R.id.nav_home).setChecked(true);
            navViewMainBottom.getMenu().findItem(R.id.nav_home).setChecked(true);

            currentFrm = FRM_HOME;
        }
    }

    private void openOrderFragment() {
        if (currentFrm != FRM_ORDER) {
            replaceFragment(new OrderFragment());
            DeselectMenuItem();
            navViewMain.getMenu().findItem(R.id.nav_order).setChecked(true);
            navViewMainBottom.getMenu().findItem(R.id.nav_order).setChecked(true);

            currentFrm = FRM_ORDER;
        }
    }

    private void openFavoriteFragment() {
        if (currentFrm != FRM_FAVORITE) {
            replaceFragment(new FavoriteFragment());
            DeselectMenuItem();
            navViewMain.getMenu().findItem(R.id.nav_my_favorite).setChecked(true);
            navViewMainBottom.getMenu().findItem(R.id.nav_my_favorite).setChecked(true);

            currentFrm = FRM_FAVORITE;
        }
    }

    private void openNoticeFragment() {
        if (currentFrm != FRM_NOTICE) {
            replaceFragment(new NoticeFragment());
            DeselectMenuItem();
            navViewMain.getMenu().findItem(R.id.nav_notice).setChecked(true);
            navViewMainBottom.getMenu().findItem(R.id.nav_notice).setChecked(true);

            currentFrm = FRM_NOTICE;
        }
    }

    private void openProfileFragment() {
        if (currentFrm != FRM_PROFILE) {
            replaceFragment(new ProfileFragment());
            DeselectMenuItem();
            navViewMain.getMenu().findItem(R.id.nav_profile).setChecked(true);
            navViewMainBottom.getMenu().findItem(R.id.nav_profile).setChecked(true);

            currentFrm = FRM_PROFILE;
        }
    }

    private void DeselectMenuItem() {
        navViewMain.getMenu().findItem(R.id.nav_profile).setChecked(false);
        navViewMain.getMenu().findItem(R.id.nav_notice).setChecked(false);
        navViewMain.getMenu().findItem(R.id.nav_my_favorite).setChecked(false);
        navViewMain.getMenu().findItem(R.id.nav_order).setChecked(false);
        navViewMain.getMenu().findItem(R.id.nav_home).setChecked(false);
    }

    private void setImageProfile() {
        User user = SharedPrefConfig.getUserLoginFromSharedPref(MainActivity.this);
        String imageUrl = user.getImageUrl();

        // if don't have image url => set image profile default
        if (imageUrl.equals("") || imageUrl == null) {
            imageViewProfileImage.setImageResource(R.drawable.profile_image_default);
        } else {
            Picasso.with(MainActivity.this).load(imageUrl)
                    .into(imageViewProfileImage);
        }
    }
}