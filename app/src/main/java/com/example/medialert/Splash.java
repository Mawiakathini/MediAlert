package com.example.medialert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.medialert.util.Constants;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            if (Constants.authentication.getCurrentUser() != null) {
                startActivity(new Intent(this, HomeScreen.class));
                finish();
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
        }, 3000);

    }
}