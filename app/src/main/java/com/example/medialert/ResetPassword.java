package com.example.medialert;

import static com.example.medialert.util.Constants.EMAIL_REGEX;
import static com.example.medialert.util.Constants.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPassword extends AppCompatActivity {

    private TextInputEditText email;
    private static final String TAG = "ResetPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email = findViewById(R.id.email);

    }

    public void resetPassword(View view) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email.getText().toString());

        if (!matcher.matches()) {
            Toast.makeText(this, "Wrong email format", Toast.LENGTH_SHORT).show();
        } else {


            authentication.sendPasswordResetEmail(email.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ResetPassword.this, "Check your email for instructions.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ResetPassword.this, "Failed due to "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onFailure: "+e.getMessage());
                        }
                    });
        }
    }
}