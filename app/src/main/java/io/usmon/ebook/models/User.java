package io.usmon.ebook.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class User implements Serializable {

    @Exclude
    public boolean isAuthenticated;

    public User() {
    }
}
