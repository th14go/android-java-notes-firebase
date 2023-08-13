package com.tibesoft.notesfirebase.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.tibesoft.notesfirebase.data.auth.FirebaseAuthManager;

public class SignViewModel extends ViewModel {

    private FirebaseAuthManager authManager = new FirebaseAuthManager();
    private MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
    private MutableLiveData<Exception> errorLiveData = new MutableLiveData<>();

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<Exception> getErrorLiveData() {
        return errorLiveData;
    }


    public void signUp(String email, String password){
        authManager.signUp(email, password, new FirebaseAuthManager.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                userLiveData.postValue(user);
            }

            @Override
            public void onError(Exception exception) {
                errorLiveData.postValue(exception);
            }
        });
    }

    public void signIn(String email, String password){
        authManager.signIn(email, password, new FirebaseAuthManager.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                userLiveData.postValue(user);
            }

            @Override
            public void onError(Exception exception) {
                errorLiveData.postValue(exception);
            }
        });
    }
}
