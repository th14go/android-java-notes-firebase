package com.tibesoft.notesfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tibsoft.notesfirebase.R;
import com.tibsoft.notesfirebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(
                R.id.frame_layout, new LoginFragment(), "login"
        ).commit();
    }
}