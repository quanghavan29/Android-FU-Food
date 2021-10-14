package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fu_food.R;

public class StartAppActivity extends AppCompatActivity {

    private Button buttonWelcome;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        buttonWelcome = findViewById(R.id.buttonWelcome);
        buttonWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignInActivity();
            }
        });
    }

    public void openSignInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("SIGN_UP_PHONE", "");
        bundle.putString("SIGN_UP_PASSWORD", "");

        intent.putExtra("KEY_BUNDLE", bundle);

        SharedPreferences sharedPreferencesUser = getSharedPreferences("USER_FILE.txt", MODE_PRIVATE);
        SharedPreferences sharedPreferencesCart = getSharedPreferences("CART_FILE.txt", MODE_PRIVATE);
        sharedPreferencesUser.edit().clear().commit();
        sharedPreferencesCart.edit().clear().commit();

        startActivity(intent);
    }

}