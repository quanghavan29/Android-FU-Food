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
import com.example.fu_food.models.UserSignUp;
import com.example.fu_food.services.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private Button buttonBack, buttonSignUp;
    private TextView textViewSignIn;
    private EditText editTextName, editTextPhone, editTextPassword, editTextRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // onclick button Back => go back to previous activity
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // onclick text view sign in => open sign in activity
        textViewSignIn = findViewById(R.id.textViewSignIn);
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignInActivity();
            }
        });

        // sign up account
        buttonSignUp = findViewById(R.id.buttonSignUp);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextSignUpPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRePassword = findViewById(R.id.editTextRePassword);

        signUp();

    }

    public void openSignInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void signUp() {
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = editTextName.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String rePassword = editTextRePassword.getText().toString().trim();

                if (isEditTextEmpty(fullName, phone, password, rePassword)) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(rePassword)) {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                AuthService.authService.signUp(fullName, phone, password).enqueue(new Callback<UserSignUp>() {
                    @Override
                    public void onResponse(Call<UserSignUp> call, Response<UserSignUp> response) {
                        if (response.body().getStatusCode() == 200) {
                            Toast.makeText(SignUpActivity.this, "Đăng ký thành công! Đăng nhập ngay.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("SIGN_UP_PHONE", phone);
                            bundle.putString("SIGN_UP_PASSWORD", password);

                            intent.putExtra("KEY_BUNDLE", bundle);

                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Số điện thoại đã được đăng kí!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserSignUp> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public boolean isEditTextEmpty(String fullName, String phone, String password, String rePassword) {
        if (fullName.equals("") || phone.equals("") || password.equals("") || rePassword.equals("")) {
            return true;
        }

        return false;
    }
}