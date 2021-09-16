package io.usmon.ebook.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import io.usmon.ebook.viewmodel.SplashViewModel;

public class SplashActivity extends AppCompatActivity {

    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSplashViewModel();
        userAuthentication();
    }

    private void userAuthentication() {
        splashViewModel.userAuthentication();
        splashViewModel.isUserAuthenticatedLiveData.observe(this, user -> {
            if (user.isAuthenticated) {
                goToMainActivity();
            }else {
                gotToAuthActivity();
            }
            finish();
        });
    }

    private void gotToAuthActivity() {
        startActivity(new Intent(this, AuthActivity.class));
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void initSplashViewModel() {
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
    }
}