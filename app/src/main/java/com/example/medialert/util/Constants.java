package com.example.medialert.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Constants {
    public static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final FirebaseAuth authentication = FirebaseAuth.getInstance();

    public static final DatabaseReference remindersReference = FirebaseDatabase.getInstance().getReference("reminders");


}
