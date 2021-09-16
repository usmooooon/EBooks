package io.usmon.ebook.models;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthRepository {
    private Application application;
    private FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> userMutableLiveData;

    public AuthRepository(Application application) {
        this.application = application;
        firebaseAuth = FirebaseAuth.getInstance();
        userMutableLiveData = new MutableLiveData<>();
    }

    public void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
            } else {
                Toast.makeText(application,
                        "Registration failed" + task.getException().getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
            }else {
                Toast.makeText(application, "Authentication failed!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
