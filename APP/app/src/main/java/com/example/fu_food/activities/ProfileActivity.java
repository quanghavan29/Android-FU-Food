package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fu_food.R;

import android.os.Bundle;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    private void setProfile() {

    }
}