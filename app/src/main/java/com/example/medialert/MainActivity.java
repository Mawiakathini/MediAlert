package com.example.medialert;

import static com.example.medialert.util.Constants.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToHome(View view) {
        startActivity(new Intent(this, HomeScreen.class));
    }

    public void openLogin(View view) {
        startActivity(new Intent(this, Login.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (authentication.getCurrentUser() != null) {
            finish();
        }
    }
}