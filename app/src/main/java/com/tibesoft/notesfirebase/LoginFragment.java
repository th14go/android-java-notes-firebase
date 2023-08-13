package com.tibesoft.notesfirebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.tibsoft.notesfirebase.R;
import com.tibsoft.notesfirebase.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        setToggleTouch();
        setToolbarTouch();

        return binding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setToggleTouch() {
        binding.passwordLogin.setOnTouchListener(new PasswordToggleTouchListener(binding.passwordLogin));
        binding.passwordRegister.setOnTouchListener(new PasswordToggleTouchListener(binding.passwordRegister));
    }

    private void setToolbarTouch() {
        binding.login.setOnClickListener(v -> {
            setToolbarAppearance(binding.login, binding.register, binding.layoutLogin, binding.layoutRegister);
        });

        binding.register.setOnClickListener(v -> {
            setToolbarAppearance(binding.register, binding.login, binding.layoutRegister, binding.layoutLogin);
        });
    }

    private void setToolbarAppearance(TextView active, TextView inactive, LinearLayoutCompat visible, LinearLayoutCompat invisible){
        active.setBackgroundResource(R.drawable.bg_button_bar_gray);
        active.setTextColor(getResources().getColor(R.color.black, null));
        inactive.setBackgroundResource(R.drawable.bg_button_bar_white);
        inactive.setTextColor(getResources().getColor(R.color.gray, null));
        visible.setVisibility(View.VISIBLE);
        invisible.setVisibility(View.INVISIBLE);
    }
}
