package com.tibesoft.notesfirebase.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.tibesoft.notesfirebase.MyApplication;
import com.tibesoft.notesfirebase.viewmodel.AuthViewModel;
import com.tibsoft.notesfirebase.R;
import com.tibsoft.notesfirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AuthViewModel authStatusViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MyApplication myApp = (MyApplication) getApplication();
        authStatusViewModel = myApp.getAuthStatusViewModel();

        authStatusViewModel.getIsAuthenticatedLiveData().observe(this, isAuthenticated ->{
            if (isAuthenticated) {
                loadFragment(new HomeFragment(), "home");
            }else{
                loadFragment(new SignFragment(), "sign");
            }
        });

    }

    private void loadFragment(Fragment fragment, String tag){
        getSupportFragmentManager().beginTransaction().replace(
                R.id.frame_layout, fragment, tag
        ).commit();
    }
}