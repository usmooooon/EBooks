package io.usmon.ebook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import io.usmon.ebook.models.SplashRepository;
import io.usmon.ebook.models.User;

public class SplashViewModel extends AndroidViewModel {

    private SplashRepository splashRepository;

    public LiveData<FirebaseUser> isUserAuthenticatedLiveData;

    public SplashViewModel(@NonNull Application application) {
        super(application);

        splashRepository = new SplashRepository();

    }



    public void userAuthentication() {
        isUserAuthenticatedLiveData = splashRepository.verifyUserAuthentication();

    }
}
