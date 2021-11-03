package com.example.fu_food.activities.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fu_food.R;
import com.example.fu_food.activities.MainActivity;
import com.example.fu_food.activities.SignInActivity;
import com.example.fu_food.config.SharedPrefConfig;
import com.example.fu_food.models.User;
import com.example.fu_food.models.UserSignIn;
import com.example.fu_food.models.UserUpdate;
import com.example.fu_food.services.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private View view;
    private MainActivity mainActivity;
    private EditText editTextFullName, editTextPhone,
                    editTextOldPassword, editTextNewPassword;
    private Button buttonUpdateProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        mainActivity = (MainActivity) getActivity();

        editTextFullName = view.findViewById(R.id.editTextFullName);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        editTextOldPassword = view.findViewById(R.id.editTextOldPassword);
        editTextNewPassword = view.findViewById(R.id.editTextNewPassword);

        buttonUpdateProfile = view.findViewById(R.id.buttonUpdateProfile);

        setProfile();
        updateProfile();

        return view;
    }

    private void setProfile() {
        User user = SharedPrefConfig.getUserLoginFromSharedPref(mainActivity);

        editTextFullName.setText(user.getFullName());
        editTextPhone.setText(user.getPhone());
    }

    private void updateProfile() {
        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = SharedPrefConfig.getUserLoginFromSharedPref(mainActivity);
                String fullName = editTextFullName.getText().toString().trim();
                String oldPassword = editTextOldPassword.getText().toString().trim();
                String newPassword = editTextNewPassword.getText().toString().trim();
                if (!oldPassword.trim().equals("")) {
                    if (newPassword.trim().equals("")) {
                        Toast.makeText(mainActivity, "Hãy nhập mật khẩu mới!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                AuthService.authService.updateUser(user.getId(), fullName, oldPassword, newPassword).enqueue(new Callback<UserUpdate>() {
                    @Override
                    public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                        if (response.body().getStatus() == 500) {
                            Toast.makeText(mainActivity, "Mật khẩu cũ không chính xác!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mainActivity, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserUpdate> call, Throwable t) {
                        Toast.makeText(mainActivity, "Cập nhật thông tin thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}