package io.usmon.ebook.models;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashRepository {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public MutableLiveData<FirebaseUser> verifyUserAuthentication() {
        MutableLiveData<FirebaseUser> isAuthenticationMutableLiveData = new MutableLiveData<>();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        isAuthenticationMutableLiveData.setValue(firebaseUser);
        return isAuthenticationMutableLiveData;
    }

}
