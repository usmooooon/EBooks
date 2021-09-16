package io.usmon.ebook.views.fragments.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import io.usmon.ebook.R;
import io.usmon.ebook.databinding.FragmentLoginBinding;
import io.usmon.ebook.viewmodel.LoginViewModel;
import io.usmon.ebook.views.activities.MainActivity;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null && !firebaseUser.isEmailVerified()) {
                Navigation.findNavController(requireView()).navigate(
                        R.id.action_loginFragment_to_verifyFragment);
            } else {
                startActivity(new Intent(requireContext(), MainActivity.class));
                requireActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.tvForgotPassword.setOnClickListener(
                v -> Navigation.findNavController(requireActivity(),
                        R.id.action_loginFragment_to_forgotPasswordFragment));

        binding.tvRegister.setOnClickListener(v -> Navigation.findNavController(requireActivity(),
                R.id.action_loginFragment_to_registerFragment));

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            loginViewModel.login(email, password);
        });
    }
}