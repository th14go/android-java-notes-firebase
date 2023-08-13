package com.tibesoft.notesfirebase.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.tibesoft.notesfirebase.data.database.FirebaseAuthManager;

public class AuthStatusViewModel extends ViewModel {
    private FirebaseAuthManager authManager = new FirebaseAuthManager();
    private MutableLiveData<Boolean> isAuthenticatedLiveData = new MutableLiveData<>();

    public LiveData<Boolean> getIsAuthenticatedLiveData() {
        return isAuthenticatedLiveData;
    }

    public void checkAuthenticationStatus() {
        FirebaseUser currentUser = authManager.getCurrentUser();
        isAuthenticatedLiveData.postValue(currentUser != null);
    }
}
