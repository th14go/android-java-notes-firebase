package com.tibesoft.notesfirebase.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tibesoft.notesfirebase.MyApplication;
import com.tibesoft.notesfirebase.viewmodel.AuthStatusViewModel;
import com.tibesoft.notesfirebase.viewmodel.SignViewModel;
import com.tibsoft.notesfirebase.R;
import com.tibsoft.notesfirebase.databinding.FragmentSignBinding;


public class SignFragment extends Fragment {

    private FragmentSignBinding binding;
    private SignViewModel signViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignBinding.inflate(inflater, container, false);

        signViewModel = new ViewModelProvider(requireActivity()).get(SignViewModel.class);
        signViewModel.getUserLiveData().observe(requireActivity(), user -> {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(
                    R.id.frame_layout, new HomeFragment(), "home"
            ).commit();
        });
        signViewModel.getErrorLiveData().observe(requireActivity(), exception -> {
            binding.progressLogin.setVisibility(View.INVISIBLE);
            binding.enterLogin.setVisibility(View.VISIBLE);
            binding.progressRegister.setVisibility(View.INVISIBLE);
            binding.enterRegister.setVisibility(View.VISIBLE);
            Toast.makeText(requireActivity(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        });


        setToggleTouch();
        setToolbarTouch();
        clicks();

        return binding.getRoot();
    }

    private void clicks() {
        binding.enterLogin.setOnClickListener(v -> {
            signNoNull("login",
                    binding.emailLogin,
                    binding.passwordLogin,
                    binding.progressLogin,
                    binding.enterLogin);
        });
        binding.enterRegister.setOnClickListener(v -> {
            signNoNull("register",
                    binding.emailRegister,
                    binding.passwordRegister,
                    binding.progressRegister,
                    binding.enterRegister);
        });
    }

    private void signNoNull(String type,
                               EditText editEmail,
                               EditText editPassword,
                               ProgressBar progressBar,
                               Button button) {

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (!email.isEmpty()) {
            if (!password.isEmpty()) {
                progressBar.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (type.equals("login")) {
                            signViewModel.signIn(email, password);
                        }else if(type.equals("register")){
                            signViewModel.signUp(email, password);
                        }
                    }
                }, 1500);

            } else {
                Toast.makeText(requireActivity(), "Insira sua Senha", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireActivity(), "Insira seu E-mail", Toast.LENGTH_SHORT).show();
        }

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

    private void setToolbarAppearance(TextView active, TextView inactive, LinearLayoutCompat visible, LinearLayoutCompat invisible) {
        active.setBackgroundResource(R.drawable.bg_button_bar_gray);
        active.setTextColor(getResources().getColor(R.color.black, null));
        inactive.setBackgroundResource(R.drawable.bg_button_bar_white);
        inactive.setTextColor(getResources().getColor(R.color.gray, null));
        visible.setVisibility(View.VISIBLE);
        invisible.setVisibility(View.INVISIBLE);
    }
}
