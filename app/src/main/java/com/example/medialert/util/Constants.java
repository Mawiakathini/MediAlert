package com.example.medialert.util;

import com.google.firebase.auth.FirebaseAuth;

public class Constants {
    public static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final FirebaseAuth authentication = FirebaseAuth.getInstance();
}
