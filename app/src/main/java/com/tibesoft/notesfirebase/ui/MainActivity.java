package com.tibesoft.notesfirebase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tibesoft.notesfirebase.MyApplication;
import com.tibesoft.notesfirebase.viewmodel.AuthStatusViewModel;
import com.tibsoft.notesfirebase.R;
import com.tibsoft.notesfirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AuthStatusViewModel authStatusViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MyApplication myApp = (MyApplication) getApplication();
        authStatusViewModel = myApp.getAuthStatusViewModel();

        authStatusViewModel.getIsAuthenticatedLiveData().observe(this, isAuthenticated ->{
            if (isAuthenticated) {
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_layout, new HomeFragment(), "home"
                ).commit();
            }else{
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_layout, new SignFragment(), "sign"
                ).commit();
            }
        });


    }
}