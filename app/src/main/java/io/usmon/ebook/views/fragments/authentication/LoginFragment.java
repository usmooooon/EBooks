package io.usmon.ebook.views.fragments.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import io.usmon.ebook.R;
import io.usmon.ebook.viewmodel.LoginRegisterViewModel;

public class LoginFragment extends Fragment {
    private EditText email, password;
    private Button login, register;
    private LoginRegisterViewModel loginRegisterViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginRegisterViewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);
        loginRegisterViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Navigation.findNavController(requireView()).navigate(
                        R.id.action_loginFragment_to_verifyFragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);
        register = view.findViewById(R.id.register);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        register.setOnClickListener(v -> {
            loginRegisterViewModel.register(email.getText().toString(),
                    password.getText().toString());
        });

        login.setOnClickListener(v -> {
            loginRegisterViewModel.login(email.getText().toString(), password.getText().toString());
        });
    }
}