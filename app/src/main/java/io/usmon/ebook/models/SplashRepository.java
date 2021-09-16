package io.usmon.ebook.models;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashRepository {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private User user = new User();

    public MutableLiveData<User> verifyUserAuthentication() {
        MutableLiveData<User> isAuthenticationMutableLiveData = new MutableLiveData<>();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        user.isAuthenticated = firebaseUser != null;
        isAuthenticationMutableLiveData.setValue(user);
        return isAuthenticationMutableLiveData;
    }

}
