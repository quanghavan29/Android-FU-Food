package com.example.fu_food.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fu_food.R;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.User;
import com.example.fu_food.models.UserSignIn;
import com.example.fu_food.services.AuthService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private Button buttonCreateAnAccount;
    private Button buttonBack;
    private Button buttonSignIn;
    private TextView textViewForgotPassword;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;

//    public static final String IMAGE_PROFILE_URL = "";
//    public static final String KEY_BUNDLE = "KEY_BUNDLE";

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
                String signInPhone = editTextPhone.getText().toString().trim();
                String signInPassword = (String) editTextPassword.getText().toString().trim();
                signIn(signInPhone, signInPassword);
            }
        });

        // set data user sign up when user sign up success!
        setUserSignUpInfo();
    }

    public void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    
    public void openForgotPasswordActivity() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
    public void openHomePageActivity(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("IMAGE_PROFILE_URL", user.getImageUrl());

        intent.putExtra("KEY_BUNDLE", bundle);

        startActivity(intent);
    }

    public void signIn(String signInPhone, String signInPassword) {
        if (signInPhone.equals("") || signInPassword.equals("")) {
            Toast.makeText(SignInActivity.this, "Vui l??ng nh???p ?????y ????? th??ng tin!", Toast.LENGTH_SHORT).show();
        } else {
            AuthService.authService.signIn(signInPhone, signInPassword).enqueue(new Callback<UserSignIn>() {
                @Override
                public void onResponse(Call<UserSignIn> call, Response<UserSignIn> response) {
                    if (response.body().getStatusCode() == 200) {
                        User user = response.body().getUser();
                        Toast.makeText(SignInActivity.this, "Xin ch??o! " + user.getFullName(), Toast.LENGTH_SHORT).show();

                        SharedPrefConfig.saveUserLoginToSharedPref(SignInActivity.this, user);

                        openHomePageActivity(user);
                    } else if (response.body().getStatusCode() == 400) {
                        if (response.body().getMessage().equals("Phone do not registered!")) {
                            Toast.makeText(SignInActivity.this, "S??? ??i???n tho???i ch??a ???????c ????ng k??!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (response.body().getMessage().equals("Invalid password!")) {
                                Toast.makeText(SignInActivity.this, "M???t kh???u kh??ng ch??nh x??c!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserSignIn> call, Throwable t) {
                    Toast.makeText(SignInActivity.this, "????ng nh???p th???t b???i!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setUserSignUpInfo() {
        Intent data = SignInActivity.this.getIntent();
        Bundle bundle = data.getBundleExtra("KEY_BUNDLE");
        String signUpPhone = bundle.getString("SIGN_UP_PHONE");
        String signUpPassword = bundle.getString("SIGN_UP_PASSWORD");

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);

        editTextPhone.setText(signUpPhone);
        editTextPassword.setText(signUpPassword);
    }
}