package com.tibesoft.notesfirebase.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tibesoft.notesfirebase.viewmodel.AuthViewModel;
import com.tibsoft.notesfirebase.R;
import com.tibsoft.notesfirebase.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private AuthViewModel authViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);

        clicks();

        return binding.getRoot();
    }

    private void clicks() {
        binding.logout.setOnClickListener(v -> {
            authViewModel.signOut();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(
                    R.id.frame_layout, new SignFragment(), "sign"
            ).commit();
        });
    }
}
