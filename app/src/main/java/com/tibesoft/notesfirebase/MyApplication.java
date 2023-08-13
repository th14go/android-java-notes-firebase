package com.tibesoft.notesfirebase;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.tibesoft.notesfirebase.viewmodel.AuthViewModel;

public class MyApplication extends Application {
    private AuthViewModel authStatusViewModel;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        authStatusViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(AuthViewModel.class);
        context = getApplicationContext();
        authStatusViewModel.checkAuthenticationStatus();
    }

    public static Context getAppContext() {
        return context;
    }

    public AuthViewModel getAuthStatusViewModel() {
        return authStatusViewModel;
    }
}
