package com.tibesoft.notesfirebase;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.tibesoft.notesfirebase.viewmodel.AuthStatusViewModel;
import com.tibesoft.notesfirebase.viewmodel.SignViewModel;

public class MyApplication extends Application {
    private AuthStatusViewModel authStatusViewModel;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        authStatusViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(AuthStatusViewModel.class);
        context = getApplicationContext();
        authStatusViewModel.checkAuthenticationStatus();
    }

    public static Context getAppContext() {
        return context;
    }

    public AuthStatusViewModel getAuthStatusViewModel() {
        return authStatusViewModel;
    }
}
