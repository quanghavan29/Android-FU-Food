package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fu_food.R;

public class SignInActivity extends AppCompatActivity {

    private Button buttonCreateAnAccount;
    private Button buttonBack;
    private Button buttonSignIn;
    private TextView textViewForgotPassword;
    private EditText editTextPhone;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // onclick button Back => go back to previous activity
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // onclick text view forgot password => open Forgot Password Activity
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openForgotPasswordActivity();
            }
        });

        // onclick button create an account => open Sign Up Activity
        buttonCreateAnAccount = findViewById(R.id.buttonCreateAccount);
        buttonCreateAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpActivity();
            }
        });

        // onclick button sign in
        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextPhone = findViewById(R.id.editTextPhone);
                editTextPassword = findViewById(R.id.editTextPassword);
                String phoneInputed = editTextPhone.getText().toString().trim();
                String passwordInputed = (String) editTextPassword.getText().toString().trim();
                signIn(phoneInputed, passwordInputed);
            }
        });
    }

    public void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    
    public void openForgotPasswordActivity() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
    public void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void signIn(String phoneInputed, String passwordInputed) {
        if (phoneInputed.equals("") || passwordInputed.equals("")) {
            Toast.makeText(SignInActivity.this, "Nhập số điện thoại và mật khẩu của bạn!", Toast.LENGTH_SHORT).show();
        } else {
            if (phoneInputed.equals("0968904962") && passwordInputed.equals("123")) {
                Toast.makeText(SignInActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                openHomeActivity();
            } else  {
                Toast.makeText(SignInActivity.this, "Mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}